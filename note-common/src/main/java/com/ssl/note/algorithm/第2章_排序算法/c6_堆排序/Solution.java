package com.ssl.note.algorithm.第2章_排序算法.c6_堆排序;

import java.util.Arrays;

public class Solution {

  /**
   * 堆排序
   * 稳定性：不稳定
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    heapSort(nums);
  }

  // 大根堆
  private void heapSort(int[] nums) {
    if (nums == null) {
      return;
    }
    int n = nums.length;
    // 1、建堆
    buildMaxHeap(nums, n);
    for (int i = n - 1; i >= 0; i--) {
      // 2、交换：大根堆，0=最大值
      swap(nums, 0, i);
      // 3、最后一个叶子结点往下调整
      downHeap(nums, 0, i);
    }
  }

  private void buildMaxHeap(int[] nums, int heapSize) {
    // 大根堆，从后往前建堆
    for (int i = (heapSize - 2) >> 1; i >= 0; i--) {
      downHeap(nums, i, heapSize);
    }
  }

  private void downHeap(int[] nums, int index, int heapSize) {
    // 3个坐标
    int left = index * 2 + 1;
    int right = index * 2 + 2;
    int larget = index;

    if (left < heapSize && nums[left] > nums[larget]) {
      larget = left;
    }
    if (right < heapSize && nums[right] > nums[larget]) {
      larget = right;
    }
    if (larget != index) {
      swap(nums, index, larget);
      downHeap(nums, larget, heapSize);
    }
  }

  private void swap(int[] nums, int left, int right) {
    if (left >= right) {
      return;
    }
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }


  public static void main(String[] args) {
    int[] nums = new int[]{2, 5, 4, 3, 6, 1};
    Solution solution = new Solution();
    solution.sort(nums);
    System.out.println(Arrays.toString(nums));
  }
}
