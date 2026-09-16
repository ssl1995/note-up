package com.ssl.note.leetcode.编号刷题.LC347_前K个高频元素;

import java.util.*;


public class Solution1 {

  /**
   * 前k个高频元素
   * 输入: nums = [1,1,1,2,2,3], k = 2
   * 输出: [1,2]
   */
  public int[] topKFrequent(int[] nums, int k) {
    if (nums == null) {
      return new int[]{};
    }
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    // 前k个高频元素，最大的频率是n+1个，用桶排序
    int n = nums.length;
    List<Set<Integer>> buckets = new ArrayList<>(n + 1);
    for (int i = 0; i < n + 1; i++) {
      buckets.add(new HashSet<>());
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int num = entry.getKey();
      int count = entry.getValue();
      // 桶的下标就是频率，值就是那个数
      buckets.get(count).add(num);
    }

    int[] res = new int[k];
    int index = 0;
    for (int i = n; i >= 0; i--) {
      if (buckets.get(i).isEmpty()) {
        continue;
      }
      if (index == k) {
        break;
      }
      // 频率相同的,Set天然去重
      Set<Integer> sameCount = buckets.get(i);
      for (int num : sameCount) {
        res[index++] = num;
        if (index == k) {
          break;
        }
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
