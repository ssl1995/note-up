package com.ssl.note.leetcode.编号刷题.LC146_LRU缓存.Practive;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  static class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }

  private Map<Integer, Node> map;
  private int capacity;
  private Node head;
  private Node tail;

  public LRUCache(int capacity) {
    this.map = new HashMap<>();
    this.capacity = capacity;
    this.head = new Node(-1, -1);
    this.tail = new Node(-1, -1);
    this.head.next = tail;
    this.tail.pre = head;
  }

  public int get(int key) {
    if (!map.containsKey(key)) {
      return -1;
    }
    Node node = map.get(key);
    moveToTail(node);
    return node.value;
  }

  public void put(int key, int value) {
    if (!map.containsKey(key)) {
      if (capacity == map.size()) {
        deleteHead();
      }
      Node node = new Node(key, value);
      insertToTail(node);
      map.put(key, node);
      return;
    }
    Node node = map.get(key);
    moveToTail(node);
    node.value = value;
  }

  // 原子1:删除一个节点
  private void deleteNode(Node node) {
    if (node == null) {
      return;
    }
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }

  // 原子2：插入到队尾
  private void insertToTail(Node node) {
    if (node == null) {
      return;
    }
    tail.pre.next = node;
    node.pre = tail.pre;
    node.next = tail;
    tail.pre = node;
  }

  // 操作1：插入到队尾=最近使用
  private void moveToTail(Node node) {
    if (node == null) {
      return;
    }
    deleteNode(node);
    insertToTail(node);
  }

  // 操作2：删除对头
  private void deleteHead() {
    Node node = head.next;
    deleteNode(node);
    map.remove(node.key);
  }
}
