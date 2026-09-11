package com.ssl.note.leetcode.编号刷题.LC32_最长有效括号;

import java.util.ArrayDeque;
import java.util.Deque;

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
    // 有效长度 = 当前匹配成功的右括号位置 - 最近一个未匹配位置
    // 栈：保存当前位置之前，所有未消除的下标，所以就会有以下2个含义：
    // 1、栈底：最后一个未匹配的)括号下标，初始值未哨兵-1
    // 2、栈顶到栈底：未匹配的(括号下标，等待被匹配
    Deque<Integer> stack = new ArrayDeque<>();
    // 哨兵，栈底初始化放-1，比如()(),当遍历到最后一个)位置是3时，无法之前前面已经匹配过多少
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


  public static void main(String[] args) {
    Solution soluiton = new Solution();
    String s="(()";
    System.out.println(soluiton.longestValidParentheses(s));
  }
}
