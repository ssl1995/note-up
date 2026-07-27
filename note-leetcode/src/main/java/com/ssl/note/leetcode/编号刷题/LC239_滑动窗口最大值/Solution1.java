package com.ssl.note.leetcode.编号刷题.LC239_滑动窗口最大值;

import java.util.PriorityQueue;

public class Solution1 {

  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    // 大顶堆：存储 (值, 下标)，按值降序
    PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    // 初始化第一个窗口
    for (int i = 0; i < k; i++) {
      heap.offer(new int[]{nums[i], i});
    }
    int[] res = new int[n - k + 1];
    // 初始化第一个元素
    res[0] = heap.peek()[0];
    for (int i = k; i < n; i++) {
      heap.offer(new int[]{nums[i], i});
      // 最大值元素下标不在窗口内，就移除
      while (heap.peek()[1] <= i - k) {
        heap.poll();
      }
      res[i - k + 1] = heap.peek()[0];
    }
    return res;
  }
}
