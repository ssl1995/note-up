package com.ssl.note.leetcode.编号刷题.LC70_爬楼梯;

/**
 * @author SongShengLin
 * @date 2022/1/27 8:57 AM
 * @description
 */
public class Solution1 {

  /**
   * 爬楼梯
   * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   * n=1,返回1;n=2,返回2;n=3，返回3;
   * - 时间复杂度：O(n)，只需一次遍历
   * - 空间复杂度：O(n)，需要数组存储状态
   */
  public int climbStairs(int n) {
    if (n <= 2) {
      return n;
    }
    // 动态规划
    int[] dp = new int[n + 1];
    dp[1] = 1;
    dp[2] = 2;

    for (int i = 3; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }

    return dp[n];
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int n = 4;
    System.out.println(solution.climbStairs(n));
  }

}
