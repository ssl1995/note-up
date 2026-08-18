package com.ssl.note.leetcode.编号刷题.LC31_下一个排列;

import java.util.Arrays;

public class Test {

  /**
   * 下一个排列
   * 核心：把数组看成一个多位数，求比它大的数里最小的那个。
   * 示例：
   * 0 1 2 3 4 5
   * [4,5,2,6,3,1]
   * 1. 找下降点：i=2（2 < 6）
   * 2. 找交换点：j=4（3 > 2），交换后 [4,5,3,6,2,1]
   * 3. 反转剩余：反转[6,2,1]得到[1,2,6]，最终结果 [4,5,3,1,2,6]
   * 边界情况：
   * - 数组长度 < 2：直接返回
   * - 无下降点（如[3,2,1]）：反转整个数组得到最小排列
   */
  public void nextPermutation(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }
    int n = nums.length;
    // 不能从前往后找，必须是从后往前
    int i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }
//    int i = n - 1;
//    while (i >= 1 && nums[i - 1] >= nums[i]) {
//      i--;
//    }
//    i--;

    if (i >= 0) {
      int j = n - 1;
      while (j > i && nums[j] <= nums[i]) {
        j--;
      }
      swap(nums, i, j);
    }

    reverse(nums, i + 1, n - 1);
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
    Test solution = new Test();

    // 测试用例1：正常情况
    int[] nums1 = {4, 5, 2, 6, 3, 1};
    solution.nextPermutation(nums1);
    System.out.println("Test 1: " + Arrays.toString(nums1)); // 期望：[4,5,3,1,2,6]

    // 测试用例2：最大排列
//    int[] nums2 = {3, 2, 1};
//    solution.nextPermutation(nums2);
//    System.out.println("Test 2: " + Arrays.toString(nums2)); // 期望：[1,2,3]
//
//    // 测试用例3：重复元素
//    int[] nums3 = {1, 5, 1};
//    solution.nextPermutation(nums3);
//    System.out.println("Test 3: " + Arrays.toString(nums3)); // 期望：[5,1,1]
//
//    // 测试用例4：最小排列
//    int[] nums4 = {1, 2, 3};
//    solution.nextPermutation(nums4);
//    System.out.println("Test 4: " + Arrays.toString(nums4)); // 期望：[1,3,2]
  }
}
