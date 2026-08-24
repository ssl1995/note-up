package com.ssl.note.leetcode.编号刷题.LC22_括号生成;

import java.util.ArrayList;
import java.util.List;

public class Solution {
  /**
   * 题目：数字n代表生成括号的对数，生成所有可能的并且有效的括号组合。
   * 输入：n = 3
   * 输出：["((()))","(()())","(())()","()(())","()()()"]
   */
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    dfs1(0, 0, n, path, res);
//    dfs2(n, n, path, res);
    return res;
  }

  /**
   * left和right从0开始
   * 左右括号有效的条件：
   * 1、左右括号到n停止
   * 2、left < n就放左括号
   * 3、left > right就放右括号
   */
  private void dfs1(int left, int right, int n, StringBuilder path, List<String> res) {
    if (left == n && right == n) {
      res.add(path.toString());
      return;
    }
    if (left < n) {
      path.append("(");
      dfs1(left + 1, right, n, path, res);
      path.deleteCharAt(path.length() - 1);
    }
    if (left > right) {
      path.append(")");
      dfs1(left, right + 1, n, path, res);
      path.deleteCharAt(path.length() - 1);
    }
  }

  /**
   * left和right从n开始
   */
  private void dfs2(int left, int right, StringBuilder path, List<String> res) {
    if (left == 0 && right == 0) {
      res.add(path.toString());
      return;
    }
    if (left > 0) {
      path.append("(");
      dfs2(left - 1, right, path, res);
      path.deleteCharAt(path.length() - 1);
    }
    if (left < right) {
      path.append(")");
      dfs2(left, right - 1, path, res);
      path.deleteCharAt(path.length() - 1);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 3;
    // [((())), (()()), (())(), ()(()), ()()()]
    System.out.println(solution.generateParenthesis(n));
  }

}
