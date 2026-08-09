package com.ssl.note.leetcode.编号刷题.LC227_基本计算器II;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * LC227_基本计算器II
   * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
   * 表达式只包含 +、-、*、/ 和数字，可以有空格，没有括号。整数除法仅保留整数部分。
   * 示例1：s = "3+2*2" → 输出 7
   * 示例2：s = " 3/2 " → 输出 1
   * 示例3：s = " 3+5 / 2 " → 输出 5
   * <p>
   * 解法：嵌套递归模板（与LC224/LC772同一模板）
   * 本题没有括号，永远不会触发嵌套递归，f(0)一趟扫完整个字符串
   * 核心是push的结算策略：乘除立即与栈顶结算，加减延迟到最后统一累加
   */

  public int calculate(String s) {
    return f(s.toCharArray(), 0);
  }

  private int f(char[] cs, int i) {
    List<Integer> numbers = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    int cur = 0;
    while (i < cs.length) {
      if (cs[i] == ' ') {
        i++;
      } else if (cs[i] >= '0' && cs[i] <= '9') {
        cur = cur * 10 + (cs[i++] - '0');
      } else {
        // 遇到加减乘除
        push(numbers, ops, cur, cs[i]);
        i++;
        cur = 0;
      }
    }
    push(numbers, ops, cur, '+');
    return compute(numbers, ops);
  }

  private int compute(List<Integer> numbers, List<Character> ops) {
    int n = numbers.size();
    int res = numbers.get(0);
    for (int i = 0; i < n - 1; i++) {
      res += ops.get(i) == '+' ? numbers.get(i + 1) : -numbers.get(i + 1);
    }
    return res;
  }

  private void push(List<Integer> numbers, List<Character> ops, Integer cur, char op) {
    int n = numbers.size();
    if (n == 0 || ops.get(n - 1) == '+' || ops.get(n - 1) == '-') {
      numbers.add(cur);
      ops.add(op);
    } else {
      int topN = numbers.get(n - 1);
      char topOp = ops.get(n - 1);
      if (topOp == '*') {
        numbers.set(n - 1, topN * cur);
      } else {
        numbers.set(n - 1, topN / cur);
      }
      ops.set(n - 1, op);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    // 7
    System.out.println(solution.calculate("3+2*2"));
    // 1
    System.out.println(solution.calculate(" 3/2 "));
    // 5
    System.out.println(solution.calculate(" 3+5 / 2 "));
    // 4，连除从左到右：8/4=2，2*2=4
    System.out.println(solution.calculate("8/4*2"));
  }
}
