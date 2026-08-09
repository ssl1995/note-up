package com.ssl.note.leetcode.编号刷题.LC224_基本计数器;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * LC224_基本计数器
   * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
   * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成，无乘除参与
   * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval()
   * 示例1：s = "(1+(4+5+2)-3)+(6+8)" → 输出 23
   * 示例2：s = " 2-1 + 2 " → 输出 3
   * 示例3：s = "-(3+4)" → 输出 -7
   * 解法：嵌套递归模板（与LC227/LC772同一模板，本题去掉乘除结算分支）
   * 三要素：
   * 1.全局变量where：下级返回前更新，上级通过它知道解析到了哪
   * 2.递归f(i)：解析s[i..]这一段，遇到字符串结尾或')'就返回本段结果
   * 3.遇到'('：外包给下级f(i+1)，返回值当操作数用，i = where+1 跳过括号
   */
  public static int where;

  public int calculate(String s) {
    where = 0;
    return f(s.toCharArray(), 0);
  }

  private int f(char[] cs, int i) {
    List<Integer> numbers = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    int cur = 0;
    while (i < cs.length && cs[i] != ')') {
      if (cs[i] == ' ') {
        i++;
      } else if (cs[i] >= '0' && cs[i] <= '9') {
        cur = cur * 10 + (cs[i++] - '0');
      } else if (cs[i] != '(') {
        // 遇到加减
        push(numbers, ops, cur, cs[i]);
        i++;
        cur = 0;
      } else {
        // 遇到左括号
        cur = f(cs, i + 1);
        // 本次遍历从上轮递归的下个位置开始遍历
        i = where + 1;
      }
    }
    push(numbers, ops, cur, '+');
    // 告诉上级本次遍历到哪个位置
    where = i;
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
    // 只有加减的情况，就是每次都入栈
    numbers.add(cur);
    ops.add(op);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    // 23
    System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    // 3
    System.out.println(solution.calculate(" 2-1 + 2 "));
    // -7，一元负号：按 0-(3+4) 结算
    System.out.println(solution.calculate("-(3+4)"));
    // 3
    System.out.println(solution.calculate("1-(-2)"));
  }
}
