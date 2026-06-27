package com.ssl.note.leetcode.编号刷题.LC53_最大子数组和;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:21 AM
 * @description
 */
public class Solution1 {

  /**
   * 最大子数组和
   * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
   * 输出：6
   * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
   */
  public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    //从选择到贪心：假设你现在遍历到第 i 个元素，你面对一个选择：
    // 1、把 nums[i] 接在之前的子数组后面：之前的和 + nums[i]
    // 2、从 nums[i] 重新开始一个新子数组：nums[i]
    // 贪心：如果之前的子数组和是负数，后续加上新的nums[i]只会拖累新的子数组和，不如从nums[i]新开始
    int[] dp = new int[nums.length];
    dp[0] = nums[0];

    int res = nums[0];
    for (int i = 1; i < nums.length; i++) {
      // 要么接在前一个后面，要么从当前重新开始
      dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);

      res = Math.max(res, dp[i]);
    }

    return res;
  }


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(solution.maxSubArray(nums));
  }
}
