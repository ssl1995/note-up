package com.ssl.note.leetcode.编号刷题.LC224_基本计数器;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1 {

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
  private int where;

  public int calculate(String s) {
    where = 0;
    return f(s.toCharArray(), 0);
  }

  private int f(char[] cs, int i) {
    Deque<Integer> numberStack = new ArrayDeque<>();
    Deque<Character> opsStack = new ArrayDeque<>();
    int num = 0;
    while (i < cs.length && cs[i] != ')') {
      if (cs[i] == ' ') {
        i++;
        continue;
      }
      if (cs[i] >= '0' && cs[i] <= '9') {
        num = num * 10 + (cs[i++] - '0');
      } else if (cs[i] != '(') {
        push(numberStack, opsStack, num, cs[i++]);
        num = 0;
      } else {
        num = f(cs, i + 1);
        i = where + 1;
      }
    }
    push(numberStack, opsStack, num, '+');
    where = i;
    return compute(numberStack, opsStack);
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
    // 3
    System.out.println(solution.calculate("1 + 1"));
  }
}
