package com.ssl.note.leetcode.编号刷题.LC394_字符串解码;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test {

  public String decodeString(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    StringBuilder res = new StringBuilder();

    Deque<Integer> numsStack = new ArrayDeque<>();
    Deque<String> letterStack = new ArrayDeque<>();
    int multi = 0;
    for (char c : s.toCharArray()) {
      if (c >= '0' && c <= '9') {
        multi = multi * 10 + (c - '0');
      } else if (c == '[') {
        numsStack.push(multi);
        multi = 0;

        letterStack.push(res.toString());
        res = new StringBuilder();
      } else if (c == ']') {
        int num = numsStack.pop();
        StringBuilder temp = new StringBuilder();
        while (num-- > 0) {
          temp.append(res);
        }


        res = new StringBuilder(letterStack.pop() + temp);
      } else {
        res.append(c);
      }

    }

    return res.toString();
  }

  public static void main(String[] args) {
    String s = "3[a]2[bc]";
    Test test = new Test();
    System.out.println(test.decodeString(s));
  }
}
