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
public class Solution1 {

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
      // 入栈：左括号才能入栈
      if (map.containsKey(c)) {
        stack.push(c);
      } else {
        // 否则就需要出栈
        // 栈空 或者 待插入不匹配栈顶
        if (stack.isEmpty() || c != map.get(stack.peek())) {
          return false;
        }
        stack.pop();
      }
    }
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    System.out.println(solution.isValid("(]"));
  }
}
