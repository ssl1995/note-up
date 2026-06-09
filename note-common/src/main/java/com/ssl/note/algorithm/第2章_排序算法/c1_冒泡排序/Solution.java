package com.ssl.note.algorithm.第2章_排序算法.c1_冒泡排序;

public class Solution {

  /**
   * 冒泡排序
   * 稳定性：稳定
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    int n = nums.length;
    boolean isSwap = false;
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < n - 1; j++) {
        if (nums[j] > nums[j + 1]) {
          swap(nums, j, j + 1);
          isSwap = true;
        }
      }
      if (!isSwap) {
        break;
      }
    }
  }

  private void swap(int[] nums, int index1, int index2) {
    if (nums == null || index1 == index2) {
      return;
    }
    int temp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = temp;
  }
}
