package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Random;

public class Solution2 {

  /**
   * 数组中第k大的数
   * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   * 输出: 4
   */
  public int findKthLargest(int[] nums, int k) {
    int target = nums.length - k;
    return quickSelect(nums, 0, nums.length-1, target);
  }

  private int quickSelect(int[] nums, int left, int right, int target) {
    while (true) {
      int[] bound = partition(nums, left, right);
      int lt = bound[0];
      int gt = bound[1];

      if (target <= lt) {
        right = lt;       // 只改边界，不递归
      } else if (target > gt) {
        left = gt + 1;
      } else {
        return nums[target];
      }
    }
  }

  private int[] partition(int[] nums, int left, int right) {
    int randomIndex = left + new Random().nextInt(right - left + 1);
    swap(nums, randomIndex, right);

    int pivot = nums[right];
    int lt = left - 1;
    int gt = right;
    int i = left;

    while (i < gt) {
      if (nums[i] < pivot) {
        swap(nums, ++lt, i++);
      } else if (nums[i] > pivot) {
        swap(nums, --gt, i);
      } else {
        i++;
      }
    }
    swap(nums, gt, right);

    return new int[]{lt, gt};
  }

  private void swap(int[] nums, int i, int j) {
    // 避免自我交换
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 5, 6, 4};
    int k = 2;
    int res = 5;
    Solution2 solution = new Solution2();
    System.out.println(solution.findKthLargest(nums, k) == res);
  }
}
