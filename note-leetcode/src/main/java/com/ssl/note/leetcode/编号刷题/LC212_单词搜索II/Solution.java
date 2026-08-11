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
   * 本题用前缀树 + 回溯，剪枝有3种，非常好，需要领悟：
   * 剪枝1（pass计数）：前缀树节点记录还有多少个未收集的单词经过它，pass==0 时整棵子树不用再搜
   * 剪枝2（end置空）：单词收集过后 end[t]=null，同一路径上的其他走法不会重复收集同一个单词
   * 剪枝3（原地标记）：board[i][j]=0 标记已访问，代替 visited 数组，省空间
   * 以 main 中 board 为例：
   * o a a n        可找到: oath = (0,0)o->(0,1)a->(1,1)t->(2,1)h
   * e t a e        可找到: eat  = (1,3)e->(1,2)a->(1,1)t
   * i h k r        找不到: pea(没有p)、rain(r的邻居没有a)
   * i f l v
   */
  private int dfs(char[][] board, int i, int j, int t, List<String> res) {
    // 剪枝1：越界 或者 ≠特殊字符0的ASCII码
    // 例：走 oath 时 o(0,0)被置0，从 a(0,1) 就无法再走回 o(0,0)，防止同一格重复使用
    if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1
        || board[i][j] == 0) {
      return 0;
    }
    char temp = board[i][j];
    // 路径坐标
    int path = temp - 'a';
    t = tree[t][path];
    // 前缀树要么没有这个字符 or 该前缀下的单词已经收集完了
    // 例1(没有这个字符)：从 (0,3)n 出发，根节点没有 'n' 这条路 -> t=0，pass[0]恒为0，直接剪枝
    // 例2(已经收集完)：eat 被收集后，e->a->t 沿途 pass 各减1；若该子树下再无其他单词，
    if (pass[t] == 0) {
      return 0;
    }
    // 本次从i,j位置出发能收集到多少个有效单词，用于回溯时减少pass来剪枝
    int collect = 0;
    // 收集自己：当前前缀正好是一个完整单词
    if (end[t] != null) {
      res.add(end[t]);
      // 剪枝2：收集过的单词置空，防止不同的格子走法重复收集同一个单词
      // 例：若单词表有 "aa"，a(0,1)->a(0,2) 和 a(0,2)->a(0,1) 都能走到同一结尾节点，
      // 第一次收集后置空，第二次走到这里 end[t]==null 就不会再收集
      end[t] = null;
      collect++;
    }

    board[i][j] = 0;
    // 收集4个方向，从前缀树的t位置出发
    collect += dfs(board, i + 1, j, t, res);
    collect += dfs(board, i - 1, j, t, res);
    collect += dfs(board, i, j + 1, t, res);
    collect += dfs(board, i, j - 1, t, res);
    // 剪枝3：回溯时把本次收集到的单词数从当前节点的pass中扣掉，
    pass[t] -= collect;
    board[i][j] = temp;
    return collect;
  }

  // 实现一个前缀树，节点范围由题目提供
  // 1 <= words.length <= 3 * 10^4
  // 1 <= words[i].length <= 10
  private static final int MAX = 30001;
  //  private final int MAX = 16;
  private static int[][] tree = new int[MAX][26];
  private static int[] pass = new int[MAX];
  private static String[] end = new String[MAX];
  private static int cnt;

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
    // 例：插入 "eat" 后，end[节点t] = "eat"，dfs走到该节点即可直接拿到完整单词
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
    // 力扣官方答案 ["eat","oath"]（不限制顺序），本代码实际按扫描顺序输出 [oath, eat]
    System.out.println(solution.findWords(board, words));
  }

}
