package com.ssl.note.leetcode.编号刷题.LC303_区域和搜索;

public class NumArray {

  private final int[] sum;

  public NumArray(int[] nums) {
    int n = nums.length;
    // [1,n]表示前i个数字前缀和
    // 初始化n+1,sum[0]=0
    sum = new int[n + 1];

    for (int i = 1; i <= n; i++) {
      sum[i] = sum[i - 1] + nums[i - 1];
    }
  }

  public int sumRange(int left, int right) {
    // 初始化是n+1的写法
    return sum[right + 1] - sum[left];
  }
}
