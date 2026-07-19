package com.ssl.note.algorithm.第2章_排序算法.c4_快速排序;

import java.util.Arrays;

public class Solution {

  /**
   * 快速排序
   * 稳定性：不稳定
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    quickSort(nums, 0, nums.length - 1);
  }

  private void quickSort(int[] nums, int start, int end) {
    if (nums == null || (start >= end)) {
      return;
    }
    int j = partition(nums, start, end);
    quickSort(nums, start, j - 1);
    quickSort(nums, j + 1, end);
  }

  private int partition(int[] nums, int start, int end) {
    // pivot 放哪头，哪头就是占位符,遍历区间要跳过它
    int pivotValue = nums[end];
    int j = start - 1;
    for (int i = start + 1; i < end; i++) {
      if (nums[i] < pivotValue) {
        // ++j：交换前，j需要提前走一步
        swap(nums, ++j, i);
      }
    }
    // j是待交换位置前一个位置，最后需要j+1交换
    swap(nums, j + 1, start);
    return j + 1;
  }

  private void swap(int[] nums, int index1, int index2) {
    if (nums == null || index1 == index2) {
      return;
    }
    int temp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = temp;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{2, 5, 4, 3, 6, 1};
    Solution solution = new Solution();
    solution.sort(nums);
    System.out.println(Arrays.toString(nums));
  }
}
