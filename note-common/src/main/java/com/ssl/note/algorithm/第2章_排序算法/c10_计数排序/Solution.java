package com.ssl.note.algorithm.第2章_排序算法.c10_计数排序;

import java.util.Arrays;

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
    countingSort(nums);
  }

  // 基数排序
  private void countingSort(int[] nums) {
    if (nums == null) {
      return;
    }
    int length = nums.length;
    int max = Arrays.stream(nums).max().getAsInt();
    int min = Arrays.stream(nums).min().getAsInt();
    int bucketLength = max - min + 1;
    int[] buckets = new int[bucketLength];

    // 基数排序，不基于比较，基于每个数离最小值出现的次数
    for (int num : nums) {
      buckets[num - min]++;
    }

    int index = 0;
    for (int j = 0; j < bucketLength; j++) {
      if (buckets[j] == 0) {
        continue;
      }
      for (int k = 0; k < buckets[j]; k++) {
        nums[index++] = min + j;
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
