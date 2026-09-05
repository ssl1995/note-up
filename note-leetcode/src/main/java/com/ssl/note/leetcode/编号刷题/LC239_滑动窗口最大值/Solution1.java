package com.ssl.note.leetcode.编号刷题.LC239_滑动窗口最大值;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author SongShengLin
 * @date 2022/6/19 12:06
 * @description
 */
public class Solution1 {

  /**
   * 滑动窗口最大值
   */
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length < k || k < 1) {
      return new int[]{};
    }
    int n = nums.length;
    int[] res = new int[n - k + 1];
    // 双端队列，队存窗口内数组下标
    Deque<Integer> queue = new ArrayDeque<>();
    for (int r = 0; r < n; r++) {
      // 窗口队头位置最大值下标
      while (!queue.isEmpty() && nums[r] >= nums[queue.peekLast()]) {
        queue.pollLast();
      }
      queue.offerLast(r);
      // 考虑移动左窗口
      if (r >= k) {
        if (queue.peekFirst() < r - k + 1) {
          queue.pollFirst();
        }
      }
      // 开始记录答案
      if (r >= k - 1) {
        res[r - k + 1] = nums[queue.peekFirst()];
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {7, 2, 4};
    int k = 2;
    // [3, 3, 5, 5, 6, 7]
    System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
  }
}
