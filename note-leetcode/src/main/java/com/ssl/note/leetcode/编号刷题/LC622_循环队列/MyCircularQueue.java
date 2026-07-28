package com.ssl.note.leetcode.编号刷题.LC622_循环队列;

public class MyCircularQueue {

  private int[] queue;

  private int l, r, size, limit;

  public MyCircularQueue(int k) {
    queue = new int[k];
    l = r = size = 0;
    limit = k;
  }

  public boolean enQueue(int value) {
    if (isFull()) {
      return false;
    }
    queue[r] = value;
//    r = (r == limit - 1) ? 0 : r + 1;
    r = (r + 1) % limit;
    size++;
    return true;
  }

  public boolean deQueue() {
    if (isEmpty()) {
      return false;
    }
//    l = (l == limit - 1) ? 0 : l + 1;
    l = (l + 1) % limit;
    size--;
    return true;
  }

  public int Front() {
    if (isEmpty()) {
      return -1;
    }
    return queue[l];
  }

  public int Rear() {
    if (isEmpty()) {
      return -1;
    }
    // 对尾元素：左闭右开[0,r)
    // 当r=0时，数组还不是空时，r=limit-1
    int last = (r == 0) ? limit - 1 : r - 1;
    return queue[last];
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == limit;
  }
}
