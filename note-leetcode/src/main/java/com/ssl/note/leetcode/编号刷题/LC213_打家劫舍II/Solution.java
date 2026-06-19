package com.ssl.note.leetcode.编号刷题.LC213_打家劫舍II;

public class Solution {

  /**
   * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
   * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
   * 示例 1：
   * 输入：nums = [2,3,2]
   * 输出：3
   * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
   */
  public int rob(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    if (n == 2) {
      return Math.max(nums[0], nums[1]);
    }
    return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
  }

  private int robRange(int[] nums, int start, int end) {
    int n = end - start + 1;
    if (n == 1) {
      return nums[start];
    }
    if (n == 2) {
      return Math.max(nums[start], nums[start + 1]);
    }
    // 动态规划优化成2个变量迭代
    int a = nums[start];
    int b = Math.max(nums[start], nums[start+1]);

    for (int i = start + 2; i <= end; i++) {
      int c = Math.max(a + nums[i], b);
      a = b;
      b = c;
    }

    return b;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 3, 1};
    // 4
    System.out.println(solution.rob(nums));
  }
}
