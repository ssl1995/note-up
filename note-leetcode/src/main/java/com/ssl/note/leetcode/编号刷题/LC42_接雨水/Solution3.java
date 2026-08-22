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
    int leftMax = 0;
    int rightMax = 0;

    // 双指针
    int l = 0;
    int r = height.length - 1;
    int water = 0;

    while (l <= r) {
      // i位置的左右两边最高值,包含自己
      leftMax = Math.max(leftMax, height[l]);
      rightMax = Math.max(rightMax, height[r]);

      // 左右两边低位决定接哪边水
      int min = Math.min(leftMax, rightMax);
      int diff = height[l] < height[r] ? min - height[l++] : min - height[r--];
      water += Math.max(diff, 0);
      // 统一：
      //water += leftMax < rightMax ? leftMax - height[l++] : rightMax - height[r--];
    }

    return water;
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(solution.trap(nums));
  }
}
