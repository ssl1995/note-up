package com.ssl.note.leetcode.编号刷题.LC20_有效的括号;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
    Map<Character, Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');

    for (char c : s.toCharArray()) {
      // 入栈：栈空或者是左括号
      if (stack.isEmpty() || map.containsKey(c)) {
        stack.push(c);
      } else {
        // 出栈前检查：栈顶不是左 或者 待入的不匹配
        if (!map.containsKey(stack.peek()) || map.get(stack.peek()) != c) {
          return false;
        }
        // 出栈
        stack.pop();
      }
    }
    // s='{]'时，栈还有元素
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.isValid("(]"));
  }
}
