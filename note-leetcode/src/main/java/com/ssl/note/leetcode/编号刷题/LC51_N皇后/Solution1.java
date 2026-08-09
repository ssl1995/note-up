package com.ssl.note.leetcode.编号刷题.LC51_N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

  /**
   * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
   * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
   * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
   * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
   * 输入：n = 4
   * 输出：
   * [[".Q..",
   * "...Q",
   * "Q...",
   * "..Q."],
   * ["..Q.",
   * "Q...",
   * "...Q",
   * ".Q.."]]
   * 解释：如上图所示，4 皇后问题存在两个不同的解法。
   */
  public List<List<String>> solveNQueens(int n) {
    if (n < 0) {
      return new ArrayList<>();
    }
    char[][] board = new char[n][n];
    for (char[] row : board) {
      Arrays.fill(row, '.');
    }

    // path路径法:判断左右对角线方便
    int[] path = new int[n];
    List<List<String>> res = new ArrayList<>();
    dfs(board, 0, n, path, res);

    return res;
  }

  private void dfs(char[][] board, int i, int n, int[] path, List<List<String>> res) {
    if (i == n) {
      addRes(board, res);
      return;
    }
    for (int j = 0; j < n; j++) {
      if (check(path, i, j)) {
        path[i] = j;

        board[i][j] = 'Q';
        dfs(board, i + 1, n, path, res);
        board[i][j] = '.';
      }
    }
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

  private void addRes(char[][] board, List<List<String>> res) {
    List<String> temp = new ArrayList<>();
    for (char[] row : board) {
      String rowStr = new String(row);
      temp.add(rowStr);
    }
    res.add(temp);
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int n = 4;
    List<List<String>> res = solution.solveNQueens(n);
    System.out.println(res);
  }


}
