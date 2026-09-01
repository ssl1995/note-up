# Map 并发修改异常（ConcurrentModificationException）详解

## 一、什么是并发修改异常？

**ConcurrentModificationException** 是 Java 集合框架中的 **fail-fast（快速失败）** 机制抛出的异常。当迭代器在遍历集合的过程中，检测到集合被结构性修改（如添加、删除元素），就会立即抛出此异常。

> **注意**：这个异常是 Java 的**侦测机制**，不能保证在所有并发修改情况下都抛出，**不能依赖它来做并发控制**。

---

## 二、Map 遍历的正确与错误写法

### 2.1 错误写法（会抛出 ConcurrentModificationException）

```java
import java.util.HashMap;
import java.util.Map;

public class MapConcurrentModificationDemo {
    
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        
        // ❌ 错误写法1：使用 for-each 循环删除元素
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if ("B".equals(entry.getKey())) {
                map.remove(entry.getKey());  // 抛出 ConcurrentModificationException
            }
        }
        
        // ❌ 错误写法2：使用 keySet 遍历并删除
        for (String key : map.keySet()) {
            if ("C".equals(key)) {
                map.remove(key);  // 抛出 ConcurrentModificationException
            }
        }
    }
}
```

### 2.2 正确写法（使用迭代器）

```java
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapIteratorCorrectDemo {
    
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        
        // ✅ 正确写法1：使用 EntrySet 的迭代器删除
        Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<String, Integer> entry = entryIterator.next();
            if ("B".equals(entry.getKey())) {
                entryIterator.remove();  // 使用迭代器的 remove 方法，安全删除
                System.out.println("删除了: " + entry.getKey());
            }
        }
        System.out.println("删除后: " + map);  // {A=1, C=3, D=4}
        
        // ✅ 正确写法2：使用 KeySet 的迭代器删除
        Iterator<String> keyIterator = map.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            if ("C".equals(key)) {
                keyIterator.remove();  // 安全删除
                System.out.println("删除了: " + key);
            }
        }
        System.out.println("再次删除后: " + map);  // {A=1, D=4}
    }
}
```

---

## 三、Map 遍历的完整示例（包含所有方式）

```java
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTraversalCompleteGuide {
    
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);
        map.put("Date", 4);
        
        System.out.println("=== 1. 使用 EntrySet 迭代器（推荐，可安全删除）===");
        Iterator<Map.Entry<String, Integer>> entryIter = map.entrySet().iterator();
        while (entryIter.hasNext()) {
            Map.Entry<String, Integer> entry = entryIter.next();
            System.out.println(entry.getKey() + " = " + entry.getValue());
            // 可以安全删除
            // entryIter.remove();
        }
        
        System.out.println("\n=== 2. 使用 KeySet 迭代器 ===");
        Iterator<String> keyIter = map.keySet().iterator();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            System.out.println(key + " = " + map.get(key));
        }
        
        System.out.println("\n=== 3. 使用 for-each + EntrySet（遍历，不可删除）===");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        
        System.out.println("\n=== 4. 使用 for-each + KeySet ===");
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }
        
        System.out.println("\n=== 5. Java 8+ forEach + Lambda ===");
        map.forEach((key, value) -> System.out.println(key + " = " + value));
        
        System.out.println("\n=== 6. Java 8+ Stream API ===");
        map.entrySet().stream()
            .filter(entry -> entry.getValue() > 2)
            .forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
    }
}
```

---

## 四、并发环境下的安全写法

### 4.1 使用 ConcurrentHashMap（推荐）

```java
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    
    public static void main(String[] args) {
        // ConcurrentHashMap 是线程安全的，支持并发修改
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        
        // ✅ 在遍历时删除元素是安全的（弱一致性迭代器）
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if ("B".equals(entry.getKey())) {
                map.remove(entry.getKey());  // 不会抛出异常
                System.out.println("安全删除了: " + entry.getKey());
            }
        }
        
        System.out.println("删除后: " + map);
        
        // 使用迭代器也是安全的
        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            // ConcurrentHashMap 的迭代器是弱一致性的，不保证看到最新修改
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
```

### 4.2 使用 Collections.synchronizedMap

```java
import java.util.*;

public class SynchronizedMapDemo {
    
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        
        // 包装成同步 Map
        Map<String, Integer> syncMap = Collections.synchronizedMap(map);
        
        // ⚠️ 遍历时仍需手动同步
        synchronized (syncMap) {
            Iterator<Map.Entry<String, Integer>> iter = syncMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Integer> entry = iter.next();
                if ("B".equals(entry.getKey())) {
                    iter.remove();  // 在同步块中使用迭代器删除是安全的
                }
            }
        }
        
        System.out.println("删除后: " + syncMap);
    }
}
```

---

## 五、核心知识点总结

### 5.1 Fail-Fast 机制原理

