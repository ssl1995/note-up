package com.ssl.note.leetcode.编号刷题.LC41_缺失的第一个正数;

import java.util.HashSet;
import java.util.Set;

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
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }
    // 数组长度n，正整数范围:[1,n]
    int res = 1;
    while (set.contains(res)) {
      res++;
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {3, 4, -1, 1};
    System.out.println(solution.firstMissingPositive(nums));
  }

}
