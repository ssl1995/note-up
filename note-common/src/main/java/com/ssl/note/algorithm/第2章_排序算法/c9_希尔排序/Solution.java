package com.ssl.note.algorithm.第2章_排序算法.c9_希尔排序;

import java.util.Arrays;

public class Solution {

  /**
   * 希尔排序
   * 稳定性：不稳定
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    // 假设用3个桶来进行排序
    shellSort(nums);
  }

  // 希尔排序
  private void shellSort(int[] nums) {
    if (nums == null) {
      return;
    }
    int length = nums.length;
    // 初始化步长：数组长度一半，每次减半
    // 好处：为了让小的元素快速跳到前面、大的元素快速跳到后面，减少最后一步插入排序的移动次数。
    for (int step = length >> 1; step >= 1; step >>= 1) {
      // 当步长=1，就是插入排序
      for (int i = step; i < length; i++) {
        int key = nums[i];
        int j = i - step;
        for (; j >= 0 && nums[j] > key; j -= step) {
          nums[j + step] = nums[j];
        }
        nums[j + step] = key;
      }
    }
  }

  public static void main(String[] args) {
    int[] nums = new int[]{2, 5, 4, 3, 6, 1};
    Solution solution = new Solution();
    solution.sort(nums);
    System.out.println(Arrays.toString(nums));
  }
}
