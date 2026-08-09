package com.ssl.note.leetcode.编号刷题.LC52_N皇后II;

public class Solution {

  /**
   * LC52_N皇后II
   * 求有效n皇后的个数
   * 示例：
   * n=4,返回2
   * 限制：
   * 1 <= n <= 9
   */
  public int totalNQueens(int n) {
    if (n < 0) {
      return 0;
    }

    // path路径法:判断左右对角线方便
    int[] path = new int[n];

    return dfs(0, n, path);
  }

  // 路径法缺点，如果n>9后会非常慢，所以会引出位计算法
  private int dfs(int i, int n, int[] path) {
    if (i == n) {
      return 1;
    }
    int res = 0;
    for (int j = 0; j < n; j++) {
      if (check(path, i, j)) {
        path[i] = j;

        res += dfs(i + 1, n, path);
      }
    }
    return res;
  }

  private boolean check(int[] path, int i, int j) {
    for (int k = 0; k < i; k++) {
      // 列是否存在相同：j == path[k]
      // 两个对角线是否存在相同：现在行-之前行 绝对值 == 现在列-之前列 绝对值
      if (j == path[k] || Math.abs(i - k) == Math.abs(j - path[k])) {
        return false;
      }
    }
    return true;
  }
}
