package com.ssl.note.leetcode.编号刷题.LC394_字符串解码;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  /**
   * 字符串解码
   * 输入：s = "3[a]2[bc]"
   * 输出："aaabcbc"
   * 输入：s = "abc3[cd]xyz"
   * 输出："abccdcdcdxyz"
   */
  public String decodeString(String s) {
    if (s.isEmpty()) {
      return "";
    }
    Deque<Integer> numsStack = new ArrayDeque<>();
    Deque<String> letterStack = new ArrayDeque<>();

    int num = 0;
    StringBuilder res = new StringBuilder();
    char[] cs = s.toCharArray();
    for (char c : cs) {
      // 数字，数字判断0-9，整形要-'0'
      if (c >= '0' && c <= '9') {
        num = num * 10 + (c - '0');
      } else if (c >= 'a' && c <= 'z') {
        // 字母，保存当前res
        res.append(c);
      } else if (c == '[') {
        // 左括号，压入数字栈和字母栈
        numsStack.push(num);
        letterStack.push(res.toString());
        num = 0;
        res = new StringBuilder();
      } else {
        // 右括号，两个栈都是同时弹出
        int count = numsStack.isEmpty() ? 0 : numsStack.pop();
        String pop = letterStack.isEmpty() ? "" : letterStack.pop();

        StringBuilder temp = new StringBuilder();
        while (count-- > 0) {
          temp.append(res);
        }

        // 与弹出的字母栈拼接
        res = new StringBuilder(pop + temp);
      }
    }

    return res.toString();
  }

  public String decodeString1(String s) {
    if (s == null || s.isEmpty()) {
      return "";
    }
    Deque<Integer> numStack = new ArrayDeque<>();
    Deque<String> letterStack = new ArrayDeque<>();

    char[] cs = s.toCharArray();
    int num = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < cs.length; i++) {
      char c = cs[i];
      if (c >= '0' && c <= '9') {
        num = num * 10 + (c - '0');
      } else if (c >= 'a' && c <= 'z') {
        sb.append(String.valueOf(c));
      } else if (c == '[') {
        numStack.push(num);
        letterStack.push(sb.toString());
        num = 0;
        sb = new StringBuilder();
      } else {
        int count = numStack.isEmpty() ? 0 : numStack.pop();
        String pop = letterStack.isEmpty() ? "" : letterStack.pop();

        StringBuilder temp = new StringBuilder();
        while (count-- > 0) {
          temp.append(sb.toString());
        }
        // pop不用判空
        pop += temp.toString();
        sb = new StringBuilder(pop);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "3[a]2[bc]";
    String res = "aaabcbc";
    System.out.println(solution.decodeString(s).equals(res));
    System.out.println(solution.decodeString1(s).equals(res));
  }
}
