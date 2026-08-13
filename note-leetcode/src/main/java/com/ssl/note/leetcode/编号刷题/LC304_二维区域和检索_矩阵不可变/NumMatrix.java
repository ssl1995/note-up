package com.ssl.note.leetcode.编号刷题.LC304_二维区域和检索_矩阵不可变;

public class NumMatrix {

  private int[][] sum;

  public NumMatrix(int[][] matrix) {
    if (matrix == null) {
      return;
    }
    int m = matrix.length;
    int n = matrix[0].length;

    // 初始化sum:m和n
    sum = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        sum[i][j] = matrix[i][j];
      }
    }

    // 公式：+左+上-左上+自己
    // 需要特判
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        sum[i][j] = get(sum, i, j - 1) + get(sum, i - 1, j) - get(sum, i - 1, j - 1) + get(sum, i, j);
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    // 需要特判
    // (row1,col1) - (row2,col2)
    return get(sum, row2, col2) - get(sum, row2, col1 - 1) - get(sum, row1 - 1, col2) + get(sum, row1 - 1, col1 - 1);
  }

  // 需要特判，加一个get方法
  private int get(int[][] matrix, int i, int j) {
    return i < 0 || j < 0 || i > matrix.length - 1 || j > matrix[0].length - 1 ? 0 : matrix[i][j];
  }
}
