package com.ssl.note.leetcode.编号刷题.LC146_LRU缓存;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

  // 底层Node数据结构
  static class Node {
    // 双指针是public状态
    public Node pre;
    public Node next;

    private final int key;
    private int value;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  // 双向链表数据结构
  private final Map<Integer, Node> map;
  private final Node head;
  private final Node tail;
  // capacity与map.size做满判断
  private final Integer capacity;

  /**
   * 手写一个LRU最近最少使用缓存策略
   * 提供：get(key)，put(key, value)
   */
  public LRUCache(int capacity) {
    this.head = new Node(-1, -1);
    this.tail = new Node(-1, -1);
    this.map = new HashMap<>();
    this.capacity = capacity;

    this.head.next = this.tail;
    this.tail.pre = this.head;
  }

  public int get(int key) {
    // 不存在
    if (!map.containsKey(key)) {
      return -1;
    }
    // 存在
    Node node = map.get(key);
    moveToTail(node);
    // 返回的是值
    return node.value;
  }

  public void put(int key, int value) {
    // 不存在
    if (!map.containsKey(key)) {
      if (capacity == map.size()) {
        removeHead();
      }
      // 新增节点
      Node node = new Node(key, value);
      insertToTail(node);
      // 更新map
      map.put(key, node);
      return;
    }
    // 存在
    Node node = map.get(key);
    // 存在要更新新的value
    node.value = value;
    moveToTail(node);
  }

  // 原子操作1：删除=删除某个节点
  private void deleteNode(Node node) {
    if (node == null) {
      return;
    }
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }

  // 原子操作2：插入=队尾插入节点
  private void insertToTail(Node node) {
    if (node == null) {
      return;
    }
    tail.pre.next = node;
    node.pre = tail.pre;
    node.next = tail;
    tail.pre = node;
  }

  // 通用操作：LRU特性-移动到队尾（队尾标记最近使用）
  private void moveToTail(Node node) {
    if (node == null) {
      return;
    }
    deleteNode(node);
    insertToTail(node);
  }

  // 通用操作：LRU特性-移除队首（队首标记最久未使用）
  private void removeHead() {
    Node removeHead = head.next;
    deleteNode(removeHead);
    // map的删除remove(key)，不是removeKey(key)
    map.remove(removeHead.key);
  }

  public static void main(String[] args) {
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1);// 缓存是 {1=1}
    lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
    System.out.println(lRUCache.get(1));    // 返回 1
    lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
    lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
    System.out.println(lRUCache.get(3));    // 返回 3
    System.out.println(lRUCache.get(4));    // 返回 4
  }

}