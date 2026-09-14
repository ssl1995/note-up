# CompletableFuture 与 TransmittableThreadLocal 详解

> 背景：Crane 权限查询接口 V2 优化（串行 → 并行）的两个核心技术选型。
> 项目事实：JDK8 + `com.alibaba:transmittable-thread-local:2.14.2`，代码见 `EmployeeAuthorityServiceImpl`（V2 并行查询）、`FetchAuthorityThreadContext`（TTL 上下文）。

---

## 一、CompletableFuture

### 1. 是什么 / 解决什么问题

JDK8 引入的异步编排工具，**Future 的增强版**。它同时实现两个接口：

- `Future`：负责"拿结果"（get/join）；
- `CompletionStage`：定义了 50+ 个**任务编排**方法（串行、组合、异常处理）。

**传统 Future 的三大局限**（面试先说痛点）：

1. `get()` 只能**阻塞**等待，无法"完成后自动触发下一步"；
2. 无法表达**任务间依赖与组合**（A、B 都完成后合并结果）；
3. **异常处理弱**，异常只能在 get 时以 ExecutionException 抛出。

### 2. 核心 API 分类（附小例子）

**① 创建任务**

```java
CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "有返回值");              // 默认 commonPool
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "有返回值", executor);    // 指定线程池（推荐）
CompletableFuture<Void>   f3 = CompletableFuture.runAsync(() -> {}, executor);              // 无返回值
```

**② 串行变换**（记忆口诀：thenApply=map，thenCompose=flatMap）

```java
f1.thenApply(s -> s + "!");            // 有入有出，同步变换
f1.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "!"));  // 返回值本身是个 CF，扁平化，避免 CF<CF<T>>
f1.thenAccept(System.out::println);    // 消费，无返回
```

**③ 并行组合**（thenCombine=zip，allOf=等全部）

```java
f1.thenCombine(f2, (a, b) -> a + b);                       // 两个结果合并
CompletableFuture.allOf(f1, f2, f3).get(3, TimeUnit.SECONDS);  // 等全部完成 + 整体超时（Crane 用法）
CompletableFuture.anyOf(f1, f2);                           // 任一完成即返回
```

**④ 异常处理**

```java
f1.exceptionally(e -> "兜底值");        // 捕获异常给默认值
f1.handle((r, e) -> r);                 // 无论成败都执行（BiFunction）
f1.whenComplete((r, e) -> {});          // 类似 finally，不改变结果
```

**⑤ 结果获取**

- `join()`：不抛受检异常（编译期省心）；`get(timeout)`：可带超时（Crane 用它做整体超时）。
- ⚠️ `orTimeout()` / `completeOnTimeout()` 是 **JDK9+** 才有的，JDK8 项目（如 Crane）只能用 `allFuture.get(timeout)`。

**async 后缀的含义**：`thenApplyAsync` 会换到线程池执行；不带 async 的版本在"上一个任务完成的线程"或"当前调用线程"执行。

### 3. 底层实现

- 每个 CompletableFuture 内部有一个 `result` 字段（**CAS 更新**，保证多线程下的原子性与可见性）+ 一个**无锁 Completion 栈**（链表结构）。
- 调用 `thenXxx` 时，把一个 `UniApply` / `BiApply` 等 Completion 节点**压栈**；任务完成（complete）时，**弹栈依次触发**所有依赖节点——这就是"编排"的实现本质：观察者模式的链式触发。
- 默认线程池 `ForkJoinPool.commonPool`：**全局共享**、并行度 = CPU 核数 - 1，为 CPU 密集的分治任务设计。**IO 密集任务（DB/RPC 查询）必须自定义线程池**，否则阻塞任务占满 commonPool，会拖垮所有共用它的功能（如并行流 parallelStream）。

### 4. Crane 为什么选它（V2 并行化）

```java
// 线程池：TtlExecutors 包装（见 TTL 部分），业务隔离的 login-auth 池
executorService = TtlExecutors.getTtlExecutorService(
    executorServiceFactory.getOrCreateExecutorService("login-auth"));

// 并行查 N 个业务系统
for (BusinessVO businessVO : request.getBusinessVOS()) {
    futures.add(CompletableFuture.supplyAsync(() -> {
        try { /* 查角色 + 查权限 */ return new BusinessAuthorityVO(...); }
        catch (Throwable e) { log.error(...); return null; }   // 单系统失败不影响其他
    }, executorService));
}
CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
    .get(fetchV2TimeOut, TimeUnit.SECONDS);   // 整体超时控制
```

