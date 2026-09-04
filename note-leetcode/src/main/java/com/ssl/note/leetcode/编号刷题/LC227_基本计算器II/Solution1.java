package com.ssl.note.leetcode.编号刷题.LC227_基本计算器II;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1 {

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
    Deque<Integer> numbers = new ArrayDeque<>();
    Deque<Character> ops = new ArrayDeque<>();
    int num = 0;
    while (i < cs.length) {
      if (cs[i] == ' ') {
        i++;
      } else if (cs[i] >= '0' && cs[i] <= '9') {
        num = num * 10 + (cs[i++] - '0');
      } else {
        // 遇到加减乘除
        push(numbers, ops, num, cs[i]);
        i++;
        num = 0;
      }
    }
    push(numbers, ops, num, '+');
    return compute(numbers, ops);
  }

  // 虽然本题只有加减，但是保留乘除写法
  private void push(Deque<Integer> numberStack, Deque<Character> opsStack, int num, char op) {
    int n = numberStack.size();
    if (n == 0 || opsStack.peek() == '-' || opsStack.peek() == '+') {
      numberStack.push(num);
      opsStack.push(op);
    } else {
      int numPop = numberStack.pop();
      char opPop = opsStack.pop();
      if (opPop == '*') {
        numberStack.push(numPop * num);
      } else {
        numberStack.push(numPop / num);
      }
      opsStack.push(op);
    }
  }

  private int compute(Deque<Integer> numbersStack, Deque<Character> opsStack) {
    if (numbersStack.isEmpty() || opsStack.isEmpty()) {
      return 0;
    }
    // 栈底到栈顶：从左到右计算，需要逆序
    // Deque的api，push=addFirst,栈底=pollLast
    int res = numbersStack.pollLast();
    while (!numbersStack.isEmpty()) {
      int num = numbersStack.pollLast();
      char op = opsStack.pollLast();
      if (op == '+') {
        res += num;
      } else {
        res -= num;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
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
