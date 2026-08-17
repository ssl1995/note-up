package com.ssl.note.leetcode.编号刷题.LC209_长度最小的子数组;

public class Solution {

  /**
   * LC209_长度最小的子数组
   * 输入：target = 7, nums = [2,3,1,2,4,3]
   * 输出：2
   * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
   */
  public int minSubArrayLen(int target, int[] nums) {
    int res = Integer.MAX_VALUE;
    for (int l = 0, r = 0, sum = 0; r < nums.length; r++) {
      sum += nums[r];
      // while循环，持续把左边窗口减小
      while (sum - nums[l] >= target) {
        sum -= nums[l++];
      }
      // 上面是while，这里需要if
      if (sum >= target) {
        res = Math.min(res, r - l + 1);
      }
    }
    return res == Integer.MAX_VALUE ? 0 : res;
  }
}
