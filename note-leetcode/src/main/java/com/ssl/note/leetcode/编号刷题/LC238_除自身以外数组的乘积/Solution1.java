package com.ssl.note.leetcode.编号刷题.LC238_除自身以外数组的乘积;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/2/22 9:12 PM
 * @description
 */
public class Solution1 {
  /**
   * 除自身以外数组的乘积
   * 给你一个整数数组 nums，返回 数组 answer ，
   * 其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
   * 输入: nums = [1,2,3,4]
   * 输出: [24,12,8,6]
   */
  public int[] productExceptSelf(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[]{};
    }
    int n = nums.length;
    int[] res = new int[n];
    // 从左到右
    for (int i = 0, leftSum = 1; i < n; i++) {
      leftSum *= nums[i];
      res[i] = leftSum;
    }
    // 从右到左，i==0特判
    for (int i = n - 1, rightSum = 1; i >= 0; i--) {
      int pre = i == 0 ? 1 : res[i - 1];
      // 先res再更新sum：i位置的结果，sum不能包含自己
      res[i] = pre * rightSum;
      rightSum *= nums[i];
    }

    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {1, 2, 3, 4};
    System.out.println(Arrays.toString(solution.productExceptSelf(nums)));
  }
}
