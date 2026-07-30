package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Random;

public class Solution1 {

  /**
   * 数组中第k大的数
   * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   * 输出: 4
   */
  public int findKthLargest(int[] nums, int k) {
    // 第1大的数：n-1
    // 第k大的数：n-k
    int target = nums.length - k;
    return quickSelect(nums, 0, nums.length - 1, target);
  }

  private int quickSelect(int[] nums, int left, int right, int target) {
    while (true) {
      // (l,r)开区间写法
      int[] partition = partition(nums, left, right);
      int l = partition[0];
      int r = partition[1];

      if (l < target && target < r) {
        return nums[target];
      } else if (target <= l) {
        right = l;
      } else {
        left = r;
      }
    }
  }

  // 三路快排
  private int[] partition(int[] nums, int l, int r) {
    int random = new Random().nextInt(r - l + 1) + l;
    // 1、先保存基准值，不能用 nums[random]，否则交换后基准值会跟着变
    int pivot = nums[random];
    int a = l;
    int b = r;
    int i = l;
    // 2、扫描右边界是 b 而不是 r，i > b 时终止
    while (i <= b) {
      if (nums[i] < pivot) {
        swap(nums, i++, a++);
      } else if (nums[i] > pivot) {
        swap(nums, i, b--);
      } else {
        i++;
      }
    }
    // 开区间：等于基准的归位区间是 [a, b]，开区间边界必须是 {a-1, b+1}
    return new int[]{a - 1, b + 1};
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
    int[] nums = {3,2,1,5,6,4};
    int k = 2;
    int res = 5;
    Solution1 solution = new Solution1();
    System.out.println(solution.findKthLargest(nums, k) == res);
  }
}
