package com.ssl.note.leetcode.编号刷题.LC560_和为k的子数组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/2/27 11:00 AM
 * @description
 */
public class Solution1 {
  /**
   * 和为k的子数组
   * 返回该数组中和为 k 的连续子数组的个数
   * 输入：nums = [1,2,3], k = 3
   * 输出：2
   */
  public int subarraySum(int[] nums, int k) {
    // key:前缀和,value:题目为次数=次数
    Map<Integer, Integer> map = new HashMap<>();
    // 特判：前缀和为0，出现了1次
    map.put(0, 1);

    int sum = 0;
    int res = 0;
    for (int num : nums) {
      sum += num;
      // 先put再res，是不对的，因为k=0，导致sum-k=sum，
//      map.put(sum, map.getOrDefault(sum, 0) + 1);

      // sum[L-1] = k - sum[R],sum[L-1]就是曾经的某个出现的前缀和
      res += map.getOrDefault(sum - k, 0);
      // 因为是次数，必须更新次数
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {1, 1, 1};
    int k = 2;
    System.out.println(solution.subarraySum(nums, k));
  }
}
