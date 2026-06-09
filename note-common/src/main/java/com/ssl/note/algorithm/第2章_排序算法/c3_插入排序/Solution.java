package com.ssl.note.algorithm.第2章_排序算法.c3_插入排序;

import java.util.Arrays;

public class Solution {

  /**
   * 插入排序
   * 稳定性：稳定
   */
  public void sort(int[] nums) {
    if (nums == null) {
      return;
    }
    int n = nums.length;
    for  (int i = 1; i < n; i++) {
      int key = nums[i];
      int j = i - 1;
      for (; j >= 0 && nums[j] > key; j--) {
        nums[j + 1] = nums[j];
      }
      nums[j + 1] = key;
    }
  }

  public static void main(String[] args) {
    int[] nums=new int[]{2,5,4,3,6,1};
    Solution solution = new Solution();
    solution.sort(nums);
    System.out.println(Arrays.toString(nums));
  }

}
