package com.ssl.note.leetcode.编号刷题.LC287_寻找重复数;

public class Solution {

  /**
   * 寻找重复数
   * 给定一个包含n + 1 个整数的数组nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。
   * 假设nums只有一个重复的整数 ，返回这个重复的数 。
   * 输入：nums = [1,3,4,2,2]
   * 输出：2
   */
  public int findDuplicate(int[] nums) {
    // 原地交换法的缺点：需要改变原数组
    // 最优解还是快慢指针找环
    int i = 0;
    while (i < nums.length) {
      if (i + 1 == nums[i]) {
        i++;
        continue;
      }
      if (nums[nums[i] - 1] == nums[i]) {
        return nums[i];
      }
      swap(nums, i, nums[i] - 1);
    }
    return -1;
  }

  private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 3, 4, 2, 2};
    // 2
    System.out.println(solution.findDuplicate(nums));
  }
}
