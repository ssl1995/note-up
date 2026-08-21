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
    // 双端队列，存下标，方便判断左边界是否过期
    // 规定：队头维持窗口最大值
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      // 1、队头维持窗口最大值，新加入的元素如果比队尾打，队尾依次移除
      while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
        deque.pollLast();
      }
      // 2、队尾加入新元素
      deque.offerLast(i);

      // 3、维持窗口左边界，当i来到某个位置，左边界是i-k+1
      int l = i - k + 1;
      // 队首最大值下标<左边界，需要移除
      if (deque.peekFirst() < l) {
        deque.pollFirst();
      }

      // 4、记录结果
      if (i >= k - 1) {
        res[i - k + 1] = nums[deque.peekFirst()];
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;
    // [3, 3, 5, 5, 6, 7]
    System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
  }
}
