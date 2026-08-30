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
  private Node head;
  private Node tail;
  private int cap;

  public LRUCache(int capacity) {
    map = new HashMap<>();
    head = new Node(-1, -1);
    tail = new Node(-1, -1);
    cap = capacity;
    head.next = tail;
    tail.pre = head;
  }

  public int get(int key) {
    if (cap == 0) {
      return -1;
    }
    if (!map.containsKey(key)) {
      return -1;
    }
    Node node = map.get(key);
    moveToTail(node);
    return node.value;
  }

  public void put(int key, int value) {
    if (!map.containsKey(key)) {
      if (cap == map.size()) {
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

  private void insertToTail(Node node) {
    if (node == null) {
      return;
    }
    node.pre = tail.pre;
    tail.pre.next = node;
    node.next = tail;
    tail.pre = node;
  }

  private void deleteNode(Node node) {
    if (node == null) {
      return;
    }
    node.pre.next = node.next;
    node.next.pre = node.pre;
  }

  private void deleteHead() {
    Node node = head.next;
    if (node == null) {
      return;
    }
    deleteNode(node);
    map.remove(node.key);
  }

  private void moveToTail(Node node) {
    deleteNode(node);
    insertToTail(node);
  }
}
