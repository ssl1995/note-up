package com.ssl.note.leetcode.编号刷题.LC63_不同路径II;

public class Solution {

  /**
   * LC63 不同路径II
   * 给定一个 m x n 的整数数组 grid。一个机器人初始位于 左上角（即 grid[0][0]）。机器人尝试移动到 右下角（即 grid[m - 1][n - 1]）。机器人每次只能向下或者向右移动一步。
   * 网格中的障碍物和空位置分别用 1 和 0 来表示。机器人的移动路径中不能包含 任何 有障碍物的方格。
   * 返回机器人能够到达右下角的不同路径数量。
   * 测试用例保证答案小于等于 2 * 109。
   * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
   * 输出：2
   * 解释：3x3 网格的正中间有一个障碍物。
   * 从左上角到右下角一共有 2 条不同的路径：
   * 1. 向右 -> 向右 -> 向下 -> 向下
   * 2. 向下 -> 向下 -> 向右 -> 向右
   */
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;

    int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
      if (obstacleGrid[i][0] == 1) {
        break;
      }
      dp[i][0] = 1;
    }
    for (int i = 0; i < n; i++) {
      if (obstacleGrid[0][i] == 1) {
        break;
      }
      dp[0][i] = 1;
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          // 障碍物格子不可达，dp[i][j] 保持 0；
          // 但本行后面的格子仍可能从上方绕过来，不能 break 掉整行
          continue;
        }
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }

    return dp[m - 1][n - 1];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] ob = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
    int res = 2;
    System.out.println(solution.uniquePathsWithObstacles(ob) == res);
  }
}
