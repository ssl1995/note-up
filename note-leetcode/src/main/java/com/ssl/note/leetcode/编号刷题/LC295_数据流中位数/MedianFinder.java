package com.ssl.note.leetcode.编号刷题.LC295_数据流中位数;

import java.util.PriorityQueue;

public class MedianFinder {

  private PriorityQueue<Integer> maxHeap;// 大根堆：小的一半放这里，堆顶是小的一半里的最大值
  private PriorityQueue<Integer> minHeap;// 小根堆：大的一半放这里，堆顶是大的一半里的最小值

  public MedianFinder() {
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    // 1、只考虑入那个根堆
    if (maxHeap.isEmpty() || num < maxHeap.peek()) {
      maxHeap.offer(num);
    } else {
      minHeap.offer(num);
    }
    // 平衡堆
    balance();
  }

  private void balance() {
    /**
     * 合法状态：
     * 奇数：中位数在中间，一堆比另一堆多一个，规定maxHeap比minHeap多一个是合法状态
     * 偶数：中位数在中间两边，两个堆数量相等合法
     */
    if (maxHeap.size() == minHeap.size() || maxHeap.size() == minHeap.size() + 1) {
      return;
    }
    // 长度差超过1，谁大就移动谁
    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.offer(maxHeap.poll());
    } else {
      maxHeap.offer(minHeap.poll());
    }
  }

  public double findMedian() {
    if (maxHeap.size() == minHeap.size()) {
      return (maxHeap.peek() + minHeap.peek()) / 2d;
    } else {
      return (double) maxHeap.peek();
    }
  }
}
