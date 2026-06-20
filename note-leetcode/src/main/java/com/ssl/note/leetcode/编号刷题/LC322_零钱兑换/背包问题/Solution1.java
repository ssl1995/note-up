package com.ssl.note.leetcode.编号刷题.LC322_零钱兑换.背包问题;

public class Solution1 {

  /**
   * 完全背包：二维DP基础写法
   * 问题描述：
   * 有 n 个物品，第 i 个物品重量为 w[i]，价值为 v[i]；背包容量为 C。
   * 每个物品选无数次，求能装入背包的最大价值。
   */
  public int completeKnapsack01(int[] w, int[] v, int C) {
    int n = w.length;
    int[][] dp = new int[n + 1][C + 1];
    for (int i = 1; i < n; i++) {     // i 从 1 开始，表示考虑前 i 个物品
      for (int j = 0; j <= C; j++) {  // 遍历每个容量
        if (j < w[i - 1]) {
          // 容量不够，只能不选
          dp[i][j] = dp[i - 1][j];
        } else {
          // 完全背包核心：
          // 不选：dp[i-1][j]
          // 选：  dp[i][j - w[i-1]] + v[i-1]
          //       注意这里是 dp[i][...] 而不是 dp[i-1][...]
          //       因为选了第 i-1 个物品后，还可以继续选第 i-1 个物品
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - w[i - 1]] + v[i - 1]);
          // 01背包：
          // dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
        }
      }
    }
    return dp[n][C];
  }

  /**
   * 完全背包：一维DP空间优化写法
   */
  public int completeKnapsack02(int[] w, int[] v, int C) {
    int n = w.length;
    int[] dp = new int[C + 1];
    for (int i = 0; i < n; i++) {
      // 完全背包:正序遍历
      // 因为正序遍历时，先更新了小的容量，后面算大容量时用到的小容量已经是本轮更新过的值，相当于允许再次选择当前物品。
      for (int j = w[i]; j <= C; j++) {
        // 完全背包：容量正序遍历
        // 原因：dp[j - w[i]] 需要是本轮已经更新过的值
        //      这样同一个物品可以被重复选择
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
        // 01背包：
        // dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
      }
    }
    return dp[C];
  }
}
