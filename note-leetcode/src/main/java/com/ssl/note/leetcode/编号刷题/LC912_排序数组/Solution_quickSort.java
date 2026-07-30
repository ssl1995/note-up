package com.ssl.note.leetcode.编号刷题.LC912_排序数组;

import com.ssl.note.common.utils.ArrayUtil;

import java.util.Random;

public class Solution_quickSort {

  /**
   * LC912_排序数组
   * 三路快排
   */
  public int[] sortArray(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
    return nums;
  }

  // 三路快排
  private void quickSort(int[] nums, int l, int r) {
    if (l >= r) {
      return;
    }
    // 返回闭区间
    int[] arr = partition(nums, l, r);

    quickSort(nums, l, arr[0] - 1);
    quickSort(nums, arr[1] + 1, r);
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
    // 这个范围中的数已经到最终位置
    return new int[]{a, b};
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    Solution_quickSort solution = new Solution_quickSort();
    int times = 1001;
    while (times-- > 0) {
      int[] nums = {5, 1, 1, 2, 0, 0};
      int[] right = {0, 0, 1, 1, 2, 5};
      solution.sortArray(nums);
      boolean equals = ArrayUtil.isEquals(nums, right);
      if (!equals) {
        System.out.println("error");
        break;
      }
    }
  }


}
