package com.ssl.note.leetcode.编号刷题.LC283_移动零;

import java.util.Arrays;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/20 9:17 AM
 * @Describe:
 */
public class Solution {

  /**
   * 移动零
   * 输入: nums = [0,1,0,3,12]
   * 输出: [1,3,12,0,0]
   */
  public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) {
      return;
    }
    // [0,l]放非0
    int l = 0;
    // [r,n-1]放0
    int r = 0;
    int n = nums.length;
    while (r < n) {
      if (nums[r] != 0) {
        swap(nums, l++, r);
      }
      r++;
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {0, 1, 0, 3, 12};
    solution.moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
  }


}
