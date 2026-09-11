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
    // max:表示以当前元素i结尾的连续子数组的最大乘积
    int max = 1;
    // min:表示以当前元素i结尾的连续子数组的最小乘积
    int min = 1;
    // res:全局答案，表示遍历到i为止，整个数组中乘积最大的连续子数组的乘积
    int res = Integer.MIN_VALUE;
    for (int num : nums) {
      // 当前元素为负数时，乘上它之后大小关系会反转：原来的最大乘积会变成最小，最小会变成最大
      if (num < 0) {
        int temp = max;
        max = min;
        min = temp;
      }
      // 二选一：延续之前的子数组（max * num）or 从当前元素重新开始（num）
      // 遇到0时会自动完成重置（max和min都变为0，之后从下一个元素重新开始）
      max = Math.max(max * num, num);
      min = Math.min(min * num, num);
      res = Math.max(res, max);
    }
    return res;
  }

  public static void main(String[] args) {
    int[] nums = {2, 3, -2, 4};
    Solution solution = new Solution();
    System.out.println(solution.maxProduct(nums));
  }

}
