package com.ssl.note.leetcode.编号刷题.LC772_基本计算器III;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * LC772_基本计算器III
   * 实现加减乘除，含有()
   * 输入：
   * "(2*(3-4))*5"
   * 返回值：
   * -10
   */
  public static int where;

  public static int calculate(String str) {
    where = 0;
    return f(str.toCharArray(), 0);
  }

  // s[i....]开始计算，遇到字符串终止 或者 遇到)停止
  // 返回 : 自己负责的这一段，计算的结果
  // 返回之间，更新全局变量where，为了上游函数知道从哪继续！
  public static int f(char[] cs, int i) {
    List<Integer> numbers = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    int num = 0;
    // 循环：指针没越界 且 没遇到右括号
    while (i < cs.length && cs[i] != ')') {
      if (cs[i] == ' ') {
        // 空格跳过
        i++;
      } else if (cs[i] >= '0' && cs[i] <= '9') {
        // 数字
        num = num * 10 + cs[i++] - '0';
      } else if (cs[i] != '(') {
        // 运算符：+ - * /
        push(numbers, ops, num, cs[i++]);
        num = 0;
      } else {
        // 左括号：递归交给下个栈处理
        num = f(cs, i + 1);
        i = where + 1;
      }
    }
    // 最后一个数字没有入栈，入数字栈，符号位随意设置
    push(numbers, ops, num, '+');
    // 记录本轮遍历到的位置
    where = i;
    // 栈非空，计算出栈
    return compute(numbers, ops);
  }

  public static void push(List<Integer> numbers, List<Character> ops,
                          int num, char op) {
    int n = numbers.size();
    // 没有数字 或 遇到+、-
    if (n == 0 || ops.get(n - 1) == '+' || ops.get(n - 1) == '-') {
      numbers.add(num);
      ops.add(op);
    } else {
      int topNumber = numbers.get(n - 1);
      char topOp = ops.get(n - 1);
      // 这里只有乘除可能
      if (topOp == '*') {
        topNumber *= num;
      } else {
        topNumber /= num;
      }
      numbers.set(n - 1, topNumber);
      ops.set(n - 1, op);
    }
  }

  public static int compute(List<Integer> numbers, List<Character> ops) {
    int n = numbers.size();
    // 先取数字栈栈底的元素
    int res = numbers.get(0);
    // ops中只剩加减，末尾不参与计算
    for (int i = 0; i < n - 1; i++) {
      char op = ops.get(i);
      if (op == '+') {
        res += numbers.get(i + 1);
      } else {
        res -= numbers.get(i + 1);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    //纯加减乘除：6-4/2 = 6-2 = 4
    System.out.println(calculate("6-4/2"));
    // 乘除优先级：1+2*3-4/2 = 1+6-2 = 5
    System.out.println(calculate("1+2*3-4/2"));
    // 单层括号：2*(5+5*2)/3+(6/2+8) = 2*15/3+11 = 10+11 = 21
    System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
    // 括号开头：(1+2)*3 = 9
    System.out.println(calculate("(1+2)*3"));
    // 嵌套括号：((2+3)*(1+2)) = 5*3 = 15
    System.out.println(calculate("((2+3)*(1+2))"));
    // 混合嵌套：(2+6*3+5-(3*14/7+2)*5)+3 = (2+18+5-8*5)+3 = -15+3 = -12
    System.out.println(calculate("(2+6*3+5-(3*14/7+2)*5)+3"));
    // 括号前是减号：10-(2+3) = 5
    System.out.println(calculate("10-(2+3)"));
  }
}
