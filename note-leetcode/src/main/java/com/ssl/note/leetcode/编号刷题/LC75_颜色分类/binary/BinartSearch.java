package com.ssl.note.leetcode.编号刷题.LC75_颜色分类.binary;

public class BinartSearch {

  /**
   * 二分查找，学习定义区间
   */
  private int binartSearch1(int[] nums, int target) {
    // 定义区间:[0,left)[left,right](right,n-1]
    int left = 0, right = nums.length - 1;      // 已确定区为空

    while (left <= right) {           // 未知区非空
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        // mid 已排除，所以 -1
        right = mid - 1;
      }
    }
    return left;
  }

  private int binartSearch2(int[] nums, int target) {
    // 定义区间:[0,left)[left,right)[right,n-1]
    int left = 0, right = nums.length;          // 已确定区为空
    while (left < right) {            // 未知区非空
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        // mid 可能是答案，保留
        right = mid;
      } else {
        // mid 不是答案，踢出
        left = mid + 1;
      }
    }
    return left;
  }

}
