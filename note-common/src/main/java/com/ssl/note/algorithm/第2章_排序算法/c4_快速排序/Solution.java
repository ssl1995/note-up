package com.ssl.note.algorithm.第2章_排序算法.c4_快速排序;

import com.ssl.note.common.utils.RandomUtil;

import java.util.Arrays;
import java.util.Random;

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
    // 可以随机使用一个数作为基准
    int pivotValue = nums[start];
    int j = start;
    for (int i = start + 1; i <= end; i++) {
      if (nums[i] < pivotValue) {
        // ++j：交换前，j需要提前走一步
        swap(nums, ++j, i);
      }
    }
    // 最后交换基准
    swap(nums, j, start);
    return j;
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