```
┌─────────────────────────────────────────────────────────┐
│                    Fail-Fast 原理                        │
├─────────────────────────────────────────────────────────┤
│  1. 集合内部维护一个 modCount 变量（修改计数）              │
│  2. 创建迭代器时，记录 expectedModCount = modCount        │
│  3. 每次调用 next() 时检查：                              │
│     if (modCount != expectedModCount)                   │
│         throw new ConcurrentModificationException();    │
│  4. 结构性修改（add/remove）会使 modCount++               │
└─────────────────────────────────────────────────────────┘
```

### 5.2 关键对比表

| 遍历方式 | 是否可删除 | 线程安全 | 性能 | 适用场景 |
|---------|-----------|---------|------|---------|
| for-each + EntrySet | ❌ 会抛异常 | ❌ | 高 | 只读遍历 |
| Iterator + EntrySet | ✅ 安全删除 | ❌ | 高 | 需要删除元素 |
| for-each + KeySet | ❌ 会抛异常 | ❌ | 中 | 只读遍历 |
| Iterator + KeySet | ✅ 安全删除 | ❌ | 中 | 需要删除元素 |
| ConcurrentHashMap | ✅ 安全删除 | ✅ | 高 | 并发环境 |
| synchronizedMap | ✅ 需同步块 | ✅ | 低 | 并发环境（ legacy）|

### 5.3 常见面试问题

**Q1: 为什么 for-each 循环删除元素会抛异常？**

> for-each 底层使用的是迭代器，但调用的是集合的 `remove()` 方法而不是迭代器的 `remove()` 方法。集合的 `remove()` 会修改 `modCount`，但不会更新迭代器的 `expectedModCount`，导致下次 `next()` 时检测失败。

**Q2: Iterator.remove() 和 Map.remove() 有什么区别？**

> - `Iterator.remove()`：会同时更新 `modCount` 和 `expectedModCount`，保持同步
> - `Map.remove()`：只更新 `modCount`，导致迭代器状态不一致

**Q3: ConcurrentHashMap 为什么不会抛 ConcurrentModificationException？**

> ConcurrentHashMap 使用**弱一致性迭代器**（Weakly Consistent Iterator），它不保证看到遍历开始后的所有修改，因此不需要 fail-fast 机制。这是用一致性换取并发性能的设计。

**Q4: 如何在多线程环境下安全地遍历并修改 Map？**

> 三种方案：
> 1. **ConcurrentHashMap**（推荐）：天然支持并发修改
> 2. **Collections.synchronizedMap + 外部同步**：`synchronized(map) { ... }`
> 3. **先复制再遍历**：`new HashMap<>(map).entrySet().iterator()`

---

## 六、最佳实践代码模板

```java
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapBestPractices {
    
    // ========== 单线程环境 ==========
    
    /**
     * 单线程下安全删除 Map 元素的标准写法
     */
    public static void safeRemoveSingleThread(Map<String, Integer> map, String keyToRemove) {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            if (keyToRemove.equals(entry.getKey())) {
                iterator.remove();
            }
        }
    }
    
    /**
     * 单线程下按条件删除（Java 8+ 更简洁）
     */
    public static void safeRemoveIf(Map<String, Integer> map, int threshold) {
        map.entrySet().removeIf(entry -> entry.getValue() > threshold);
    }
    
    // ========== 多线程环境 ==========
    
    /**
     * 多线程下使用 ConcurrentHashMap
     */
    public static void concurrentRemove(ConcurrentHashMap<String, Integer> map, String keyToRemove) {
        // 直接删除，无需额外同步
        map.remove(keyToRemove);
        
        // 遍历时删除也是安全的
        map.entrySet().removeIf(entry -> entry.getValue() < 0);
    }
    
    /**
     * 多线程下使用同步 Map（需要外部同步）
     */
    public static void synchronizedMapRemove(Map<String, Integer> map, String keyToRemove) {
        Map<String, Integer> syncMap = Collections.synchronizedMap(map);
        synchronized (syncMap) {
            syncMap.entrySet().removeIf(entry -> keyToRemove.equals(entry.getKey()));
        }
    }
    
    // ========== 防御性编程 ==========
    
    /**
     * 防御性复制：在遍历前创建副本
     */
    public static void defensiveCopyIteration(Map<String, Integer> map) {
        // 创建副本，避免并发修改
        Map<String, Integer> copy = new HashMap<>(map);
        for (Map.Entry<String, Integer> entry : copy.entrySet()) {
            // 处理逻辑，可以安全地修改原 map
            if (entry.getValue() < 0) {
                map.remove(entry.getKey());
            }
        }
    }
}
```

---

## 七、记忆口诀

> **"迭代删除用迭代器，并发环境用 Concurrent"**
> 
> - 单线程删除 → `iterator.remove()`
> - 多线程环境 → `ConcurrentHashMap`
> - 只读遍历 → `for-each` 或 `stream()`
> - 需要同步 → `synchronized` 块包裹

---

*参考资料：*
- [Java 集合框架官方文档](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
- [ConcurrentHashMap 源码分析](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html)
