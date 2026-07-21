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
    int[] dp = new int[n];
    // 初始化
    dp[0] = nums[0];// 一个房间最大值
    dp[1] = Math.max(nums[0], nums[1]);// 两个房间挑大的取值

    for (int i = 2; i < n; i++) {
      // 状态转移方程：不偷第i间房屋 vs 偷第i间房屋
      dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
    }

    return dp[n - 1];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 7, 9, 3, 1};
    System.out.println(solution.rob(nums));
  }
}
