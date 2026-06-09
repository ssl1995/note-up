package com.ssl.note.algorithm.第2章_排序算法.c8_基数排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

  /**
   * 基数排序
   * 稳定性：稳定
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    // 假设用3个桶来进行排序
    radixSort(nums);
  }

  // 基数排序
  private void radixSort(int[] nums) {
    if (nums == null) {
      return;
    }
    // -9到9有19个数
    int digitCount = 19;
    int maxCount = maxLength(nums);
    int radix = 1;

    int[][] tempArr = new int[digitCount][nums.length];

    for (int i = 0; i < maxCount; i++) {
      int[] count = new int[digitCount];

      for (int num : nums) {
        int temp = ((num / radix) % 10) + 9;
        tempArr[temp][count[temp]++] = num;
      }
      int index = 0;
      for (int j = 0; j < digitCount; j++) {
        if (count[j] == 0) {
          continue;
        }
        for (int k = 0; k < count[j]; k++) {
          nums[index++] = tempArr[j][k];
        }
      }
      radix *= 10;
    }
  }

  // 获取最大数字的位数
  private int maxLength(int[] nums) {
    if (nums == null) {
      return 0;
    }
    int max = Arrays.stream(nums).max().getAsInt();
    int min = Arrays.stream(nums).min().getAsInt();
    if (min > 0) {
      return getBitCount(max);
    }
    return Math.max(getBitCount(max), getBitCount(-min));
  }

  // 获取一个数字的位数
  private int getBitCount(int num) {
    if (num == 0) {
      return 1;
    }
    int count = 0;
    while (num != 0) {
      count++;
      num /= 10;
    }
    return count;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{2, 5, 4, 3, 6, 1};
    Solution solution = new Solution();
    solution.sort(nums);
    System.out.println(Arrays.toString(nums));
  }
}
