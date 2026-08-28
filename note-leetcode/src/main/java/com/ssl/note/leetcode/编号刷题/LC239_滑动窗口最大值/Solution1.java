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
    // 双端队列：存下标，队尾要移除使用双端队列
    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      // 1、队头维持窗口最大值，新加入的元素如果比队尾打，队尾依次移除
      while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
        deque.pollLast();
      }
      // 2、队尾加入新元素
      deque.offer(i);
      // 3、记录结果
      if (i >= k - 1) {
        // 4、更新左边界,再记录答案
        // 合法：i - k + 1 <= queue.peek() && queue.peek() <= i
        // 不合法：i-k+1 > queue.peek()
        if (i - k + 1 > deque.peek()) {
          deque.peek();
        }
        // 结果存的是值，不是下标
        res[i - k + 1] = nums[deque.peek()];
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
