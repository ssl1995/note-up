package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.PriorityQueue;

public class Solution {

  /**
   * 数组中第k大的数
   * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   * 输出: 4
   */
  public int findKthLargest(int[] nums, int k) {
    // 小根堆：默认无参构造器就是小根堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 大根堆：记住写法
//    PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> b - a);
//    PriorityQueue<Integer> maxHeap1 = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
//    PriorityQueue<Integer> maxHeap3 = new PriorityQueue<>(Comparator.reverseOrder());
    for (int num : nums) {
      minHeap.offer(num);
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }
    return minHeap.peek();
  }
}
