package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

public class Solution4 {
  /**
   * 接雨水
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * 输出：6
   * 解法三：单调递减栈
   */
  public int trap(int[] height) {
    if (height == null || height.length <= 1) {
      return 0;
    }
    int lMax = height[0];
    int rMax = height[height.length - 1];

    // 双指针
    int l = 1;
    int r = height.length - 2;
    int water = 0;

    while (l <= r) {
      // i位置的左右两边最高值,不包含自己
      if (lMax < rMax) {
        water += Math.max(0, lMax - height[l]);
        lMax = Math.max(lMax, height[l++]);
      } else {
        water += Math.max(0, rMax - height[r]);
        rMax = Math.max(rMax, height[r--]);
      }
    }

    return water;
  }

  public static void main(String[] args) {
    Solution4 solution = new Solution4();
    int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(solution.trap(nums));
  }
}
