package com.ssl.note.leetcode.编号刷题.LC34_在排序数组中查找第一个和最后一个位置;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/15 12:10 PM
 * @description
 */
public class Solution1 {

  /**
   * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
   * 如果数组中不存在目标值 target，返回 [-1, -1]。
   * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
   */
  public int[] searchRange(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return new int[]{-1, -1};
    }
    int first = getFirst(nums, target);
    int last = getLast(nums, target);
    return new int[]{first, last};
  }

  private int getFirst(int[] nums, int target) {
    int left = 0;
    int right = nums.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    // left指针越界 或者 第一个>=t的数可能不是t
    if (left == nums.length || nums[left] != target) {
      return -1;
    }
    return left;
  }

  private int getLast(int[] nums, int target) {
    int left = 0;
    int right = nums.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      // 最后一个<=t，反过来>t
      if (nums[mid] > target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    // left指针越界 或者最后一个>=t的数可能不是t
    if (left == 0 || nums[left - 1] != target) {
      return -1;
    }
    return left - 1;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {1, 1, 2};
    int t = 1;
    System.out.println(Arrays.toString(solution.searchRange(nums, t)));
  }
}
