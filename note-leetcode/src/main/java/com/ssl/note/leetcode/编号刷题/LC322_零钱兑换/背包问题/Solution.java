package com.ssl.note.leetcode.编号刷题.LC322_零钱兑换.背包问题;

public class Solution {

  /**
   * 01背包问题：一维DP空间优化写法
   * 记忆口诀：
   * 01 背包：容量逆序（从大到小）
   * 完全背包：容量正序（从小到大）
   */
  public int knapsack02(int[] w, int[] v, int C) {
    int n = w.length;
    // dp[j] = 背包容量为 j 时能获得的最大价值
    int[] dp = new int[C + 1];

    for (int i = 0; i < n; i++) {
      // 01背包：逆序遍历,逆序时它还没被本轮更新=上一轮的值
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
    int[] w = new int[]{2};
    int[] v = new int[]{3};
    int C = 4;
    // dp=[ 0, 0, 3, 3, 3 ]
    System.out.println(solution.knapsack02(w, v, C));
    System.out.println(solution.knapsack03(w, v, C));
  }
}
