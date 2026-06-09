package com.ssl.note.algorithm.第2章_排序算法.c7_桶排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

  /**
   * 桶排序
   * 稳定性：稳定
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    // 假设用3个桶来进行排序
    bucketSort(nums, 3);
  }

  // 桶排序
  private void bucketSort(int[] nums, int bucketCount) {
    if (nums == null) {
      return;
    }
    int length = nums.length;
    int max = Arrays.stream(nums).max().getAsInt();
    int min = Arrays.stream(nums).min().getAsInt();
    // 1、创建桶
    List<List<Integer>> buckets = new ArrayList<>(bucketCount);
    for (int i = 0; i < bucketCount; i++) {
      buckets.add(new ArrayList<>());
    }
    // 2、桶里放入数据
    // 问题：为什么+1?
    // 原因1：最大值不会越界。原因2：所有数相同时不会除零
    int bucketRange = (max - min) / bucketCount + 1;
    for (int num : nums) {
      // 这个数在从 0 开始排的队伍里，属于第几个小组
      buckets.get((num - min) / bucketRange).add(num);
    }
    // 3、桶里数据排序后放回原数组
    int bucketIndex = 0;
    for (int i = 0; i < bucketCount; i++) {
      List<Integer> mList = buckets.get(i);
      Collections.sort(mList);

      for (Integer integer : mList) {
        nums[bucketIndex++] = integer;
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