**选型理由**：

| 候选方案 | 为什么不选 |
|---|---|
| 手写 ExecutorService + 逐个 future.get() | get 是串行收集（第一个慢就堵后面）；整体超时、异常隔离都要自己写，代码量大 |
| CountDownLatch | 只能"等"，拿不到各任务结果，也无法编排 |
| parallelStream 并行流 | 底层是 commonPool 不可控、无超时控制、异常处理弱 |
| **CompletableFuture** ✅ | 声明式编排、for + supplyAsync + allOf 三行完成"并行+聚合+超时"；单系统 catch 返回 null 实现**异常隔离（可用性优先）**；自定义 login-auth 线程池与 commonPool 隔离 |

---

## 二、TransmittableThreadLocal（TTL）

### 1. 是什么

阿里开源组件（`com.alibaba:transmittable-thread-local`），解决**线程池/异步场景下 ThreadLocal 上下文传递**问题。

### 2. 三代方案演进（面试叙事线）

| 方案 | 机制 | 线程池场景为什么不行 |
|---|---|---|
| ThreadLocal | 值绑定在**当前线程**的 ThreadLocalMap | 线程池**复用线程**，异步任务跑在别的线程上 → 拿不到提交线程的值；甚至读到上个任务的脏数据 |
| InheritableThreadLocal | **线程创建时**把父线程的值拷贝给子线程 | 线程池线程**早已创建好并反复复用**，不会在任务提交时重新拷贝 → 失效；且拷贝的是引用，存在脏数据风险 |
| **TransmittableThreadLocal** ✅ | **任务提交时**把上下文打包带走，执行时回放 | 把传递时机从"线程创建"挪到"任务提交"，正确匹配线程池模型 |

### 3. 核心 API

```java
// 声明：用法与 ThreadLocal 完全一致（get / set / remove）
private static final TransmittableThreadLocal<FetchAuthorityParam> CONTEXT = new TransmittableThreadLocal<>();
```

三种接入方式（**Crane 用的是第 ② 种**）：

1. **修饰任务**：`TtlRunnable.wrap(runnable)` / `TtlCallable.wrap(callable)`；
2. **修饰线程池（推荐）**：`TtlExecutors.getTtlExecutorService(executor)` —— 之后提交到这个池的所有任务自动具备传递能力；
3. **Java Agent 无侵入**：启动加 `-javaagent:ttl-agent.jar`，字节码增强 JDK 线程池类，业务代码零改动。

### 4. 底层实现：capture → replay → restore 三步曲

TTL 的关键在包装器（TtlRunnable / TtlExecutors 返回的代理池），对每次任务执行做三件事：

1. **capture（快照）**：**任务提交时**（在提交线程），捕获该线程所有 TTL 实例的当前值，打包进任务对象。TTL 内部用一个全局注册表（WeakHashMap 管理所有 TTL 实例，防内存泄漏）知道"要捕哪些"。
2. **replay（回放）**：**任务执行前**（在工作线程），先把工作线程的原值备份，再把快照设置进去。
3. **restore（恢复）**：**任务执行后**，恢复工作线程的原值——**防止线程复用导致上下文串扰/泄漏**，这是 TTL 区别于"简单复制"的关键设计。

注意：capture 默认是**浅拷贝（引用共享）**，上下文对象应当只读；需要隔离时用 `copy()` 定制深拷贝。

### 4.1 为什么三步曲能解决线程池复用导致的丢失？（Crane 代码走读）

**问题本质**：普通 ThreadLocal 的存储模型是「线程 → 值」，而线程池破坏了两个前提：①任务不在提交线程上执行（工作线程 Map 里没值 → 丢失）；②池化线程会被别人的任务复用（残留值 → 串数据）。TTL 的本质是把绑定关系从「线程 → 值」改成「**任务 → 值**」——让值跟着任务走。

