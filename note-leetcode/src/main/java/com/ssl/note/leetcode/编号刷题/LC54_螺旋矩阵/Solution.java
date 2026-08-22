package com.ssl.note.leetcode.编号刷题.LC54_螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
   */
  private List<Integer> res;
  public List<Integer> spiralOrder(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int a = 0, b = 0;
    int c = m - 1, d = n - 1;

    res = new ArrayList<>();

    while (a <= c && b <= d) {
      f(matrix, a++, b++, c--, d--);
    }

    return res;
  }

  private void f(int[][] nums, int a, int b, int c, int d) {
    for (int j = b; j <= d; j++) {
      res.add(nums[a][j]);
    }
    for (int i = a + 1; i <= c; i++) {
      res.add(nums[i][d]);
    }
    if (a < c) {
      for (int j = d - 1; j >= b; j--) {
        res.add(nums[c][j]);
      }
    }
    if (b < d) {
      for (int i = c - 1; i > a; i--) {
        res.add(nums[i][b]);
      }
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
//    int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    int[][] matrix = {{1}, {2}, {3}};
    System.out.println(solution.spiralOrder(matrix));
  }
}
