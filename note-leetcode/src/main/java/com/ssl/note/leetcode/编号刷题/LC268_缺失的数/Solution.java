package com.ssl.note.leetcode.编号刷题.LC268_缺失的数;

public class Solution {

  /**
   * LC268_缺失的数
   * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
   * 输入：nums = [3,0,1]
   * 输出：2
   * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
   */
  public int missingNumber(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0] == 0 ? 1 : 0;
    }
    // a=[0,n]下标异或
    int a = 0;
    // b=数组每个数异或
    int b = 0;
    for (int i = 0; i < n; i++) {
      a ^= i;
      b ^= nums[i];
    }
    // a再补一个异或n，才能完整
    a ^= n;
    // 异或的交换律，相同的数异或=0，缺失的数会被留下
    return a ^ b;
  }
}
