package com.ssl.note.leetcode.编号刷题.LC295_数据流中位数;

import java.util.PriorityQueue;

public class MedianFinder {

  /**
   * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
   * 例如 arr = [2,3,4] 的中位数是 3 。
   * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
   * 实现 MedianFinder 类:
   * MedianFinder() 初始化 MedianFinder 对象。
   * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
   * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
   */
  private PriorityQueue<Integer> maxHeap;// 大根堆：小的一半放这里，堆顶是小的一半里的最大值
  private PriorityQueue<Integer> minHeap;// 小根堆：大的一半放这里，堆顶是大的一半里的最小值

  public MedianFinder() {
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
//    maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    // 1、只考虑入那个根堆
    if (maxHeap.isEmpty() || num < maxHeap.peek()) {
      maxHeap.offer(num);
    } else {
      minHeap.offer(num);
    }
    // 平衡堆：大根堆 == 小根堆，或者大根堆多 1
    balance1();
//    balance2();
  }

  private void balance1() {
    if (maxHeap.size() == minHeap.size() || maxHeap.size() == minHeap.size() + 1) {
      return;
    }
    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.offer(maxHeap.poll());
    } else {
      maxHeap.offer(minHeap.poll());
    }
  }

  private void balance2() {
    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.offer(maxHeap.poll());
    } else if (maxHeap.size() < minHeap.size()) {
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

  public static void main(String[] args) {
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);
    medianFinder.addNum(2);
    System.out.println(medianFinder.findMedian());
    medianFinder.addNum(3);
    System.out.println(medianFinder.findMedian());
  }
}
