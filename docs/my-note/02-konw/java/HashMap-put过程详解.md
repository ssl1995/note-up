# HashMap put 过程详解（JDK 8+）

## 一、put 流程概览

```
┌─────────────────────────────────────────────────────────────────┐
│                     HashMap.put(key, value)                      │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  1. 计算 key 的 hash 值                                          │
│     hash = (h = key.hashCode()) ^ (h >>> 16)                    │
│     【扰动函数：让高位也参与运算，减少哈希冲突】                      │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│  2. 计算数组索引（桶位置）                                        │
│     index = (n - 1) & hash                                      │
│     【n 是数组长度，必须是 2 的幂次方】                             │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │  该位置是否为空？  │
                    └─────────────────┘
                          │        │
                    是 ◄──┘        └──► 否
                    │                  │
                    ▼                  ▼
        ┌──────────────────┐   ┌──────────────────────────────┐
        │ 直接插入新节点      │   │ 3. 遍历链表/红黑树            │
        │ （无哈希冲突）      │   │                              │
        └──────────────────┘   │  3.1 如果 key 已存在           │
                               │      → 覆盖旧值，返回旧值        │
                               │                              │
                               │  3.2 如果 key 不存在           │
                               │      → 插入新节点              │
                               │         - 链表：尾插法          │
                               │         - 红黑树：树插入        │
                               └──────────────────────────────┘
                                                    │
                                                    ▼
                               ┌──────────────────────────────┐
                               │ 4. 检查是否需要扩容            │
                               │    size > threshold 时扩容     │
                               │    threshold = capacity * 0.75 │
                               └──────────────────────────────┘
```

---

## 二、源码级详细解析

