package com.ssl.note.leetcode.编号刷题.LC1139_最大的以1为边界的正方形;

public class Solution {

  /**
   * LC1139_最大的以1为边界的正方形
   * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
   * 示例：
   * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
   * 输出：9
   */
  public int largest1BorderedSquare(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    build(grid, m, n);

    // 数组全是0
    if (sum(grid, 0, 0, m - 1, n - 1) == 0) {
      return 0;
    }
    // 既然数组不全为0，至少存在一个1的正方形
    int res = 1;
    for (int a = 0; a < m; a++) {
      for (int b = 0; b < n; b++) {
        // 剪枝：c和d加res，如果存在一个res边长的正方形了，下一个找比它还大的就行
        for (int c = a + res, d = b + res, k = res + 1; c < m && d < n; c++, d++, k++) {
          // 外边尝试正方形累加和：(a,b)-(c,d)
          int outside = sum(grid, a, b, c, d);
          // 往里一层的正方形累加和：(a+1,b+1)-(c-1,d-1)
          int inside = sum(grid, a + 1, b + 1, c - 1, d - 1);
          // (a,b)-(c,d)期望全为1的周长,k=边长，也可以用d-b+1代替
          int round = (k - 1) * 4;

          // 外-里=期望全是1的周长，是一个有效边界全为1的正方形
          if (outside - inside == round) {
            res = k;
          }
        }
      }
    }
    // 返回正方形存在的元素数量=面积
    return res * res;
  }

  private void build(int[][] grid, int m, int n) {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        grid[i][j] += get(grid, i, j - 1) + get(grid, i - 1, j) - get(grid, i - 1, j - 1);
      }
    }
  }

  private int sum(int[][] grid, int a, int b, int c, int d) {
    // 不能构成正方形
    if (a > c) {
      return 0;
    }
    return get(grid, c, d) - get(grid, c, b - 1) - get(grid, a - 1, d) + get(grid, a - 1, b - 1);
  }

  private int get(int[][] grid, int i, int j) {
    return i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1 ? 0 : grid[i][j];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] grid = {{1, 1, 1,}, {1, 0, 1}, {1, 1, 1}};
    // 面积9
    System.out.println(solution.largest1BorderedSquare(grid));
  }


}
