package com.ssl.note.practice.lc_top_100;

import java.util.Arrays;

public class Practice {

  /**
   * 1. 16-多维动态规划 LC 62 不同路径
   * 2. 15-动态规划 LC 152 乘积最大子数组
   */
  public void nextPermutation(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }
    int n = nums.length;
    int index1 = -1;
    for (int i = 1; i < n - 1; i++) {
      if (nums[i - 1] > nums[i]) {
        index1 = i;
      }
    }

    if (index1 == -1) {
      swap(nums, 1, n - 1);
      return;
    }

    int index2 = n - 1;
    // [4,5,2,6,3,1] -> [4,5,3,6,2,1] -> [4,5,3,1,2,6]
    while (nums[index2] < nums[index1]) {
      index2--;
    }

    swap(nums, index1, index2);

    reverse(nums, index1 + 1, n - 1);
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private void reverse(int[] nums, int i, int j) {
    int l = i;
    int r = j;
    while (l < r) {
      swap(nums, l++, r--);
    }
  }


  public static void main(String[] args) {
    Practice solution = new Practice();

    // 测试用例1：正常情况
    int[] nums1 = {4, 5, 2, 6, 3, 1};
    solution.nextPermutation(nums1);
    System.out.println("Test 1: " + Arrays.toString(nums1)); // 期望：[4,5,3,1,2,6]

    // 测试用例2：最大排列
    int[] nums2 = {3, 2, 1};
    solution.nextPermutation(nums2);
    System.out.println("Test 2: " + Arrays.toString(nums2)); // 期望：[1,2,3]

    // 测试用例3：重复元素
    int[] nums3 = {1, 1, 5};
    solution.nextPermutation(nums3);
    System.out.println("Test 3: " + Arrays.toString(nums3)); // 期望：[1,5,1]

    // 测试用例4：最小排列
    int[] nums4 = {1, 2, 3};
    solution.nextPermutation(nums4);
    System.out.println("Test 4: " + Arrays.toString(nums4)); // 期望：[1,3,2]
  }
}
