package com.ssl.note.algorithm.第2章_排序算法.c5_归并排序;

import java.util.Arrays;

public class Solution {

  /**
   * 归并排序
   * 稳定性：<=,就是稳定的。<,就是不稳定的
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    mergeSort(nums, 0, nums.length - 1);
  }

  private void mergeSort(int[] nums, int left, int right) {
    if (nums == null || (left >= right)) {
      return;
    }
    int mid = left + (right - left) / 2;
    mergeSort(nums, left, mid);
    mergeSort(nums, mid + 1, right);
    merge(nums, left, mid, right);
  }

  // 合并两个有序数组，[left,center]、[center+1,right]
  private void merge(int[] nums, int left, int center, int right) {
    int length = right - left + 1;
    int[] temp = new int[length];

    int start1 = left;
    int start2 = center + 1;
    int index = 0;
    while (start1 <= center && start2 <= right) {
      // <=,就是稳定的
      // <,就是不稳定的
      if (nums[start1] <= nums[start2]) {
        temp[index++] = nums[start1++];
      } else {
        temp[index++] = nums[start2++];
      }
    }

    while (start1 <= center) {
      temp[index++] = nums[start1++];
    }

    while (start2 <= right) {
      temp[index++] = nums[start2++];
    }

    index = 0;
    while (index < length) {
      nums[left + index] = temp[index++];
    }
  }

  public static void main(String[] args) {
    int[] nums = new int[]{2, 5, 4, 3, 6, 1};
    Solution solution = new Solution();
    solution.sort(nums);
    System.out.println(Arrays.toString(nums));
  }
}
