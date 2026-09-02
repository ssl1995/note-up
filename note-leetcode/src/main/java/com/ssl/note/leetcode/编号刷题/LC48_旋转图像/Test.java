package com.ssl.note.leetcode.编号刷题.LC48_旋转图像;

public class Test {

  public void rotate(int[][] matrix) {
    int n = matrix.length;
    int a = 0, b = 0, c = n - 1, d = n - 1;
    while (a <= c) {
      f(matrix, a++, b++, c--, d--);
    }
  }

  private void f(int[][] matrix, int a, int b, int c, int d) {
    int times = c - a;
    int offset = 0;
    while (times-- > 0) {
      // (a,b) (a,d)
      // (c,b) (c,d)
      int t = matrix[a][b + offset];
      matrix[a][b + offset] = matrix[c - offset][b];
      matrix[c - offset][b] = matrix[c][d - offset];
      matrix[c][d - offset] = matrix[a + offset][d];
      matrix[a + offset][d] = t;
      offset++;
    }
  }
}
