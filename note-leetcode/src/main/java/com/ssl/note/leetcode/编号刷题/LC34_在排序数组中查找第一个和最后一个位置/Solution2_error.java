package com.ssl.note.leetcode.编号刷题.LC34_在排序数组中查找第一个和最后一个位置;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/15 12:10 PM
 * @description
 */
public class Solution2_error {

  /**
   * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
   * 如果数组中不存在目标值 target，返回 [-1, -1]。
   * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
   */
  public int[] searchRange(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return new int[]{-1, -1};
    }
    if (nums.length == 1 && target == nums[0]) {
      return new int[]{0, 0};
    }
    int index1 = getFirst(nums, target);
    int index2 = getLast(nums, target);
    return new int[]{index1, index2};
  }

  private int getFirst(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int res = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= target) {
        res = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    if (res == -1 || nums[res] != target) {
      return -1;
    }
    return res;
  }

  // <=t的最后一个位置 转成 >t的第一个位置
  private int getLast(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int res = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      // <=t的最后一个位置，反过来>t的前一个位置
      if (nums[mid] > target) {
        res = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    // 错误：如果nums全是=t的，找>t+1的第一个位置，res的写法返回-1，无法区分是返回0还是-1
    if (res == -1 || res == 0 || nums[res - 1] != target) {
      return -1;
    }
    return res - 1;
  }

  public static void main(String[] args) {
    Solution2_error solution = new Solution2_error();
    int[] nums = {1};
    int t = 1;
    System.out.println(Arrays.toString(solution.searchRange(nums, t)));
  }
}
