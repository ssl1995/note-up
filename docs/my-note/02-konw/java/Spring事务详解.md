# Spring事务详解：传播行为与隔离级别
## 一、为什么需要理解传播行为和隔离级别？
平时使用 `@Transactional` 注解时，Spring默认使用 `REQUIRED` 传播行为和数据库默认隔离级别。但在复杂业务场景中，不合理的配置会导致：
- 数据不一致（该回滚的没回滚）
- 性能问题（不必要的串行化）
- 事务失效（代码写了但没生效）
## 二、7种传播行为详解
### 2.1 REQUIRED（默认）
**含义**：如果当前没有事务，就新建一个事务；如果已经存在一个事务中，加入到这个事务中。
**场景**：最常用的传播行为，大多数业务场景适用。
**代码示例**：
```java
@Transactional(propagation = Propagation.REQUIRED)
public void requiredExample() {
    // 业务逻辑
    innerService.requiredMethod();
    // 如果innerService抛出异常，整个事务回滚（包括当前方法）
}
```
**关键点**：所有操作在一个事务中，要么全部成功，要么全部回滚。
### 2.2 SUPPORTS
**含义**：如果当前有事务，就在事务中执行；如果没有事务，就以非事务方式执行。
**场景**：查询操作，有事务更好，没有也能运行。
**代码示例**：
```java
@Transactional(propagation = Propagation.SUPPORTS)
public void supportsExample() {
    // 查询操作，有没有事务都可以
    innerService.supportsMethod();
}
```
### 2.3 MANDATORY
**含义**：如果当前有事务，就在事务中执行；如果没有事务，就抛出异常。
**场景**：强制要求必须在事务中执行的方法。
**代码示例**：
```java
@Transactional(propagation = Propagation.MANDATORY)
public void mandatoryExample() {
    // 如果外层没有事务，直接抛异常
    innerService.mandatoryMethod();
}
```
### 2.4 REQUIRES_NEW
**含义**：新建事务，如果当前存在事务，把当前事务挂起。
**场景**：需要独立事务的操作，不受外层事务影响。
**典型应用**：日志记录、审计操作。
**代码示例**：
```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void requiresNewExample() {
    // 当前事务
    innerService.requiresNewMethod();  // 新起一个事务
    // innerService的事务独立提交或回滚，不影响外层
}
```
**关键点**：内层事务和外层事务完全独立，互不影响。
### 2.5 NOT_SUPPORTED
**含义**：以非事务方式执行，如果当前存在事务，把当前事务挂起。
**场景**：不需要事务的操作，比如发送通知。
**代码示例**：
```java
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public void notSupportedExample() {
    // 非事务执行，外层事务被挂起
    innerService.notSupportedMethod();
}
```
### 2.6 NEVER
**含义**：以非事务方式执行，如果当前存在事务，则抛出异常。
**场景**：明确不需要事务的方法。
**代码示例**：
```java
@Transactional(propagation = Propagation.NEVER)
public void neverExample() {
    // 如果外层有事务，直接抛异常
    innerService.neverMethod();
}
```
### 2.7 NESTED
**含义**：如果当前存在事务，则在嵌套事务内执行；如果当前没有事务，则执行与REQUIRED类似的操作。
**场景**：嵌套事务，可以独立回滚（部分回滚）。
**注意**：需要数据库支持保存点（SAVEPOINT）。
**代码示例**：
```java
@Transactional(propagation = Propagation.NESTED)
public void nestedExample() {
    // 外层事务
    try {
        innerService.nestedMethod();  // 嵌套事务
    } catch (Exception e) {
        // 只回滚嵌套事务，外层事务继续
        System.out.println("嵌套事务回滚，外层继续");
    }
}
```
**关键点**：NESTED 和 REQUIRES_NEW 的区别：
- NESTED：是外层事务的子事务，外层提交时内层才提交，外层回滚时内层也回滚
- REQUIRES_NEW：完全独立的新事务，和外层事务无关
## 三、4种隔离级别详解
### 3.1 并发事务的问题
在理解隔离级别之前，先了解并发事务的3个问题：
**1. 脏读（Dirty Read）**
- 事务A读取了事务B未提交的数据
- 如果事务B回滚，事务A读到的就是"脏数据"
**2. 不可重复读（Non-repeatable Read）**
- 事务A多次读取同一数据，结果不一致
- 因为事务B在事务A读取期间修改并提交了数据
**3. 幻读（Phantom Read）**
- 事务A多次查询同一范围的数据，结果集不一致
- 因为事务B在事务A查询期间插入了新数据
### 3.2 隔离级别对比
| 隔离级别 | 脏读 | 不可重复读 | 幻读 | 性能 |
|---------|------|-----------|------|------|
| READ_UNCOMMITTED | ❌ | ❌ | ❌ | 最好 |
| READ_COMMITTED | ✅ | ❌ | ❌ | 较好 |
| REPEATABLE_READ | ✅ | ✅ | ⚠️* | 一般 |
| SERIALIZABLE | ✅ | ✅ | ✅ | 最差 |
*注：MySQL通过MVCC和间隙锁解决了大部分幻读问题
### 3.3 各隔离级别详解
**1. READ_UNCOMMITTED（读未提交）**
- 可以读到其他事务未提交的数据
- 几乎不用，性能最好但数据最不安全
```java
@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public void readUncommittedExample() {
    // 可以读到其他事务未提交的数据
    accountService.readData();
}
```
**2. READ_COMMITTED（读已提交）**
- 只能读到已提交的数据
- 大多数数据库的默认级别（Oracle、PostgreSQL、SQL Server）
```java
@Transactional(isolation = Isolation.READ_COMMITTED)
public void readCommittedExample() {
    // 同一事务中，两次读取可能结果不同
    accountService.readData();
}
```
**3. REPEATABLE_READ（可重复读）**
- 同一事务中，多次读取结果一致
- MySQL的默认隔离级别
```java
@Transactional(isolation = Isolation.REPEATABLE_READ)
public void repeatableReadExample() {
    // 多次读取结果一致
    accountService.readData();
}
```
**4. SERIALIZABLE（串行化）**
- 完全串行执行，相当于单线程
- 对数据一致性要求极高的场景，如金融交易
```java
@Transactional(isolation = Isolation.SERIALIZABLE)
public void serializableExample() {
    // 完全串行，性能最差但最安全
    accountService.readData();
}
```
## 四、事务失效的5大场景
### 4.1 自调用导致事务失效
**原因**：Spring事务基于AOP代理，自调用不走代理。
```java
public void selfInvocationExample() {
    // 直接调用this.transactionalMethod()，事务不生效！
    this.transactionalMethod();
}

@Transactional
public void transactionalMethod() {
    // 事务不会生效
}
```
**解决方案**：
1. 注入自身代理：`@Autowired private TransactionFailureExample self;`
2. 使用 `AopContext.currentProxy()`
3. 将方法拆分到不同的Service中
### 4.2 异常被捕获导致事务失效
**原因**：默认只回滚RuntimeException和Error。
```java
@Transactional
public void exceptionCaughtExample() {
    try {
        innerService.requiredMethod();
        throw new Exception("受检异常");
    } catch (Exception e) {
        // 异常被捕获，事务不会回滚！
    }
}
```
**解决方案**：
1. 不要捕获异常，让异常抛出
2. 或者设置 `@Transactional(rollbackFor = Exception.class)`
### 4.3 多线程导致事务失效
**原因**：事务是线程绑定的。
```java
@Transactional
public void multiThreadExample() {
    new Thread(() -> {
        // 新线程中的操作不在原事务中
        innerService.requiredMethod();
    }).start();
}
```
**解决方案**：在新线程中重新开启事务。
### 4.4 数据库引擎不支持事务
**原因**：MyISAM不支持事务。
**解决方案**：使用InnoDB引擎。
### 4.5 方法不是public
**原因**：Spring AOP只能代理public方法。
```java
@Transactional
protected void protectedMethod() { }  // 不生效

@Transactional
private void privateMethod() { }      // 不生效
```
## 五、经验总结
### 5.1 传播行为选择建议
| 场景 | 推荐传播行为 |
|------|-------------|
| 普通业务方法 | REQUIRED（默认）|
| 查询方法 | SUPPORTS 或 REQUIRED |
| 日志/审计 | REQUIRES_NEW |
| 需要部分回滚 | NESTED |
| 强制要求事务 | MANDATORY |
### 5.2 隔离级别选择建议
| 场景 | 推荐隔离级别 |
|------|-------------|
| 大多数业务 | 数据库默认（MySQL: REPEATABLE_READ, Oracle: READ_COMMITTED）|
| 高并发查询 | READ_COMMITTED |
| 金融交易 | SERIALIZABLE |
| 报表统计 | READ_COMMITTED（可接受短暂不一致）|
### 5.3 最佳实践
1. **默认使用 `@Transactional`**，不指定传播行为和隔离级别
2. **REQUIRES_NEW 用于独立操作**：日志、审计、消息发送
3. **NESTED 用于部分回滚**：批量操作中部分失败不影响其他
4. **避免在事务中调用远程接口**：网络延迟导致事务时间过长
5. **避免在事务中使用多线程**：事务是线程绑定的
6. **受检异常要指定 rollbackFor**：`@Transactional(rollbackFor = Exception.class)`
7. **只读查询加 readOnly**：`@Transactional(readOnly = true)` 优化性能
### 5.4 常见误区
1. **误区**：`@Transactional` 加在Controller层
   **纠正**：应该加在Service层
