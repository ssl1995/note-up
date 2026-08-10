package com.ssl.note.leetcode.编号刷题.LC212_单词搜索II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  /**
   * LC212_单词搜索II
   * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
   */
  public List<String> findWords(char[][] board, String[] words) {
    if (board == null || board.length == 0 || board[0].length == 0) {
      return new ArrayList<>();
    }
    // 建立前缀树
    buildTrie(words);

    int m = board.length;
    int n = board[0].length;

    List<String> res = new ArrayList<>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dfs(board, i, j, 1, res);
      }
    }

    // 清理本次测试的前缀树
    clear();
    return res;
  }

  /**
   * 本题用前缀树，剪枝有3种非常好，需要领悟
   */
  private int dfs(char[][] board, int i, int j, int t, List<String> res) {
    // 结束条件1：越界
    // 剪枝手段2导致的剪枝：已经访问过了(==0的特殊设置)
    if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1
        || board[i][j] == 0) {
      return 0;
    }
    char temp = board[i][j];
    // 路径坐标
    int path = temp - 'a';
    t = tree[t][path];
    // 剪枝手段1导致的剪枝：前缀树要么没有这个字符 or 已经收集过了
    if (pass[t] == 0) {
      return 0;
    }
    // 本次能从i,j位置收集到多少个有效单词，用于回溯减少pass=剪枝
    int collect = 0;
    // 收集自己
    if (end[t] != null) {
      res.add(end[t]);
      // 剪枝手段2：收集过的
      end[t] = null;
      collect++;
    }

    board[i][j] = 0;
    // 收集4个方向，从前缀树的t位置出发
    collect += dfs(board, i + 1, j, t, res);
    collect += dfs(board, i - 1, j, t, res);
    collect += dfs(board, i, j + 1, t, res);
    collect += dfs(board, i, j - 1, t, res);
    // 剪枝手段1：前缀树曾经收集过的字符pass减1
    pass[t] -= collect;
    board[i][j] = temp;
    return collect;
  }

  // 实现一个前缀树，节点范围由题目提供
  // 1 <= words.length <= 3 * 10^4
  // 1 <= words[i].length <= 10
  private final int MAX = 30001;
  //  private final int MAX = 16;
  private int[][] tree = new int[MAX][26];
  private int[] pass = new int[MAX];
  private String[] end = new String[MAX];
  private int cnt;

  private void buildTrie(String[] words) {
    cnt = 1;
    for (String word : words) {
      insert(word);
    }
  }

  private void insert(String word) {
    int cur = 1;
    pass[cur]++;
    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';
      if (tree[cur][path] == 0) {
        tree[cur][path] = ++cnt;
      }
      // 容易忘记
      cur = tree[cur][path];
      pass[cur]++;
    }
    // end记录末尾单词是谁，便于收集有多少个命中的单词
    end[cur] = word;
  }

  private void clear() {
    for (int i = 1; i <= cnt; i++) {
      Arrays.fill(tree[i], 0);
      pass[i] = 0;
      end[i] = null;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
    String[] words = {"oath", "pea", "eat", "rain"};
    // ["eat","oath"]
    System.out.println(solution.findWords(board, words));
  }

}
