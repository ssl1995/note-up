package com.ssl.note.leetcode.编号刷题.LC35_搜素插入的位置;

public class Solution {

  /**
   * 搜索插入的位置
   * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
   * 请必须使用时间复杂度为 O(log n) 的算法。
   * 输入: nums = [1,3,5,6], target = 5
   * 输出: 2
   * 本题翻译：找>=t的第一个下标，时间复杂度O(logN)
   */
  public int searchInsert(int[] nums, int target) {
    // 左闭右开模板：[0,n)，涵盖"插入到末尾 n"答案
    int left = 0;
    int right = nums.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= target) {
        //  mid 满足条件 → 答案在左半(含mid)，right=mid 不丢解
        right = mid;
      } else {
        // mid 不满足 → 答案必在右半，left=mid+1
        left = mid + 1;
      }
    }
    // 1、右指针如果是初始化n-1
    // int right = nums.length - 1;
    // 2、结果就得多判断一步
    // nums=[1,3,5,6],t=7时，此时nums[left]=6
    // t=7应该插到末尾，所以需要特判一下
//    if (nums[left] < target) {
//      return left + 1;
//    }
    return left;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 3, 5, 6};
    int target = 7;
    System.out.println(solution.searchInsert(nums, target));
  }

}
