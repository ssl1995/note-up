package com.ssl.note.leetcode.编号刷题.LC739_每日温度;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

  /**
   * 每日温度
   * 输入: temperatures = [73,74,75,71,69,72,76,73]
   * 输出: [1,1,4,2,1,1,0,0]
   */
  public int[] dailyTemperatures(int[] temperatures) {
    if (temperatures == null) {
      return new int[]{};
    }
    int[] res = new int[temperatures.length];
    Deque<Integer> stack = new ArrayDeque<>();

    // 单调栈标准写法
    for (int i = 0; i < temperatures.length; i++) {
      // 单调递减栈：栈底到栈顶是递减的，所以入>栈顶就要弹出
      while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
        Integer pop = stack.pop();
        res[pop] = i - pop;
      }
      stack.push(i);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] t = {73, 74, 75, 71, 69, 72, 76, 73};
    // [1,1,4,2,1,1,0,0]
    System.out.println(Arrays.toString(solution.dailyTemperatures(t)));
  }
}