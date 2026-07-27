package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

public class Solution3 {

  /**
   * 接雨水
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * 输出：6
   * 解法四：双指针，最优解
   */
  public int trap(int[] height) {
    // 记录i位置左右两边最高
    int leftMax = 0;
    int rightMax = 0;

    // 双指针
    int left = 0;
    int right = height.length - 1;

    int water = 0;
    while (left < right) {
      // i位置的左右两边最高值
      leftMax = Math.max(leftMax, height[left]);
      rightMax = Math.max(rightMax, height[right]);
      // i位置，能接雨水的位置一定在较高的那一侧
      if (leftMax < rightMax) {
        System.out.println("右边加水："+leftMax +"-"+height[left]);
        water += leftMax - height[left];
        left++;
      } else {
        water += rightMax - height[right];
        System.out.println("左边加水："+rightMax +"-"+height[right]);
        right--;
      }
    }

    return water;
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    int[] nums = {4, 2, 0, 3, 2, 5};
    System.out.println(solution.trap(nums));
  }
}
