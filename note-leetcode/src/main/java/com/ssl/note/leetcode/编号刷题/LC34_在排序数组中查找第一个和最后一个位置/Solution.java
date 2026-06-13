package com.ssl.note.leetcode.编号刷题.LC34_在排序数组中查找第一个和最后一个位置;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/15 12:10 PM
 * @description
 */
public class Solution {

  /**
   * 在排序数组中查找第一个和最后一个位置
   * 输入：nums = [5,7,7,8,8,10], target = 8
   * 输出：[3,4]
   */
  public int[] searchRange(int[] nums, int target) {
    if (nums.length == 0) {
      return new int[]{-1, -1};
    }
    return new int[]{findFirst(nums, target), findLast(nums, target)};
  }

  // 找第一个 >= target 的位置
  private int findFirst(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      // 找左边界：把 < target 的通通扔掉，剩下的第一个就是答案
      if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    // left 是第一个 >= target 的位置
    // 比如[1,2,3],t=5,此时left=4,需要判断：1、不越界。
    // 比如[1,3,5,6],t=4,此时left=2,需要判断：2、是否相等
    return (left < nums.length && nums[left] == target) ? left : -1;
  }

  // 找最后一个 <= target 的位置
  private int findLast(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      // 找右边界：把 <= target 的通通收下，最后一个收下的就是答案
      if (nums[mid] <= target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    // right 是最后一个 <= target 的位置
    return (right >= 0 && nums[right] == target) ? right : -1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 1, 2};
    int t = 1;
    System.out.println(Arrays.toString(solution.searchRange(nums, t)));
  }
}
