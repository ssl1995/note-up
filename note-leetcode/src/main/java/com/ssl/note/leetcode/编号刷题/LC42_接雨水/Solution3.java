package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

public class Solution3 {

  /**
   * 接雨水
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * 输出：6
   * 解法四：双指针，最优解
   */
  public int trap(int[] height) {
    if (height == null || height.length <= 1) {
      return 0;
    }
    // 记录i位置左右两边最高
    int leftMax = 0, rightMax = 0;

    // 双指针
    int l = 0, r = height.length - 1;
    int res = 0;

    while (l < r) {
      // l,r位置包含自己，找左右最高的最小值
      leftMax = Math.max(leftMax, height[l]);
      rightMax = Math.max(rightMax, height[r]);
      int min = Math.min(leftMax, rightMax);

      // l,r矮的那边作为底座接水，并且移动矮的那边
      int h = height[l] < height[r] ? height[l++] : height[r--];
      // 雨水量=左右两边小的-底座
      int diff = min - h;

      res += diff;
    }

    return res;
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(solution.trap(nums));
  }
}
