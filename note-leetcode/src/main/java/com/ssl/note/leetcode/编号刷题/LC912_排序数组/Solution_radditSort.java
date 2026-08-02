package com.ssl.note.leetcode.编号刷题.LC912_排序数组;

import com.ssl.note.common.utils.ArrayUtil;

public class Solution_radditSort {

  /**
   * LC912_排序数组
   * 三路快排
   */
  public int[] sortArray(int[] nums) {
    radixSort(nums);
    return nums;
  }

  private void radixSort(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }
    int n = nums.length;
    int min = nums[0];
    for (int i = 1; i < n; i++) {
      min = Math.min(min, nums[i]);
    }
    int max = nums[0];
    for (int i = 0; i < n; i++) {
      // 如果有负数，每个数加上最小值的相反数
      if (min < 0) {
        nums[i] -= min;
      }
      max = Math.max(max, nums[i]);
    }

    int maxBits = getMaxBits(max);
    radix(nums, n, maxBits);

    // 如果有负数，加回去
    if (min < 0) {
      for (int i = 0; i < n; i++) {
        nums[i] += min;
      }
    }
  }

  private int getMaxBits(int max) {
    int res = 0;
    while (max > 0) {
      // 每次都除进制位数
      max /= BASE_SIZE;
      res++;
    }
    return res;
  }

  // 十进制
  private final int BASE_SIZE = 10;

  private void radix(int[] nums, int n, int maxBits) {
    // 排序后的数组
    int[] help = new int[n];

    for (int offset = 1; maxBits > 0; offset *= BASE_SIZE, maxBits--) {
      // 十进制需要十位数的数组
      int[] count = new int[BASE_SIZE];

      // 前缀和累加数组
      for (int i = 0; i < n; i++) {
        count[(nums[i] / offset) % BASE_SIZE]++;
      }
      for (int i = 1; i < BASE_SIZE; i++) {
        count[i] = count[i - 1] + count[i];
      }
      // 从后往前遍历，help[--count[当前位]]=nums[j]
      for (int i = n - 1; i >= 0; i--) {
        help[--count[(nums[i] / offset) % BASE_SIZE]] = nums[i];
      }

      // java.lang包下的，LC不用import，可以直接用
      System.arraycopy(help, 0, nums, 0, n);
    }
  }

  public static void main(String[] args) {
    Solution_radditSort solution = new Solution_radditSort();
    int times = 1001;
    while (times-- > 0) {
      int[] nums = {-1, 2, -8, -10};
      // 转成非负数：[9,12,2,0]
      // 排序：[ 9, 2, 0, 12 ] 这里就不对了
      int[] right = {-10, -8, -1, 2};
      solution.sortArray(nums);
      boolean equals = ArrayUtil.isEquals(nums, right);
      if (!equals) {
        System.out.println("error");
        break;
      }
    }
  }


}
