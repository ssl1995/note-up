package com.ssl.note.leetcode.编号刷题.LC300_最长递增子序列;

import java.util.Arrays;

public class Test {

  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    // dp[i]:以nums[i]结尾的最长递增序列
    int[] dp = new int[n];
    // 每个数的最低长度=1
    Arrays.fill(dp, 1);

    int max = 0;
    for (int i = 0; i < n; i++) {
      // [1,5,2,3]：只需要比选中的那个子序列的末尾大，不需要比前面所有元素都大
      // 所以不能单纯的计算一个max，而是需要遍历[0,j],j<i
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      max = Math.max(max, dp[i]);
    }
    // 不能直接返回dp[n-1]
    // 比如[1,2,3,4,0],dp[n-1]=0
    return max;
  }

  public static void main(String[] args) {
    Test test = new Test();
    int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
    int res = 4;
    System.out.println(test.lengthOfLIS(nums) == res);
  }
}
