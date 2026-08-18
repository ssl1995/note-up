package com.ssl.note.leetcode.编号刷题.LC922_按奇偶排序数组II;

public class Solution {

  /**
   * LC922_按奇偶排序数组II
   * 给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
   * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
   * 输入：nums = [4,2,5,7]
   * 输出：[4,5,2,7]
   * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
   */
  public int[] sortArrayByParityII(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[]{};
    }
    int n = nums.length;
    for (int i = 0, j = 1; i < n && j < n; ) {
      // 以最后一个位置来判断发货
      int t = nums[n - 1];

      if (nums[n - 1] % 2 == 0) {
        swap(nums, i, n - 1);
        i += 2;
      } else {
        swap(nums, j, n - 1);
        j += 2;
      }
    }

    return nums;
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
