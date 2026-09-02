package com.ssl.note.leetcode.编号刷题.LC75_颜色分类;

import java.util.Arrays;

public class Solution1 {

  /**
   * 颜色分类：0-红，1-白，2-蓝色
   * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列
   * 输入：nums = [2,0,2,1,1,0]
   * 输出：[0,0,1,1,2,2]
   */
  public void sortColors(int[] nums) {
    if (nums == null || nums.length == 0) {
      return;
    }
    // 一次partition，常数空间复杂度
    partition(nums, 0, nums.length - 1, 1);
  }

  // 荷兰国旗问题
  private void partition(int[] nums, int l, int r, int v) {
    int a = l, b = r, i = l;
    while (i <= b) {
      if (nums[i] < v) {
        swap(nums, a++, i++);
      } else if (nums[i] > v) {
        swap(nums, b--, i);
      } else {
        i++;
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {2, 0, 2, 1, 1, 0};
    solution.sortColors(nums);
    System.out.println(Arrays.toString(nums));
  }
}
