package com.ssl.note.leetcode.编号刷题.LC212_单词搜索II;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution_TIME_LONG {

  /**
   * LC212_单词搜索II
   * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
   */
  public List<String> findWords(char[][] board, String[] words) {
    if (board == null || board.length == 0 || board[0].length == 0) {
      return new ArrayList<>();
    }
    // aa aab,第二个aab时会收集到重复的aa，所以用set
    Set<String> res = new HashSet<>();
    int m = board.length;
    int n = board[0].length;

    // 用79的解法会超时
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        for (String word : words) {
          backtrack(board, i, j, word, 0, res);
        }
      }
    }

    return new ArrayList<>(res);
  }

  private void backtrack(char[][] board, int i, int j, String word, int index, Set<String> res) {
    // 结束条件1：失败
    if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1
        || board[i][j] != word.charAt(index)) {
      return;
    }
    // 结束条件2：word全部匹配成功
    if (index == word.length() - 1) {
      res.add(word);
      return;
    }
    // 做选择：原地标记
    // 防止重复：向四个方向递归，第一次是4个方向，后面都是3个方向，因为标记的来时路不能重复
    board[i][j] = '*';
    // 四个方向找
    backtrack(board, i - 1, j, word, index + 1, res);
    backtrack(board, i + 1, j, word, index + 1, res);
    backtrack(board, i, j - 1, word, index + 1, res);
    backtrack(board, i, j + 1, word, index + 1, res);
    // 回溯特征撤销选择：标记还原，递归结束，返回上一层
    board[i][j] = word.charAt(index);
  }
}
