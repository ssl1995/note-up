package com.ssl.note.leetcode.编号刷题.LC377_组合总和IV;

public class Solution2 {

  /**
   * LC377_组合总和IV
   * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
   * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
   * 题目数据保证答案符合 32 位整数范围。
   * 输入：nums = [1,2,3], target = 4
   * 输出：7
   * 解释：
   * 所有可能的组合为：
   * (1, 1, 1, 1)
   * (1, 1, 2)
   * (1, 2, 1)
   * (1, 3)
   * (2, 1, 1)
   * (2, 2)
   * (3, 1)
   * 请注意，顺序不同的序列被视作不同的组合。
   */
  public int combinationSum4(int[] nums, int target) {
    // 正确解法：完全背包求排列数,正向遍历
    // 定义dp[t]=总和为t的排列数
    // 比如nums={1,2,3},t=4
    // dp[4]=dp[3]+dp[2]+dp[1]
    int[] dp = new int[target + 1];
    // 边界：凑出0只有空序列这1种
    dp[0] = 1;

    // 外层必须遍历target（背包），这样每个t都允许任意数结尾，数出来的才是排列
    for (int i = 1; i < target + 1; i++) {
      for (int num : nums) {
        if (i >= num) {
          dp[i] += dp[i - num];
        }
      }
    }

    return dp[target];
  }


  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int[] nums = {1, 2, 3};
    int target = 4;
    System.out.println(solution.combinationSum4(nums, target));
  }
}
