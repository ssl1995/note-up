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
      // 栈空
      if (stack.isEmpty()) {
        stack.push(c);
        continue;
      }
      // 栈顶不是左括号，就失败
      if (!map.containsKey(stack.peek())) {
        return false;
      }
      // 不匹配，就入栈，下一轮栈顶判断就返回失败
      if (c != map.get(stack.peek())) {
        stack.push(c);
      } else {
        // 匹配就出栈
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
