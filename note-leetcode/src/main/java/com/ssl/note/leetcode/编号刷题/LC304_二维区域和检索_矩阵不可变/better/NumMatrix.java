package com.ssl.note.leetcode.编号刷题.LC304_二维区域和检索_矩阵不可变.better;

public class NumMatrix {

  private int[][] sum;

  public NumMatrix(int[][] matrix) {
    if (matrix == null) {
      return;
    }
    int m = matrix.length;
    int n = matrix[0].length;

    // 初始化sum:m和n
    sum = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        sum[i][j] = matrix[i - 1][j - 1];
      }
    }

    // 公式：+左+上-左上+自己
    // 需要特判
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        sum[i][j] += sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1];
      }
    }
  }

  // 原始
  public int sumRegion1(int row1, int col1, int row2, int col2) {
    // (row1,col1) - (row2,col2)
    row1++;
    col1++;
    row2++;
    col2++;
    return sum[row2][col2] - sum[row2][col1 - 1] - sum[row1 - 1][col2] + sum[row1 - 1][col1 - 1];
  }

  // 优化，原先+1的涉及的一些-1可以优化掉
  public int sumRegion(int row1, int col1, int row2, int col2) {
    // (row1,col1) - (row2,col2)
    row2++;
    col2++;
    return sum[row2][col2] - sum[row2][col1] - sum[row1][col2] + sum[row1][col1];
  }
}
