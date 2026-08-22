package com.ssl.note.leetcode.编号刷题.LC53_最大子数组和;

public class Solution3 {

  /**
   * 最大子数组和
   * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
   * 输出：6
   * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
   */
  public int maxSubArray(int[] nums) {
    // 分治法，理解分治的思想
    return divide(nums, 0, nums.length - 1);
  }

  private int divide(int[] nums, int left, int right) {
    // 递归终止
    if (left == right) {
      return nums[left];
    }
    int mid = left + (right - left) / 2;
    int leftMax = divide(nums, left, mid);       // 左半最大
    int rightMax = divide(nums, mid + 1, right); // 右半最大
    int crossMax = crossMax(nums, left, mid, right); // 跨越最大
    // 最大连续子数组来源3种可能：1、完全在左边、2、完全在右边、3、跨越左右
    return Math.max(Math.max(leftMax, rightMax), crossMax);
  }

  // 从mid位置往左和往右的前缀和最大值
  private int crossMax(int[] nums, int left, int mid, int right) {
    // 从mid向左扩展
    int leftSum = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = mid; i >= left; i--) {
      sum += nums[i];
      leftSum = Math.max(leftSum, sum);
    }
    // 从mid+1向右扩展
    int rightSum = Integer.MIN_VALUE;
    sum = 0;
    for (int i = mid + 1; i <= right; i++) {
      sum += nums[i];
      rightSum = Math.max(rightSum, sum);
    }
    return leftSum + rightSum;
  }
}
