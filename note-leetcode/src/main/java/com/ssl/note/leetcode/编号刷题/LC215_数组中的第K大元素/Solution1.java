package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1 {

  // 小根堆的写法
  public int findKthLargest(int[] nums, int k) {
    // 默认是小根堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 大根堆
//    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
//    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    for (int num : nums) {
      minHeap.offer(num);
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }
    return minHeap.peek();
  }
}
