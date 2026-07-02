package com.ssl.note.leetcode.编号刷题.LC460_LFU缓存;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

  // 底层Node数据结构
  static class Node {
    Node pre;
    Node next;

    int key;
    int value;
    int count;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
      this.count = 1;
    }
  }

  // 双向链表数据结构
  static class DeLinkedList {
    private final Node head;
    private final Node tail;

    public DeLinkedList() {
      this.head = new Node(-1, -1);
      this.tail = new Node(-1, -1);
      this.head.next = this.tail;
      this.tail.pre = this.head;
    }

    // 原子操作：链表是否为空（只有哨兵节点）
    public boolean isEmpty() {
      return head.next == tail;
    }

    // 原子操作：删除节点
    public void deleteNode(Node node) {
      if (node == null) {
        return;
      }
      node.pre.next = node.next;
      node.next.pre = node.pre;
    }

    // 原子操作：队尾插入节点（队尾标记最近使用）
    public void insertToTail(Node node) {
      if (node == null) {
        return;
      }
      tail.pre.next = node;
      node.pre = tail.pre;
      node.next = tail;
      tail.pre = node;
    }

    // 通用操作：移除队首（队首标记最久未使用）
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
  private final Integer capacity;
  private final Map<Integer, DeLinkedList> countMap;
  private int minCount;

  /**
   * 手写一个LFU最不经常使用缓存策略
   * 提供：get(key)，put(key, value)
   */
  public LFUCache(int capacity) {
    this.keyMap = new HashMap<>();
    this.countMap = new HashMap<>();
    this.capacity = capacity;
    this.minCount = 0;
  }

  public int get(int key) {
    // 不存在
    if (!keyMap.containsKey(key)) {
      return -1;
    }
    // 存在：增加使用频次
    Node node = keyMap.get(key);
    addCount(node);
    return node.value;
  }

  public void put(int key, int value) {
    // 容量为0，直接返回
    if (capacity == 0) {
      return;
    }
    // 不存在
    if (!keyMap.containsKey(key)) {
      // 容量已满，淘汰最不经常使用的
      if (capacity == keyMap.size()) {
        removeMinCountNode();
      }
      // 新增节点，频次为1
      Node node = new Node(key, value);
      keyMap.put(key, node);
      // 新加入的元素频次为1
      countMap.computeIfAbsent(1, k -> new DeLinkedList()).insertToTail(node);
      minCount = 1;
      return;
    }
    // 存在
    Node node = keyMap.get(key);
    node.value = value;
    addCount(node);
  }

  // 增加节点的使用频次
  private void addCount(Node node) {
    int oldCount = node.count;
    int newCount = oldCount + 1;

    // 当被移出的那个频次链表变空了，并且这个频次恰好就是当前最小频次时，最小频次+1
    DeLinkedList oldList = countMap.get(oldCount);
    oldList.deleteNode(node);

    // 如果旧频次链表被删空了，且这个频次刚好就是当前最小频次
    if (oldList.isEmpty() && oldCount == minCount) {
      minCount++;
    }

    // 加入新频次链表尾部
    node.count = newCount;
    countMap.computeIfAbsent(newCount, k -> new DeLinkedList()).insertToTail(node);
  }

  // 队满时，淘汰最小频次中最久未使用的节点
  private void removeMinCountNode() {
    DeLinkedList list = countMap.get(minCount);
    // 最小频次的列表中，越靠近队首，说明越久没被访问过
    Node removeNode = list.removeHead();
    if (removeNode != null) {
      keyMap.remove(removeNode.key);
    }
  }

  public static void main(String[] args) {
    LFUCache lfu = new LFUCache(2);
    lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
    lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
    System.out.println(lfu.get(1));      // 返回 1，cache=[1,2], cnt(1)=2, cnt(2)=1
    lfu.put(3, 3);   // 去除键 2，cache=[3,1], cnt(3)=1, cnt(1)=2
    System.out.println(lfu.get(2));      // 返回 -1（未找到）
    System.out.println(lfu.get(3));      // 返回 3，cache=[3,1], cnt(3)=2, cnt(1)=2
    lfu.put(4, 4);   // 去除键 1，cache=[4,3], cnt(4)=1, cnt(3)=2
    System.out.println(lfu.get(1));      // 返回 -1（未找到）
    System.out.println(lfu.get(3));      // 返回 3，cache=[3,4], cnt(3)=3, cnt(4)=1
    System.out.println(lfu.get(4));      // 返回 4，cache=[3,4], cnt(3)=3, cnt(4)=2
  }
}
