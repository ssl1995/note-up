package com.ssl.note.leetcode.编号刷题.LC560_和为k的子数组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/2/27 11:00 AM
 * @description
 */
public class Solution {
  /**
   * 和为k的子数组
   * 返回该数组中和为 k 的连续子数组的个数
   * 输入：nums = [1,2,3], k = 3
   * 输出：2
   */
  public int subarraySum(int[] nums, int k) {
    int n = nums.length;
    // 数组记录前缀和
    int[] sum = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      sum[i] += sum[i - 1] + nums[i - 1];
    }

    int res = 0;
    // n^2的遍历，不友好
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        int temp = sum[i] - sum[j];
        if (temp == k) {
          res += 1;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 3};
    int k = 3;
    System.out.println(solution.subarraySum(nums, k));
  }
}
