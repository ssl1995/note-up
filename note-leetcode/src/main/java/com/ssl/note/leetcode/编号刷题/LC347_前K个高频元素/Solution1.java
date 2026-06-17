package com.ssl.note.leetcode.编号刷题.LC347_前K个高频元素;

import java.util.*;


public class Solution1 {

  /**
   * 前k个高频元素
   * 输入: nums = [1,1,1,2,2,3], k = 2
   * 输出: [1,2]
   */
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    int n = nums.length;
    List<List<Integer>> buckets = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++) {
      buckets.add(new ArrayList<>());
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      // 桶下标：频率
      // 桶放入：数字，可能是相同频率的但不相同的数字
      buckets.get(entry.getValue()).add(entry.getKey());
    }

    int[] res = new int[k];
    int index = 0;
    // 从后往前遍历桶，拿去前k个数返回
    for (int i = n; i >= 0; i--) {
      if (index >= k) {
        break;
      }
      List<Integer> bucket = buckets.get(i);
      if (bucket.isEmpty()) {
        continue;
      }
      // 每个桶里可能有多个相同的频率的不同的数，返回k个数
      for (int num : bucket) {
        if (index >= k) {
          break;
        }
        res[index] = num;
        index++;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    int[] nums = {1, 2, 1, 2, 1, 2, 3, 1, 3, 2};
    int t = 2;
    System.out.println(Arrays.toString(solution1.topKFrequent(nums, t)));
  }
}
