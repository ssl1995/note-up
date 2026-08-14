package com.ssl.note.leetcode.编号刷题.LC152_乘积最大子数组;

/**
 * @author SongShengLin
 * @date 2022/3/2 8:56 AM
 * @description
 */
public class Solution {

  /**
   * 乘积最大子数组
   * 注意：子数组指数组的连续子序列
   * 输入: nums = [2,3,-2,4]
   * 输出: 6
   */
  public int maxProduct(int[] nums) {
    // max:表示0到i的最大乘积
    int max = 1;
    // min:表示0到i的最小乘积
    int min = 1;

    int res = Integer.MIN_VALUE;
    for (int num : nums) {
      // 由于存在负数,导致子数组乘积从最大变成最小 -> 遇到负数，交换
      if (num < 0) {
        int temp = max;
        max = min;
        min = temp;
      }
      // 延续当前子数组 or 开始新的数组
      max = Math.max(max * num, num);
      min = Math.min(min * num, num);

      res = Math.max(res, max);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] nums = {2, 3, -2, 4};
//    int[] nums = {0, 1};
    Solution solution = new Solution();
    System.out.println(solution.maxProduct(nums));
  }

}
