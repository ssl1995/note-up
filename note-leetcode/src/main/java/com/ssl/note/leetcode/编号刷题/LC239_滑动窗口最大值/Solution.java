package com.ssl.note.leetcode.编号刷题.LC239_滑动窗口最大值;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author SongShengLin
 * @date 2022/6/19 12:06
 * @description
 */
public class Solution {

  /**
   * 滑动窗口最大值
   */
  public int[] maxSlidingWindow(int[] nums, int k) {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    for (int i = 0; i < k; i++) {
      maxHeap.offer(nums[i]);
    }
    int n = nums.length;
    int[] res = new int[n - k + 1];
    // i位置表示前k个位置的最大值，末尾或者第一个数需要单独处理
    res[0] = maxHeap.peek();

    for (int i = k; i < n; i++) {
      maxHeap.offer(nums[i]);
      // AC会超时,remove+offer操作时间复杂度O(2logK)
      maxHeap.remove(nums[i - k]);

      res[i - k + 1] = maxHeap.peek();
    }
    // 如果没有初始化res[0]，这里就得单独赋值末尾元素
//    res[n-k]=maxHeap.peek();
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;
    // [3, 3, 5, 5, 6, 7]
    System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
  }
}
