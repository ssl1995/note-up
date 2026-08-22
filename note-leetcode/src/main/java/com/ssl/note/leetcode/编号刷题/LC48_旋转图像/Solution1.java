package com.ssl.note.leetcode.编号刷题.LC48_旋转图像;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/24 9:01 AM
 * @description
 */
public class Solution1 {
  /**
   * 旋转图像
   * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
   * 输出：[[7,4,1],[8,5,2],[9,6,3]]
   */
  public void rotate(int[][] matrix) {
    if (matrix == null || matrix.length < 2) {
      return;
    }
    // 左上角和右下角坐标
    int a = 0, b = 0;
    int c = matrix.length - 1, d = matrix[0].length - 1;
    // 正方形，行判断就够，=加不加都行
    while (a <= c) {
      rotateEdge(matrix, a++, b++, c--, d--);
    }
  }

  private void rotateEdge(int[][] nums, int a, int b, int c, int d) {
    int times = c - a;
    int offset = 0;
    while (times-- > 0) {
      // a,b  a,d
      // c,b  c,d
      int temp = nums[a][b + offset];
      nums[a][b + offset] = nums[c - offset][b];
      nums[c - offset][b] = nums[c][d - offset];
      nums[c][d - offset] = nums[a + offset][d];
      nums[a + offset][d] = temp;

      offset++;
    }
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    solution.rotate(matrix);
    // 期望输出: [[13,9,5,1],[14,10,6,2],[15,11,7,3],[16,12,8,4]]
    System.out.println(Arrays.deepToString(matrix));
  }
}
