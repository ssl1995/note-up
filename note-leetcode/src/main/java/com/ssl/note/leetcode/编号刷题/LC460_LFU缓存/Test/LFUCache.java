package com.ssl.note.leetcode.编号刷题.LC460_LFU缓存.Test;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
  static class Node {
    private int key;
    private int value;
    private Node pre;
    private Node next;
    private int count;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
      this.count = 1;
    }
  }

  static class DeLinkedList {
    private Node head;
    private Node tail;

    DeLinkedList() {
      head = new Node(-1, -1);
      tail = new Node(-1, -1);
      head.next = tail;
      tail.pre = head;
    }

    private boolean isEmpty() {
      return head.next == tail;
    }

    private void deleteNode(Node node) {
      if (node == null) {
        return;
      }
      if (isEmpty()) {
        return;
      }
      node.pre.next = node.next;
      node.next.pre = node.pre;
    }

    private void insertToTail(Node node) {
      if (node == null) {
        return;
      }
      tail.pre.next = node;
      node.pre = tail.pre;
      node.next = tail;
      tail.pre = node;
    }

    private Node removeHead() {
      if (isEmpty()) {
        return null;
      }
      Node node = head.next;
      deleteNode(node);
      return node;
    }
  }

  private Map<Integer, Node> keyMap;
  private Map<Integer, DeLinkedList> countMap;
  private int minCount;
  private int capacity;

  public LFUCache(int capacity) {
    this.keyMap = new HashMap<>();
    this.countMap = new HashMap<>();
    this.minCount = 0;
    this.capacity = capacity;
  }

  public int get(int key) {
    if (!keyMap.containsKey(key)) {
      return -1;
    }
    Node node = keyMap.get(key);
    addCount(node);
    return node.value;
  }

  public void put(int key, int value) {
    if (capacity == 0) {
      return;
    }
    if (!keyMap.containsKey(key)) {
      if (capacity == keyMap.size()) {
        removeMinCountNode();
      }
      // 新增节点，频次=1
      Node node = new Node(key, value);
      keyMap.put(key, node);
      countMap.computeIfAbsent(1, k -> new DeLinkedList()).insertToTail(node);
      minCount = 1;
      return;
    }
    Node node = keyMap.get(key);
    node.value = value;
    addCount(node);
  }

  private void addCount(Node node) {
    int oldCount = node.count;
    int newCount = oldCount + 1;

    DeLinkedList oldList = countMap.get(oldCount);
    oldList.deleteNode(node);

    if (oldList.isEmpty() && minCount == oldCount) {
      minCount++;
    }

    node.count = newCount;
    countMap.computeIfAbsent(newCount, k -> new DeLinkedList()).insertToTail(node);
  }

  private void removeMinCountNode() {
    DeLinkedList minCountList = countMap.get(minCount);
    Node delete = minCountList.removeHead();
    if (delete != null) {
      keyMap.remove(delete.key);
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
