package com.ssl.note.leetcode.编号刷题.LC84_柱状图中最大矩形;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author SongShengLin
 * @date 2022/1/29 3:29 PM
 * @description
 */
public class Solution {

  /**
   * 柱状图中最大矩形
   * 输入：heights = [2,1,5,6,2,3]
   * 输出：10
   * 解释：最大的矩形为图中红色区域，面积为 10
   */
  public int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    int maxArea = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    int n = heights.length;
    // 单调递增栈，栈顶是cur，往左边能扩到left位置，往右边能扩到right位置
    // 右边扩的位置right
    for (int right = 0; right < n; right++) {
      // 单调递增栈标准写法
      while (!stack.isEmpty() && heights[right] <= heights[stack.peek()]) {
        // 栈顶cur
        int cur = stack.pop();
        // 左边扩的位置left
        int left = stack.isEmpty() ? -1 : stack.peek();

        // (left..cur..right)是取不到左右边界的，长度是r-l-1
        int curArea = (right - left - 1) * heights[cur];

        maxArea = Math.max(maxArea, curArea);
      }
      stack.push(right);
    }
    while (!stack.isEmpty()) {
      int cur = stack.pop();
      int left = stack.isEmpty() ? -1 : stack.peek();
      // 当栈还有元素时候，这些元素的右边已经比他们更小的元素了
      // 此时右边界看成数组长度n
      int curArea = (n - left - 1) * heights[cur];
      maxArea = Math.max(maxArea, curArea);
    }
    return maxArea;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] heights = {2, 1, 5, 6, 2, 3};
    System.out.println(solution.largestRectangleArea(heights));
  }
}
