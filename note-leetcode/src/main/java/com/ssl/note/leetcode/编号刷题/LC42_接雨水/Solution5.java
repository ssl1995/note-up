package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution5 {
  /**
   * 接雨水
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * 输出：6
   * 解法三：单调递减栈
   */
  public int trap(int[] height) {
    if (height == null || height.length <= 1) {
      return 0;
    }
    int water = 0;
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < height.length; i++) {
      // 单调递减栈
      while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
        // [2,1,3]，当遍历到nums[2]=3时
        int bottom = stack.pop();
        // 没有左边界，则无法接雨水
        if (stack.isEmpty()) {
          break;
        }
        // bottom左边第一个比它大的
        int left = stack.peek();

        // 宽度：2,1,3行程的凹槽宽度是1
        int width = i - left - 1;
        // 高度：按层计算，木桶原理，bottom的左右两边找最小
        // 底部可能不是平地（高度可能不是0），所以要减去底部的高度
        int h = Math.min(height[left], height[i]) - height[bottom];

        // 加上当前列能接的水量
        water += width * h;
      }
      stack.push(i);
    }

    return water;
  }

  public static void main(String[] args) {
    Solution5 solution = new Solution5();
    int[] nums = {2, 1, 3};
    System.out.println(solution.trap(nums));
  }
}
