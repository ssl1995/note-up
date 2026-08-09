package com.ssl.note.leetcode.编号刷题.LC51_N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

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
    List<List<String>> res = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) {
      Arrays.fill(row, '.');
    }

    int limit = (1 << n) - 1;
    dfs(board, 0, limit, 0, 0, 0, res);
    return res;
  }

  // 位运算回溯
  private void dfs(char[][] board, int row, int limit, int col, int left, int right, List<List<String>> res) {
    if (col == limit) {
      addRes(board, res);
      return;
    }
    // 1不能放、0能放
    int ban = col | left | right;
    // 1能放，0不能放,这样的设计方便while循环条件+找右侧1
    int candidate = limit & (~ban);
    int place;

    while (candidate != 0) {
      // 找最右侧1作为新位置放皇后
      place = candidate & -candidate;
      // 抹掉候选位置最右侧的1作为新候选
      candidate ^= place;

      // col是列占用掩码，不是列下标！需把place（最右侧的1）转成真实列下标再操作棋盘
      int colIdx = getLastOneIndex(place);
      board[row][colIdx] = 'Q';
      dfs(board, row + 1, limit, col | place, (left | place) >> 1, (right | place) << 1, res);
      board[row][colIdx] = '.';
    }
  }

  private void addRes(char[][] board, List<List<String>> res) {
    List<String> temp = new ArrayList<>();
    for (char[] row : board) {
      String rowStr = new String(row);
      temp.add(rowStr);
    }
    res.add(temp);
  }

  // 一个数是5，其二进制是101，返回它最右边1的位置，此时返回0位置
  // 可以用Integer.numberOfTrailingZeros来替代
  private int getLastOneIndex(int place) {
    int colIndex = 0;
    // 某个数的最低位不是0就+1
    for (int i = place; (i & 1) == 0; i >>= 1) {
      colIndex++;
    }
    return colIndex;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int n = 4;
    List<List<String>> res = solution.solveNQueens(n);
    System.out.println(res);
  }


}
