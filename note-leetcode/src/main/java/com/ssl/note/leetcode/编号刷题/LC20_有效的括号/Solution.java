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
      // 入栈
      if (map.containsKey(c)) {
        stack.push(c);
        continue;
      }
      // 出栈
      if (stack.isEmpty()) {
        return false;
      }
      if (map.get(stack.peek()) != c) {
        return false;
      }
      stack.pop();
    }
    // s='{'时，栈还有元素
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.isValid("(]"));
  }
}
