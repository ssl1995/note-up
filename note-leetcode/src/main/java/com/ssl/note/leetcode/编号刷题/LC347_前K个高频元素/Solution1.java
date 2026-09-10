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

    // 初始化桶，桶的个数最大值=n+1
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

    // 辅助数组list转化结果到res数组
    List<Integer> list = new ArrayList<>();
    for (int i = buckets.size() - 1; i >= 0 && list.size() > k; i--) {
      if (buckets.get(i) != null) {
        list.addAll(buckets.get(i));
      }
    }

    int[] res = new int[k];
    int index = 0;
    for (int i = k - 1; i >= 0; i--) {
      res[index++] = list.get(i);
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
