package com.ssl.note.leetcode.编号刷题.LC20_有效的括号;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2022/1/20 11:29 PM
 * @description
 */
public class Solution {

  /**
   * 有效的括号
   * 输入：s = "{[]}"
   * 输出：true
   */
  public boolean isValid(String s) {
    if (s == null) {
      return false;
    }
    Deque<Character> stack = new ArrayDeque<>();

    for (char c : s.toCharArray()) {
      if (Objects.equals(c, '(') || Objects.equals(c, '[') || Objects.equals(c, '{')) {
        stack.push(c);
        continue;
      }
      if (Objects.equals(c, ')')) {
        if (!Objects.equals(stack.peek(), '(')) {
          return false;
        }
        stack.pop();
      }
      if (Objects.equals(c, '}')) {
        if (!Objects.equals(stack.peek(), '{')) {
          return false;
        }
        stack.pop();
      }
      if (Objects.equals(c, ']')) {
        if (!Objects.equals(stack.peek(), '[')) {
          return false;
        }
        stack.pop();
      }
    }
    // s='{'时，栈还有元素
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.isValid("{"));
  }
}
