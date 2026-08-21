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
   * 使用堆结构，时间复杂度会超时，可以明确思路
   */
  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    int[] res = new int[n - k + 1];
    int index = 0;
    // 使用堆结构，时间复杂度会超时，可以明确思路
    PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);

    for (int l, r = 0; r < nums.length; r++) {
      if (r < k) {
        heap.offer(nums[r]);
        continue;
      }

      res[index++] = heap.peek();

      l = r - k;
      // n*2logn会超时
      heap.remove(nums[l]);
      heap.offer(nums[r]);
    }
    res[index] = heap.peek();
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
