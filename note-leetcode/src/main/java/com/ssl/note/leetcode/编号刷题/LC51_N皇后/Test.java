package com.ssl.note.leetcode.编号刷题.LC51_N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

  public List<List<String>> solveNQueens(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    char[][] cs = new char[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(cs[i], '.');
    }

    List<List<String>> res = new ArrayList<>();
    backtrack(cs, 0, n, res);
    return res;
  }

  private void backtrack(char[][] cs, int row, int n, List<List<String>> res) {
    if (row == n) {
      addRes(cs, res);
      return;
    }
    for (int col = 0; col < n; col++) {
      if (check(cs, row, col, n)) {
        continue;
      }
      cs[row][col] = 'Q';
      backtrack(cs, row + 1, n, res);
      cs[row][col] = '.';
    }
  }

  private boolean check(char[][] cs, int row, int col, int n) {
    for (int i = 0; i < row; i++) {
      if (cs[i][col] == 'Q') {
        return true;
      }
    }
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (cs[i][j] == 'Q') {
        return true;
      }
    }
    for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
      if (cs[i][j] == 'Q') {
        return true;
      }
    }
    return false;
  }

  private void addRes(char[][] cs, List<List<String>> res) {
    List<String> temp = new ArrayList<>();
    for (char[] c : cs) {
      temp.add(new String(c));
    }
    res.add(temp);
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
