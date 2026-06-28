package com.ssl.note.leetcode.编号刷题.LC41_缺失的第一个正数;


public class Solution {

  /**
   * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。   *
   * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
   * 输入：nums = [3,4,-1,1]
   * 输出：2
   * 解释：1 在数组中，但 2 没有。
   */
  public int firstMissingPositive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int n = nums.length;
    // 第一次遍历，放正确位置
    for (int i = 0; i < n; i++) {
      // 结果一定在[1,n]范围内，遇到负数就先跳过
      // nums[i] != i + 1：当前位置没放对，因为[1,1]这种重复的数据就会一直循环不能用
      // nums[i] != nums[nums[i] - 1]：目标位置还没放对，第一次循环用这个
      while (nums[i] >= 1 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
        // nums[i]正确的位置索引:nums[i]-1
        int temp = nums[nums[i] - 1];
        nums[nums[i] - 1] = nums[i];
        nums[i] = temp;
      }
    }
    // 第二次遍历才用i+1!= nums[i]
    for (int i = 0; i < n; i++) {
      // nums[i] != i + 1：当前位置没放对，第二次遍历就能用
      // nums[i] != nums[nums[i] - 1]：目标位置还没放对，如果遇到负数，目标位置会越界不能用
      if (i + 1 != nums[i]) {
        return i + 1;
      }
    }
    // 第二次遍历没找到
    // 说明是[1,2,3]，n=3，答案是4
    return n + 1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {3, 4, -1, 1};
    System.out.println(solution.firstMissingPositive(nums));
  }

}
