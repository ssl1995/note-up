package com.ssl.note.practice.lc_top_100;

import java.util.ArrayDeque;
import java.util.Deque;

public class Practice {

  public String decodeString(String s) {
    if (s == null || s.length() == 0) {
      return "";
    }
    Deque<String> letterStack = new ArrayDeque<>();
    Deque<Integer> numberStack = new ArrayDeque<>();

    int num = 0;
    StringBuilder res = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c >= '1' && c <= '9') {
        num = num * 10 + c - '0';
      } else if (c >= 'a' && c <= 'z') {
        res.append(c);
      } else if (c == '[') {
        letterStack.push(res.toString());
        numberStack.push(num);
        res = new StringBuilder();
        num = 0;
      } else {
        int count = numberStack.isEmpty() ? 0 : numberStack.pop();

        StringBuilder temp = new StringBuilder();
        while (count-- > 0) {
          temp.append(res);
        }

        res = new StringBuilder(letterStack.pop() + temp);
      }
    }

    return res.toString();
  }

  public String decodeString1(String s) {
    if (s.isEmpty()) {
      return "";
    }

    Deque<Integer> numsStack = new ArrayDeque<>();
    Deque<String> letterStack = new ArrayDeque<>();

    int multi = 0;
    StringBuilder res = new StringBuilder();
    char[] cs = s.toCharArray();
    for (char c : cs) {
      // 数字，数字可能超过十位
      if (c >= '0' && c <= '9') {
        multi = multi * 10 + (c - '0');
      } else if (c == '[') {
        // 左括号，压入数字栈和字母栈
        numsStack.push(multi);
        multi = 0;

        letterStack.push(res.toString());
        res = new StringBuilder();
      } else if (c == ']') {
        // 右括号，弹出数字栈和字母栈，拼接字符串
        int count = numsStack.pop();

        StringBuilder temp = new StringBuilder();
        while (count-- > 0) {
          temp.append(res);
        }

        // 与弹出的字母栈拼接
        res = new StringBuilder(letterStack.pop() + temp);
      } else {
        // 字母，保存当前res
        res.append(c);
      }
    }

    return res.toString();
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "10[abc]";
    System.out.println(practice.decodeString1(s));
    System.out.println(practice.decodeString(s));
  }

}
