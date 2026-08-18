package com.ssl.note.leetcode.编号刷题.LC34_在排序数组中查找第一个和最后一个位置;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/15 12:10 PM
 * @description
 */
public class Solution3_error {

  /**
   * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
   * 如果数组中不存在目标值 target，返回 [-1, -1]。
   * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
   */
  public int[] searchRange(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return new int[]{-1, -1};
    }
    return partition(nums, 0, nums.length - 1, target);
  }

  /**
   * 荷兰国旗问题时间复杂度：O(n)，不符合要求
   * 力扣报错：Line 15: The while loop performs a linear scan (Dutch National Flag partition) over the array elements, resulting in O(n) time complexity.
   */
  private int[] partition(int[] nums, int l, int r, int t) {
    // 1、先保存基准值，不能用 nums[random]，否则交换后基准值会跟着变
    int a = l;
    int b = r;
    int i = l;
    // 2、扫描右边界是 b 而不是 r，i > b 时终止
    while (i <= b) {
      if (nums[i] < t) {
        swap(nums, i++, a++);
      } else if (nums[i] > t) {
        swap(nums, i, b--);
      } else {
        i++;
      }
    }
    if (a > b) {
      return new int[]{-1, -1};
    }
    // 这个范围中的数已经到最终位置
    return new int[]{a, b};
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    Solution3_error solution = new Solution3_error();
    int[] nums = {1};
    int t = 22;
    System.out.println(Arrays.toString(solution.searchRange(nums, t)));
  }
}
