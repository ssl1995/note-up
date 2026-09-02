package com.ssl.note.leetcode.编号刷题.LC32_最长有效括号;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2022/1/22 11:10 PM
 * @description
 */
public class Solution {

  /**
   * 最长有效括号
   * 输入：s = ")()())"
   * 输出：4
   * 解释：最长有效括号子串是 "()()"
   * 方法一：栈
   * 核心：栈底保存最后一个没有被匹配的右括号的下标
   * 时间复杂度：O(n)，空间复杂度：O(n)
   */
  public int longestValidParentheses(String s) {
    if (s.isEmpty()) {
      return 0;
    }
    int max = 0;
    // 栈底保存最后一个没有被匹配的右括号的下标
    Deque<Integer> stack = new LinkedList<>();
    // 有效长度 = 当前匹配成功的右括号位置 - 最近一个未匹配位置
    // 如果s="()",那么都是匹配的左右，当)遍历时，出栈后，peek=-1就能计算正确长度
    // 栈底初始化放-1，作为"虚拟的最后一个未匹配右括号"
    stack.push(-1);

    char[] cs = s.toCharArray();
    for (int i = 0; i < cs.length; i++) {
      if (cs[i] == '(') {
        stack.push(i);
      } else {
        stack.pop();

        if (stack.isEmpty()) {
          // 没有匹配的(，当前)成为新的"最后一个未匹配右括号"
          stack.push(i);
        } else {
          // peek:最后一个没有被匹配的右括号下标
          // 匹配上的括号长度=左闭右开：i-peek+1-1=i-peek
          max = Math.max(max, i - stack.peek());
        }
      }
    }

    return max;
  }

}