```plain text
T-main（Tomcat 请求线程）                login-auth 线程池 W-7
─────────────────────────              ──────────────────────
① CONTEXT.set(param)                    
   → 值写在 T-main 的 ThreadLocalMap
② supplyAsync(task, ttl包装过的executor)
   └─【capture】此刻还在 T-main 上！
      读出 CONTEXT.get()=param，快照存进任务对象
      ──────────────────────────────→  ③ W-7 取出任务
                                        【replay】备份 W-7 原值(null)，把 param 写入 W-7 的 Map
                                        ④ 业务代码 CONTEXT.get() = param ✅ 不再回源查库
                                        【restore】恢复 W-7 原值(null) ← 来时什么样走时什么样
⑤ finally { CONTEXT.clear(); }  → 清理 T-main（Tomcat 线程同样被复用）
```

- ① `EmployeeAuthorityServiceImpl` 主流程 `initFetchAuthorityThreadContext(...)`；② `TtlExecutors.getTtlExecutorService(...)` 包装使每次提交自动包成 `TtlRunnable`；④ 子线程 `FetchAuthorityThreadContext.getAllBusinessVOS()` 命中 ThreadLocal，不再回源 `fetchAllBusiness()`；⑤ 主流程 finally clear。

**每一步缺一不可**：

| 步骤 | 缺了它会怎样 |
|---|---|
| capture（提交时快照） | 任务入队后提交线程就去干别的了，"值还在场"的最后时机就是提交这一刻；等工作线程想读时已无法知道"谁提交的、值是什么" |
| replay（执行前回放） | 工作线程 Map 无值 → CONTEXT.get()=null → 每个子线程回源重查全量业务系统，并行收益被抵消 |
| restore（执行后恢复） | W-7 下个任务可能是员工 B 的：若残留员工 A 的 param 且 B 的任务没设值，就**拿 A 的业务列表算 B 的权限 = 串数据事故**；值常驻池线程 = 内存泄漏 |

> 面试话术：TTL 把传递时机从"线程创建/同线程"挪到任务提交这一刻——capture 在值还在场时塞进任务，replay 让工作线程临时"扮演"提交线程，restore 抹掉痕迹防止池线程把上下文带给下一个任务。restore 防串数据是线程池场景独有的问题，普通 ThreadLocal 根本不考虑。

### 5. Crane 为什么选它（V2 上下文传递）

**问题**：V2 并行后，N 个子线程都需要主线程已查好的全量 BusinessVO / 管理员系统列表。普通 ThreadLocal 在 CompletableFuture 异步线程里**拿不到** → 每个子线程都得重复查一次全量业务系统，**抵消并行收益**。

```java
// ① 声明 TTL 上下文（FetchAuthorityThreadContext）
private static final TransmittableThreadLocal<FetchAuthorityParam> CONTEXT = new TransmittableThreadLocal<>();

// ② 主线程查询前初始化上下文
initFetchAuthorityThreadContext(employeeNumber, allBusinessVOS);
try {
    ... // V2 并行 / V3 批量查询，子线程内 CONTEXT.get() 直接拿到
} finally {
    FetchAuthorityThreadContext.clear();   // ③ finally 清理，防线程复用脏数据/内存泄漏
}

// 子线程读取时优先走 ThreadLocal，拿不到才回源查询
public static List<BusinessVO> getAllBusinessVOS() {
    if (isFetchV2() && Objects.nonNull(get())) { return get().getAllBusinessVOS(); }
    return businessService.fetchAllBusiness();
}
```

**选型理由**：改动极小（线程池只包一层，业务代码零侵入）；InheritableThreadLocal 在线程池下失效；自己手写"任务装饰器复制上下文"等于重造 TTL。

---

## 三、选型总结（面试话术）

| 需求 | 候选 | 最终选择 | 一句话理由 |
|---|---|---|---|
| N 个系统并行查询编排 | 手写 Future / CountDownLatch / parallelStream | **CompletableFuture** | 声明式编排 + 单系统异常隔离 + allOf 整体超时 |
| 并行任务线程池 | commonPool | **自定义 login-auth 池** | IO 密集阻塞任务不能占全局 commonPool |
| 上下文跨线程传递 | ThreadLocal / InheritableThreadLocal | **TTL（TtlExecutors 包装）** | 线程池复用线程，前两者失效；TTL 把传递时机挪到任务提交时 |

**一条线串起来背**：V1 串行慢 → V2 用 CompletableFuture 并行（异常隔离 + 超时 + 独立线程池）→ 并行引入"子线程拿不到主线程上下文"的新问题 → TTL（capture/replay/restore）解决 → V3 进一步合并批量 SQL 降 DB 压力。
