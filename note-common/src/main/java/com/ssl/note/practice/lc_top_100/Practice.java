package com.ssl.note.practice.lc_top_100;

import java.util.ArrayList;
import java.util.List;

public class Practice {
  private int where;

  public int calculate(String s) {
    where = 0;
    return f(s.toCharArray(), 0);
  }

  private int f(char[] cs, int i) {
    List<Integer> numStack = new ArrayList<>();
    List<Character> letterStack = new ArrayList<>();
    int num = 0;

    while (i <= cs.length - 1 && cs[i] != ')') {
      if (cs[i] == ' ') {
        i++;
      } else if (cs[i] >= '0' && cs[i] <= '9') {
        num = num * 10 + (cs[i++] - '0');
      } else if (cs[i] == '(') {
        num = f(cs, i + 1);
        i = where + 1;
      } else {
        push(numStack, letterStack, num, cs[i++]);
        num = 0;
      }
    }

    push(numStack, letterStack, num, '+');
    where = i;
    return compute(numStack, letterStack);
  }

  private void push(List<Integer> numStack, List<Character> letterStack, int num, char op) {
    int n = numStack.size();
    // 注意：要判断的是运算符栈顶，不是数字栈顶
    // 栈顶是+/-时不能合并（新数字后面可能跟着优先级更高的*/），只能先压栈；栈顶是*//时才弹栈合并
    if (n == 0 || letterStack.get(n - 1) == '-' || letterStack.get(n - 1) == '+') {
      numStack.add(num);
      letterStack.add(op);
    } else {
      int numPop = numStack.get(n - 1);
      char opPop = letterStack.get(n - 1);
      if (opPop == '+') {
        numPop = numPop + num;
      } else if (opPop == '-') {
        numPop = numPop - num;
      } else if (opPop == '*') {
        numPop = numPop * num;
      } else if (opPop == '/') {
        numPop = numPop / num;
      }
      numStack.set(n - 1, numPop);
      letterStack.set(n - 1, op);
    }
  }

  private int compute(List<Integer> numStack, List<Character> letterStack) {
    int n = numStack.size();
    int res = numStack.get(0);
    for (int i = 0; i < n - 1; i++) {
      int op = letterStack.get(i);
      if (op == '+') {
        res += numStack.get(i + 1);
      } else if (op == '-') {
        res += -numStack.get(i + 1);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[][] grod = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    // aaabcbc
  }

}
