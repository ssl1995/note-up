package com.ssl.note.leetcode.编号刷题.LC279_完全平方数;

public class Solution {

  /**
   * 完全平方数
   * 输入：n = 12
   * 输出：3
   * 解释：12 = 4 + 4 + 4
   * 题目约束：1<=n<=10^4
   */
  public int numSquares(int n) {
    // 拆解子问题：n = (n - j²) + j²，那么n = 组成 (n - j²) 的最少个数 + 1
    // dp[i]:表示构成整数i最少需要多少个完全平方数
    // 数组长度为什么是 n+1？
    // 因为我们需要表示 dp[0], dp[1], ..., dp[n] 一共 n+1 个状态
    int[] dp = new int[n + 1];
    // 题目n>=1，思考如何推导dp[0]=0
    // dp[1] = min(dp[1 - 1*1] + 1)=dp[0]+1 =1,推导出来dp[0]=0
    // 0不需要任何完全平方数
    dp[0] = 0;

    for (int i = 1; i <= n; i++) {
      // 组成i的最少平方数，最多可以有i个1
      dp[i] = i;
      // 注意：j*j<=i,是小于等于
      for (int j = 1; j * j <= i; j++) {
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 12;
    // dp=[0, 1, 2, 3, 1, 2, 3, 4, 2, 1, 2, 3, 3]
    System.out.println(solution.numSquares(n));
  }
}

