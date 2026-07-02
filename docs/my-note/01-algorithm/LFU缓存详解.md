# LFU 缓存详解

## 一、问题描述

LFU（Least Frequently Used，最不经常使用）缓存策略要求设计一个支持 `get` 和 `put` 操作的数据结构：

- `LFUCache(int capacity)`：初始化缓存容量。
- `int get(int key)`：如果键存在则返回值，否则返回 `-1`。访问会累加该键的使用次数。
- `void put(int key, int value)`：如果键存在则更新值并累加次数；如果键不存在则插入。当缓存满时，淘汰使用次数最少的键；如果次数相同，则淘汰其中最久未使用的键。

函数 `get` 和 `put` 必须以 **O(1)** 的平均时间复杂度运行。

## 二、从 LRU 到 LFU 的思维迁移

我们已经实现的 LRU 使用了 **HashMap + 双向链表** 的组合：

- **HashMap**：存储 `key -> Node` 的映射，保证 O(1) 查找。
- **双向链表**：按访问时间维护节点顺序，队头是最久未使用，队尾是最近使用。

LRU 只需要维护一条时间线，而 LFU 需要维护两条规则：

1. **使用频次**：淘汰时使用次数最少的节点。
2. **最近使用**：当频次相同时，淘汰最久未使用的节点。

直观想法是：**能否也像 LRU 一样用一条双向链表来维护顺序？** 答案是：如果直接按频次排序，每次访问节点后都要调整位置，虽然能做到 O(1) 调整，但更清晰的做法是——**为每个频次都维护一条双向链表**。

每条链表内部与 LRU 完全一致：队尾是最近访问过的节点，队头是最久未访问的节点。这样，淘汰时先找到最小频次，再移除对应链表的队首即可。

## 三、核心数据结构

沿用 LRU 中的 `Node` 设计，增加一个 `freq` 字段表示使用次数：

```java
static class Node {
    Node pre;
    Node next;

    int key;
    int value;
    int freq;  // 使用次数

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.freq = 1;  // 新节点默认频次为 1
    }
}
```

封装一个 `DoublyLinkedList`，结构与 LRU 中的双向链表完全相同，包含 `head` 和 `tail` 哨兵节点：

```java
static class DoublyLinkedList {
    private final Node head;
    private final Node tail;

    // 删除节点、队尾插入、移动到队尾、移除队首
}
```

`LFUCache` 中使用三张表：

1. `keyMap: key -> Node`：O(1) 定位节点。
2. `freqMap: freq -> DoublyLinkedList`：O(1) 定位某个频次对应的节点队列。
3. `minFreq`：记录当前最小使用频次，淘汰时直接定位。

## 四、算法步骤

### get(key)

1. 若 `keyMap` 中不存在，返回 `-1`。
2. 取出节点，调用 `increaseFreq(node)` 增加其使用次数。
3. 返回节点值。

### put(key, value)

1. 容量为 0 直接返回。
2. 若 key 已存在：更新值，调用 `increaseFreq(node)`。
3. 若 key 不存在：
   - 缓存已满时，调用 `removeLFUNode()` 淘汰最小频次链表的队首节点。
   - 新建节点，频次为 1，加入 `keyMap` 和 `freqMap[1]` 的队尾。
   - 更新 `minFreq = 1`。

### increaseFreq(node)

1. 从旧频次链表中删除该节点。
2. 如果旧频次链表变空，且旧频次等于 `minFreq`，则 `minFreq++`。
3. 节点 `freq++`，加入新频次链表的队尾。

### removeLFUNode()

1. 取出 `freqMap[minFreq]` 链表。
2. 移除队首节点，并从 `keyMap` 中删除对应 key。

## 五、代码实现

