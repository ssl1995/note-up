package com.ssl.note.leetcode.编号刷题.LC41_缺失的第一个正数;


public class Solution2 {

  /**
   * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。   *
   * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
   * 输入：nums = [3,4,-1,1]
   * 输出：2
   * 解释：1 在数组中，但 2 没有。
   */
  public int firstMissingPositive(int[] nums) {
    if (nums == null) {
      return 0;
    }

    // [0,l]是满足放好了正整数的区域
    int l = 0;
    // [l+1,r):1、垃圾区，2、期望满足的放好数字的上限
    int r = nums.length;

    while (l < r) {
      // 满足最好情况
      if (nums[l] == l + 1) {
        l++;
      } else if (nums[l] <= l || nums[l] > r || nums[l] == nums[nums[l] - 1]) {
        // 3个垃圾区
        swap(nums, l, --r);
      } else {
        // 交换期望位置的过来
        swap(nums, l, nums[l] - 1);
      }
    }

    return l + 1;
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int[] nums = {1, 1};
    System.out.println(solution.firstMissingPositive(nums));
  }

}
