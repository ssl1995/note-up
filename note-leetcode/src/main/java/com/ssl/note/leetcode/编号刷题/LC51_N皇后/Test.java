package com.ssl.note.leetcode.编号刷题.LC51_N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

  public List<List<String>> solveNQueens(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    char[][] board = new char[n][n];
    for (char[] c : board) {
      Arrays.fill(c, '.');
    }

    List<List<String>> res = new ArrayList<>();
    dfs(board, n, 0, res);

    return res;
  }

  private void dfs(char[][] cs, int n, int row, List<List<String>> res) {
    if (row == n) {
      addRes(cs, res);
      return;
    }

    for (int col = 0; col < n; col++) {
      if (!check(cs, n, row, col)) {
        continue;
      }
      cs[row][col] = 'Q';

      dfs(cs, n, row + 1, res);

      cs[row][col] = '.';
    }
  }

  private boolean check(char[][] cs, int n, int row, int col) {
    for (int i = 0; i < row; i++) {
      if (cs[i][col] == 'Q') {
        return false;
      }
    }

    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (cs[i][j] == 'Q') {
        return false;
      }
    }

    for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
      if (cs[i][j] == 'Q') {
        return false;
      }
    }

    return true;
  }

  private void addRes(char[][] board, List<List<String>> res) {
    List<String> path = new ArrayList<>();
    for (char[] cs : board) {
      path.add(new String(cs));
    }
    res.add(path);
  }


  public static void main(String[] args) {
    Test solution = new Test();
    int n = 4;
    // [[".Q..",
    //   "...Q",
    //   "Q...",
    //   "..Q."],
    //  ["..Q.",
    //  "Q...",
    //  "...Q",
    //  ".Q.."]]
    List<List<String>> res = solution.solveNQueens(n);
    System.out.println(res);
  }

}
