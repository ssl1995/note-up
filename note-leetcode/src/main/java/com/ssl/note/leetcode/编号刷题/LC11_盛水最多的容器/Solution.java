package com.ssl.note.leetcode.编号刷题.LC11_盛水最多的容器;

/**
 * @author SongShengLin
 * @date 2022/1/20 8:14 AM
 * @description
 */
public class Solution {
  /**
   * 盛水最多的容器
   * 输入：[1,8,6,2,5,4,8,3,7]
   * 输出：49
   */
  public int maxArea(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int maxArea = 0;
    while (left <= right) {
      // 先算面积，再移动指针
      int temp = (right - left) * Math.min(height[left], height[right]);
      maxArea = Math.max(maxArea, temp);

      // 单调性(贪心)：面积变大，只能移动矮的才有可能变大
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }
    return maxArea;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    System.out.println(solution.maxArea(height));
  }
}
