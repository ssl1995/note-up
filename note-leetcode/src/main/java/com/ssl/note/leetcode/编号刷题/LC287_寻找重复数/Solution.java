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
    if (nums == null || nums.length == 0) {
      return -1;
    }
    // 题目要求空间复杂度O(1)，就不能用哈希表方法
    int n = nums.length;
    // 长度n：num
    // 长度n-1:num-1
    int[] count = new int[n];

    for (int num : nums) {
      count[num]++;

      if (count[num] > 1) {
        return num;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 3, 4, 2, 2};
    System.out.println(solution.findDuplicate(nums));
  }
}
