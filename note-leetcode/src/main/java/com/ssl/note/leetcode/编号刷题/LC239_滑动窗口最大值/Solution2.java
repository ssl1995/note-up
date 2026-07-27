package com.ssl.note.leetcode.编号刷题.LC239_滑动窗口最大值;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author SongShengLin
 * @date 2022/6/19 12:06
 * @description
 */
public class Solution2 {

  /**
   * 滑动窗口最大值
   */
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length < k || k < 1) {
      return new int[]{};
    }
    int n = nums.length;
    int[] res = new int[n - k + 1];
    // 存坐标
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      // 1、新加入的元素大，就要移除
      while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
        deque.pollLast();
      }
      // 2、加入新元素
      deque.offerLast(i);

      // 3、维持窗口：i-k+1<=对头<=i
      // i-k+1:i位置前面k个长度最小坐标
      if (deque.peekFirst() < i - k + 1) {
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
    Solution2 solution = new Solution2();
    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;
    // [3, 3, 5, 5, 6, 7]
    System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
  }
}