2. **误区**：事务嵌套就是REQUIRES_NEW
   **纠正**：NESTED是嵌套事务，REQUIRES_NEW是独立事务
3. **误区**：隔离级别越高越好
   **纠正**：越高性能越差，根据业务选择
4. **误区**：捕获异常后事务还能回滚
   **纠正**：默认捕获后不回滚，除非设置rollbackFor
### 5.5 面试高频问题
1. **REQUIRED 和 REQUIRES_NEW 的区别？**
   - REQUIRED：加入当前事务或新建事务
   - REQUIRES_NEW：挂起当前事务，新建独立事务
2. **NESTED 和 REQUIRES_NEW 的区别？**
   - NESTED：基于保存点，外层回滚内层也回滚
   - REQUIRES_NEW：完全独立，互不影响
3. **MySQL默认隔离级别是什么？解决了什么问题？**
   - REPEATABLE_READ
   - 解决了脏读、不可重复读，通过MVCC和间隙锁解决了大部分幻读
4. **事务失效的场景有哪些？**
   - 自调用、异常被捕获、多线程、非public方法、数据库引擎不支持
## 六、参考代码
代码路径：`note-know/src/main/java/com/ssl/know/java/transaction/`
- `PropagationExample.java`：7种传播行为示例
- `IsolationExample.java`：4种隔离级别示例
- `TransactionFailureExample.java`：事务失效场景示例
- `InnerService.java` / `AccountService.java`：辅助服务类
