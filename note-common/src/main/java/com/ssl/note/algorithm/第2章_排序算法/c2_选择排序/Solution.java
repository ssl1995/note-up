package com.ssl.note.algorithm.第2章_排序算法.c2_选择排序;

public class Solution {

  /**
   * 选择排序
   * 稳定性：不稳定，[3,4,3,1] ->  第一次 [1,4,3,3]
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    int n = nums.length;
    for (int i = 0; i < n - 1; i++) {
      // 从未排序的数组中找最小的放到最终位置
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (nums[j] < nums[minIndex]) {
          minIndex = j;
        }
      }
      if (minIndex != i) {
        swap(nums, i, minIndex);
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
