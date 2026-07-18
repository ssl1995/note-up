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

    StringBuilder res = new StringBuilder();

    Deque<Integer> numsStack = new ArrayDeque<>();
    Deque<String> letterStack = new ArrayDeque<>();
    int multi = 0;

    char[] cs = s.toCharArray();
    for (char c : cs) {
      // 1、数字，数字可能超过十位
      if (c >= '0' && c <= '9') {
        multi = multi * 10 + (c - '0');
      } else if (c == '[') {
        // 2、左括号，压入数字栈和字母栈
        numsStack.push(multi);
        multi = 0;

        letterStack.push(res.toString());

        res = new StringBuilder();
      } else if (c == ']') {
        // 3、右括号，弹出数字栈和字母栈，拼接字符串
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
    Solution solution = new Solution();
    String s = "abc3[cd]xyz";
    String res = "abccdcdcdxyz";
    System.out.println(solution.decodeString(s).equals(res));
  }
}