### 2.1 put 方法入口

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
```

### 2.2 hash 计算（扰动函数）

```java
static final int hash(Object key) {
    int h;
    // key 为 null 时 hash 为 0，放在数组第 0 个位置
    // (h = key.hashCode()) ^ (h >>> 16)：高 16 位与低 16 位异或
    // 目的：让高位也参与索引计算，减少哈希冲突
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

**为什么需要扰动函数？**
```
数组长度通常较小（如 16），直接用 hashCode 计算索引：
index = hash & (n-1) = hash & 0x1111

只有低 4 位参与运算，高位完全没用到！
如果两个 key 的 hashCode 只有高位不同，索引会相同 → 冲突

扰动后：hash = h ^ (h >>> 16)
高 16 位和低 16 位混合，索引计算更均匀
```

### 2.3 putVal 核心方法

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    
    // ========== 步骤1：初始化或扩容 ==========
    // 如果 table 为空或长度为 0，调用 resize() 初始化
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    
    // ========== 步骤2：计算索引，检查该位置是否为空 ==========
    // i = (n - 1) & hash 计算桶索引
    // 如果该位置为空，直接插入新节点
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    
    else {
        // ========== 步骤3：哈希冲突处理 ==========
        Node<K,V> e; K k;
        
        // 3.1 检查第一个节点是否就是要找的 key
        if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;  // key 已存在，记录该节点
        
        // 3.2 如果是红黑树节点，走红黑树插入逻辑
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        
        // 3.3 链表处理
        else {
            for (int binCount = 0; ; ++binCount) {
                // 遍历到链表尾部
                if ((e = p.next) == null) {
                    // 尾插法插入新节点
                    p.next = newNode(hash, key, value, null);
                    // 链表长度 >= 8，转换为红黑树
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                // 找到相同的 key，跳出循环
                if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        
        // ========== 步骤4：key 已存在，覆盖旧值 ==========
        if (e != null) {
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;  // 覆盖旧值
            afterNodeAccess(e);
            return oldValue;      // 返回旧值
        }
    }
    
    // ========== 步骤5：修改计数 +1，检查扩容 ==========
    ++modCount;
    if (++size > threshold)
        resize();  // 扩容
    afterNodeInsertion(evict);
    return null;
}
```

---

## 三、关键知识点详解

### 3.1 索引计算：为什么用 `(n-1) & hash`？

```java
// 取模运算的优化
// 当 n 是 2 的幂次方时：hash % n == hash & (n-1)

// 示例：n = 16 (2^4)
// n - 1 = 15 = 0b1111
// hash & 0b1111 相当于取 hash 的低 4 位

// 为什么不用 %？
// & 是位运算，比 % 快很多
// 但 & 要求 n 必须是 2 的幂次方
```

**为什么数组长度必须是 2 的幂次方？**
```
1. 保证 (n-1) & hash 等价于 hash % n
2. 扩容时元素位置要么在原位置，要么在原位置+旧容量（规律性强）
3. 使哈希分布更均匀
```

### 3.2 链表转红黑树的条件

```java
// 条件1：链表长度 >= 8（TREEIFY_THRESHOLD）
if (binCount >= TREEIFY_THRESHOLD - 1)
    treeifyBin(tab, hash);

// 条件2：数组长度 >= 64（MIN_TREEIFY_CAPACITY）
// 如果数组长度 < 64，优先扩容而不是转红黑树
if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
    resize();
else if ((e = tab[index]) != null) {
    // 转换为红黑树
}
```

**为什么是两个条件？**
```
链表长度 >= 8 且数组长度 >= 64 才转红黑树
如果数组长度 < 64，说明哈希冲突可能是数组太小导致的
优先扩容（resize）来减少冲突，而不是直接转红黑树
```

### 3.3 红黑树转链表的条件

```java
// 当红黑树节点数 <= 6（UNTREEIFY_THRESHOLD）时，转回链表
// 在 remove 或 resize 时触发
```

**为什么阈值是 8 和 6，而不是同一个数？**
```
避免频繁转换：
- 链表长度 8 → 转红黑树
- 红黑树节点 6 → 转链表
- 中间有个缓冲区间（7），避免在边界附近频繁转换
```

### 3.4 扩容机制（resize）

```java
final Node<K,V>[] resize() {
    Node<K,V>[] oldTab = table;
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    int oldThr = threshold;
    int newCap, newThr = 0;
    
    // 计算新容量和新阈值
    if (oldCap > 0) {
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 容量翻倍
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1;  // 阈值翻倍
    }
    
    // 创建新数组
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    table = newTab;
    
    // 数据迁移
    if (oldTab != null) {
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            if ((e = oldTab[j]) != null) {
                oldTab[j] = null;
                if (e.next == null)
                    // 单个节点直接重新计算位置
                    newTab[e.hash & (newCap - 1)] = e;
                else if (e instanceof TreeNode)
                    // 红黑树拆分
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                else {
                    // 链表拆分：lo 链和 hi 链
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        // 关键：e.hash & oldCap == 0 判断在低位还是高位
                        if ((e.hash & oldCap) == 0) {
                            // 低位链：保持原位置
                            if (loTail == null) loHead = e;
                            else loTail.next = e;
                            loTail = e;
                        } else {
                            // 高位链：原位置 + oldCap
                            if (hiTail == null) hiHead = e;
                            else hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    
                    // 低位链放到原位置
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    // 高位链放到 原位置 + oldCap
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

**扩容时元素位置变化规律：**
```
原容量 16，扩容到 32

原索引计算：hash & 15（取低 4 位）
新索引计算：hash & 31（取低 5 位）

关键：hash 的第 5 位（从右往左数）
- 如果第 5 位是 0：新索引 = 原索引（低位链）
- 如果第 5 位是 1：新索引 = 原索引 + 16（高位链）

判断方式：(e.hash & oldCap) == 0
- oldCap = 16 = 0b10000（第 5 位是 1）
- 如果 hash & 16 == 0，说明第 5 位是 0
```

---

## 四、完整流程图

```
put(key, value)
    │
    ▼
计算 hash = key.hashCode() ^ (key.hashCode() >>> 16)
    │
    ▼
table 是否为空？ ──是──► resize() 初始化
    │ 否
    ▼
计算索引 i = (n-1) & hash
    │
    ▼
tab[i] 是否为空？ ──是──► 直接插入新节点 ──► 检查扩容 ──► 结束
    │ 否
    ▼
第一个节点 key 相同？ ──是──► 覆盖 value，返回旧值
    │ 否
    ▼
是红黑树节点？ ──是──► 红黑树插入 ──► 检查扩容 ──► 结束
    │ 否
    ▼
遍历链表
    │
    ├──► 找到相同 key ──► 覆盖 value，返回旧值
    │
    └──► 到尾部 ──► 尾插法插入新节点
              │
              ▼
        链表长度 >= 8？ ──是──► 数组长度 >= 64？ ──是──► 转红黑树
              │                    │ 否
              │ 否                 ▼
              │                 resize() 扩容
              ▼
        检查扩容 ──► 结束
```

---

## 五、面试常见问题

### Q1: HashMap 的默认初始容量和负载因子是多少？

```java
// 默认初始容量：16
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

// 默认负载因子：0.75
static final float DEFAULT_LOAD_FACTOR = 0.75f;

// 扩容阈值 = 容量 × 负载因子
// 16 × 0.75 = 12，当 size > 12 时扩容
```

**为什么是 0.75？**
```
- 太小（如 0.5）：空间利用率低，频繁扩容
- 太大（如 1.0）：哈希冲突增多，查询效率下降
- 0.75 是时间和空间的折中
```

### Q2: HashMap 是线程安全的吗？

**不是！** 多线程环境下可能出现：
1. **数据覆盖**：两个线程同时 put，后一个覆盖前一个
2. **死循环**（JDK 7 及之前）：扩容时链表成环
3. **数据丢失**：扩容时数据迁移不完整

**解决方案：**
```java
// 方案1：Collections.synchronizedMap（全表锁，性能差）
Map<K, V> map = Collections.synchronizedMap(new HashMap<>());

// 方案2：ConcurrentHashMap（推荐，分段锁/CAS）
Map<K, V> map = new ConcurrentHashMap<>();
```

### Q3: JDK 7 和 JDK 8 的 HashMap 有什么区别？

| 特性 | JDK 7 | JDK 8 |
|-----|-------|-------|
| 数据结构 | 数组 + 链表 | 数组 + 链表 + 红黑树 |
| 插入方式 | 头插法 | 尾插法 |
| 扩容死循环 | 可能（头插法导致） | 不会（尾插法解决） |
| hash 计算 | 多次扰动 | 一次扰动（高16位异或） |

### Q4: 为什么 JDK 8 改用尾插法？

```
JDK 7 头插法问题：
- 扩容时链表反转，多线程下可能形成环形链表 → 死循环

JDK 8 尾插法：
- 保持链表原有顺序，避免成环
- 但 HashMap 仍然不是线程安全的（只是解决了死循环问题）
```

### Q5: 为什么 String、Integer 适合作为 HashMap 的 key？

```java
// 1. 不可变性：hashCode 缓存，不会变化
// 2. 正确实现 equals 和 hashCode
// 3. 减少哈希冲突

// 自定义对象作为 key 必须重写：
@Override
public boolean equals(Object o) { ... }

@Override
public int hashCode() { ... }
```

---

## 六、记忆口诀

> **"扰动计算索引，空位直接插；冲突链表树化，超阈值扩容"**
>
> - **扰动函数**：高16位异或，减少冲突
> - **索引计算**：(n-1) & hash，n 必须是 2 的幂
> - **树化条件**：链表 >= 8 且数组 >= 64
> - **扩容时机**：size > 容量 × 0.75
> - **扩容规则**：容量翻倍，元素位置 = 原位置 或 原位置+旧容量

---

*参考资料：*
- [HashMap 源码分析（JDK 8）](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
- [Java 集合框架面试题](https://pdai.tech/md/java/collection/java-map-HashMap&HashTable.html)
