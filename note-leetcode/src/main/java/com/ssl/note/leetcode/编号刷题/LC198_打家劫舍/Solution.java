package com.ssl.note.leetcode.编号刷题.LC198_打家劫舍;

/**
 * @author SongShengLin
 * @date 2022/2/22 7:44 AM
 * @description
 */
public class Solution {
  /**
   * 打家劫舍
   * 要求：不能盗窃相邻房间
   * 输入：[2,7,9,3,1]
   * 输出：12
   * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
   * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
   */
  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    int n = nums.length;
    int[][] dp = new int[n][2];
    dp[0][0] = nums[0];
    dp[0][1] = 0;

    for (int i = 1; i < n; i++) {
      // 偷,第i-1间必须不偷
      dp[i][0] = dp[i - 1][1] + nums[i];
      // 不偷,第i-1间可偷可不偷
      dp[i][1] = Math.max(dp[i-1][0],dp[i-1][1]);
    }

    return Math.max(dp[n - 1][0], dp[n - 1][1]);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 7, 9, 3, 1};
    System.out.println(solution.rob(nums));
  }
}