```java
import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    static class Node {
        Node pre;
        Node next;

        int key;
        int value;
        int freq;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    static class DoublyLinkedList {
        private final Node head;
        private final Node tail;

        public DoublyLinkedList() {
            this.head = new Node(-1, -1);
            this.tail = new Node(-1, -1);
            this.head.next = this.tail;
            this.tail.pre = this.head;
        }

        public boolean isEmpty() {
            return head.next == tail;
        }

        public void deleteNode(Node node) {
            if (node == null) {
                return;
            }
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        public void insertToTail(Node node) {
            if (node == null) {
                return;
            }
            tail.pre.next = node;
            node.pre = tail.pre;
            node.next = tail;
            tail.pre = node;
        }

        public void moveToTail(Node node) {
            if (node == null) {
                return;
            }
            deleteNode(node);
            insertToTail(node);
        }

        public Node removeHead() {
            if (isEmpty()) {
                return null;
            }
            Node removeHead = head.next;
            deleteNode(removeHead);
            return removeHead;
        }
    }

    private final Map<Integer, Node> keyMap;
    private final Map<Integer, DoublyLinkedList> freqMap;
    private final Integer capacity;
    private int minFreq;

    public LFUCache(int capacity) {
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!keyMap.containsKey(key)) {
            return -1;
        }
        Node node = keyMap.get(key);
        increaseFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.value = value;
            increaseFreq(node);
            return;
        }
        if (capacity == keyMap.size()) {
            removeLFUNode();
        }
        Node node = new Node(key, value);
        keyMap.put(key, node);
        freqMap.computeIfAbsent(1, k -> new DoublyLinkedList()).insertToTail(node);
        minFreq = 1;
    }

    private void increaseFreq(Node node) {
        int oldFreq = node.freq;
        int newFreq = oldFreq + 1;

        DoublyLinkedList oldList = freqMap.get(oldFreq);
        oldList.deleteNode(node);

        if (oldList.isEmpty() && oldFreq == minFreq) {
            minFreq++;
        }

        node.freq = newFreq;
        freqMap.computeIfAbsent(newFreq, k -> new DoublyLinkedList()).insertToTail(node);
    }

    private void removeLFUNode() {
        DoublyLinkedList list = freqMap.get(minFreq);
        Node removeNode = list.removeHead();
        if (removeNode != null) {
            keyMap.remove(removeNode.key);
        }
    }
}
```

## 六、示例解析

以 `capacity = 2` 为例：

| 操作 | keyMap | freqMap | minFreq | 说明 |
|------|--------|---------|---------|------|
| put(1,1) | {1} | 1:[1] | 1 | 新节点频次为 1 |
| put(2,2) | {1,2} | 1:[1,2] | 1 | 新节点加入频次 1 链表尾部 |
| get(1) | {1,2} | 1:[2], 2:[1] | 2 | 1 的频次提升到 2 |
| put(3,3) | {1,3} | 1:[3], 2:[1] | 1 | 容量满，淘汰 minFreq=1 链表的队首 2 |
| get(2) | {1,3} | 1:[3], 2:[1] | 1 | 2 不存在，返回 -1 |
| get(3) | {1,3} | 2:[1,3] | 2 | 3 的频次提升到 2，minFreq 随之变为 2 |
| put(4,4) | {3,4} | 2:[3], 1:[4] | 1 | 容量满，淘汰 minFreq=2 链表的队首 1 |
| get(1) | {3,4} | 2:[3], 1:[4] | 1 | 1 不存在，返回 -1 |
| get(3) | {3,4} | 1:[4], 3:[3] | 1 | 3 的频次提升到 3 |
| get(4) | {3,4} | 3:[3], 2:[4] | 2 | 4 的频次提升到 2 |

## 七、复杂度分析

- **时间复杂度**：`get` 和 `put` 均为 **O(1)**。所有操作都基于 HashMap 的查找和双向链表的删除/插入。
- **空间复杂度**：**O(capacity)**，最多存储 `capacity` 个节点，以及对应数量的频次链表。

## 八、LRU 与 LFU 的对比

| 维度 | LRU | LFU |
|------|-----|-----|
| 淘汰依据 | 最近使用时间 | 使用频次 |
| 核心结构 | 一条双向链表 + HashMap | 多条双向链表（按频次分桶） + HashMap |
| 节点字段 | key、value、pre、next | key、value、freq、pre、next |
| 访问时操作 | 移动到队尾 | 移动到更高频次的链表队尾 |
| 淘汰时操作 | 移除队首 | 找到最小频次，移除对应链表队首 |
| 额外变量 | 无 | `minFreq` 记录最小频次 |

## 九、总结

LFU 可以看作是 LRU 的"多频次版本"：

- LRU 用一条双向链表维护访问时间顺序；
- LFU 用多条双向链表，每条链表代表同一个使用频次，内部仍然按访问时间排序。

通过 **HashMap + 多双向链表 + minFreq** 的组合，我们既保留了 LRU 实现中的原子操作思维（删除、插入、移动、移除队首），又将时间复杂度和空间复杂度都控制在理想的范围内。

**关键要点**：

- 每个节点维护 `freq` 字段。
- 相同频次的节点放在同一条双向链表中。
- 访问节点时，从旧频次链表移除，加入新频次链表尾部。
- 淘汰时根据 `minFreq` 直接定位需要删除的链表，再移除队首节点。
