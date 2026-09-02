package com.ssl.note.leetcode.编号刷题.LC64_最小路径和;

import java.util.Arrays;

public class Solution {

  /**
   * 题目：给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
   * 说明：每次只能向下或者向右移动一步。
   * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
   * 输出：7
   * 解释：因为路径 1→3→1→1→1 的总和最小。
   */
  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;

    int[][] dp = new int[m][n];
    dp[0][0] = grid[0][0];

    for (int i = 1; i < m; i++) {
      dp[i][0] = dp[i - 1][0] + grid[i][0];
    }

    for (int i = 1; i < n; i++) {
      dp[0][i] = dp[0][i - 1] + grid[0][i];
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        // dp数组
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
      }
    }

    return dp[m - 1][n - 1];
  }

  /**
   * 错误：本题不能初始化m+1和n+1
   * 初始化元素为0，首行首列min操作会错误
   * 初始化元素为整形最大，+grid[i-1][j-1]会超出整形最大
   */
  public int minPathSum1(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    int[][] dp = new int[m + 1][n + 1];
    for (int[] d : dp) {
      Arrays.fill(d, Integer.MAX_VALUE);
    }

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        // 整形越界
        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
      }
    }

    return dp[m][n];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    System.out.println(solution.minPathSum(grid));
    System.out.println(solution.minPathSum1(grid));
  }
}
