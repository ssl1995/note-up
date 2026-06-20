package com.ssl.note.leetcode.编号刷题.LC322_零钱兑换.背包问题;

public class Solution {

  /**
   * 01背包问题：二维DP基础写法
   * <p>
   * 问题描述：
   * 有 n 个物品，第 i 个物品重量为 w[i]，价值为 v[i]；背包容量为 C。
   * 每个物品最多选 1 次，求能装入背包的最大价值。
   * 状态定义：
   * dp[i][j] = 考虑前 i 个物品，背包容量为 j 时能获得的最大价值
   * （i 从 0 到 n，j 从 0 到 C）
   * 状态转移（对第 i-1 个物品做决策）：
   * 1. 不选第 i-1 个物品：dp[i][j] = dp[i-1][j]
   * 2. 选第 i-1 个物品（前提是 j >= w[i-1]）：
   * dp[i][j] = dp[i-1][j - w[i-1]] + v[i-1]
   * 取两者最大值：
   * dp[i][j] = max(dp[i-1][j], dp[i-1][j - w[i-1]] + v[i-1])
   * 或dp[i+1][j] = max(dp[i][j], dp[i][j-w[i]] + v[i])
   * 初始化：
   * dp[0][j] = 0，表示没有物品时，任何容量下的最大价值都是 0
   * 返回：dp[n][C]
   */
  public int knapsack01(int[] w, int[] v, int C) {
    int n = w.length;
    // 行：0~n 共 n+1 行；列：0~C 共 C+1 列
    int[][] dp = new int[n + 1][C + 1];
    for (int i = 0; i < n; i++) {      // 遍历每个物品
      for (int j = 0; j <= C; j++) {   // 遍历每个容量
        if (j < w[i]) {
          // 容量不够，只能不选
          dp[i + 1][j] = dp[i][j];
        } else {
          // 容量够，选或不选取最大
          dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - w[i]] + v[i]);
        }
      }
    }
    return dp[n][C];
  }

  /**
   * 01背包问题：一维DP空间优化写法
   * <p>
   * 优化思路：
   * 二维写法中，计算 dp[i][j] 时只依赖上一行 dp[i-1][...]。
   * 因此可以把二维压缩成一维，用同一个数组反复更新。
   * 状态定义：
   * dp[j] = 背包容量为 j 时能获得的最大价值
   * 状态转移：
   * dp[j] = max(dp[j], dp[j - w[i]] + v[i])
   * <p>
   * 为什么容量 j 必须逆序遍历（从 C 到 w[i]）？
   * 一维 dp 数组同时存放着“上一轮”和“当前轮”的结果。
   * 当我们计算 dp[j] 时，需要用到 dp[j - w[i]]。
   * 这个 dp[j - w[i]] 必须是“上一轮”的值（还没选当前物品）。
   * 举例：w=[2], v=[3], C=4
   * 正序：dp[2]=3, dp[4]=dp[2]+3=6（错误，用了两次）
   * 逆序：dp[4]=dp[2]+3=3, dp[2]=3（正确，只用一次）
   * 记忆口诀：
   * 01 背包：容量逆序（从大到小）
   * 完全背包：容量正序（从小到大）
   */
  public int knapsack02(int[] w, int[] v, int C) {
    int n = w.length;
    // dp[j] = 背包容量为 j 时能获得的最大价值
    int[] dp = new int[C + 1];

    for (int i = 0; i < n; i++) {
      // 01背包：逆序遍历
      for (int j = C; j >= w[i]; j--) {
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
      }
    }
    return dp[C];
  }

  /**
   * 01背包问题：记忆化搜索写法（自顶向下）
   * 思路：
   * 从最后一个物品开始递归决策：选 or 不选。
   * 用 memo 数组缓存结果，避免重复计算。
   * 状态定义：
   * dfs(index, c) = 考虑第 index 个及之后的物品，在剩余容量 c 下的最大价值
   * 返回：dfs(0, C)
   */
  public int knapsack03(int[] w, int[] v, int C) {
    int n = w.length;
    int[][] memo = new int[n][C + 1];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= C; j++) {
        memo[i][j] = -1; // -1 表示未计算
      }
    }
    return dfs(w, v, 0, C, memo);
  }

  private int dfs(int[] w, int[] v, int index, int c, int[][] memo) {
    if (index == w.length || c == 0) {
      return 0;
    }
    if (memo[index][c] != -1) {
      return memo[index][c];
    }

    // 不选第 index 个物品
    int res = dfs(w, v, index + 1, c, memo);

    // 选第 index 个物品
    if (c >= w[index]) {
      res = Math.max(res, dfs(w, v, index + 1, c - w[index], memo) + v[index]);
    }

    memo[index][c] = res;
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] w = new int[]{4, 2, 1};
    int[] v = new int[]{300, 200, 150};
    int C = 4;

    System.out.println(solution.knapsack01(w, v, C)); // 350
    System.out.println(solution.knapsack02(w, v, C)); // 350
    System.out.println(solution.knapsack03(w, v, C)); // 350
  }
}
