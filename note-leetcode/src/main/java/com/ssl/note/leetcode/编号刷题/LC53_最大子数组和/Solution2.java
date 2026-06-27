package com.ssl.note.leetcode.编号刷题.LC53_最大子数组和;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:21 AM
 * @description
 */
public class Solution2 {

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
    int n = nums.length;
    // dp空间优化：不用数组，只用一个变量
    int pre = nums[0];

    int res = nums[0];
    for (int i = 1; i < n; i++) {
      pre = Math.max(pre + nums[i], nums[i]);

      res = Math.max(res, pre);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(solution.maxSubArray(nums));
  }
}
