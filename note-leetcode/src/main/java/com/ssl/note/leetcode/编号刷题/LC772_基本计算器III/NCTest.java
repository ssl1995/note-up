package com.ssl.note.leetcode.编号刷题.LC772_基本计算器III;

import java.util.ArrayList;
import java.util.List;

public class NCTest {

  /**
   * 牛客测试连接：https://www.nowcoder.com/practice/c215ba61c8b1443b996351df929dc4d4
   * 题目：请写一个整数计算器，支持加减乘三种运算和括号。
   */
  private int where;

  public int solve(String str) {
    where = 0;
    return f(str.toCharArray(), 0);
  }

  private int f(char[] cs, int i) {
    List<Integer> numbers = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    // number
    Integer cur = 0;

    while (i < cs.length && cs[i] != ')') {
      if (cs[i] >= '0' && cs[i] <= '9') {
        cur = cur * 10 + (cs[i++] - '0');
      } else if (cs[i] != '(') {
        // 运算符
        push(numbers, ops, cur, cs[i++]);
        cur = 0;// 记录的数字要清零
      } else {
        cur = f(cs, i + 1);
        i = where + 1;
      }
    }
    push(numbers, ops, cur, '+');
    where = i;
    return compute(numbers, ops);
  }

  private int compute(List<Integer> numbers, List<Character> ops) {
    int n = numbers.size();
    int res = numbers.get(0);
    // ops末尾不参与计算
    for (int i = 0; i < n - 1; i++) {
      res += ops.get(i) == '+' ? numbers.get(i + 1) : -numbers.get(i + 1);
    }
    return res;
  }

  private void push(List<Integer> numbers, List<Character> ops, Integer number, char op) {
    int n = numbers.size();
    if (n == 0 || ops.get(n - 1) == '+' || ops.get(n - 1) == '-') {
      numbers.add(number);
      ops.add(op);
    } else {
      int topNum = numbers.get(n - 1);
      char topOp = ops.get(n - 1);
      if (topOp == '*') {
        numbers.set(n - 1, topNum * number);
      } else {
        numbers.set(n - 1, topNum / number);
      }
      ops.set(n - 1, op);
    }
  }


}
