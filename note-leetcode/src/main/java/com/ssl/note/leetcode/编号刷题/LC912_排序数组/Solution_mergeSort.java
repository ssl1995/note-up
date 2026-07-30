package com.ssl.note.leetcode.编号刷题.LC912_排序数组;

import com.ssl.note.common.utils.ArrayUtil;

public class Solution_mergeSort {

  /**
   * LC912_排序数组
   * 三路快排
   */
  public int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return nums;
  }

  private void mergeSort(int[] nums, int l, int r) {
    if (l == r) {
      return;
    }
    int mid = l + (r - l) / 2;
    mergeSort(nums, l, mid);
    mergeSort(nums, mid + 1, r);
    merge(nums, l, mid, r);
  }

  private void merge(int[] nums, int l, int m, int r) {
    int[] help = new int[r - l + 1];
    int index = 0;

    int i = l;
    int j = m + 1;
    while (i <= m && j <= r) {
      help[index++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
    }
    while (i <= m) {
      help[index++] = nums[i++];
    }
    while (j <= r) {
      help[index++] = nums[j++];
    }

    System.arraycopy(help, 0, nums, l, r - l + 1);
  }

  public static void main(String[] args) {
    Solution_mergeSort solution = new Solution_mergeSort();
    int times = 1001;
    while (times-- > 0) {
      int[] nums = {5, 1, 1, 2, 0, 0};
      int[] right = {0, 0, 1, 1, 2, 5};
      solution.sortArray(nums);
      boolean equals = ArrayUtil.isEquals(nums, right);
      if (!equals) {
        System.out.println("error");
        break;
      }
    }
  }


}
