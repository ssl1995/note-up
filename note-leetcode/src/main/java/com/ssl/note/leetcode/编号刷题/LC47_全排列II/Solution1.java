package com.ssl.note.leetcode.编号刷题.LC47_全排列II;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution1 {

  /**
   * LC47_全排列II
   * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
   * 示例 1：
   * 输入：nums = [1,1,2]
   * 输出：
   * [[1,1,2],
   * [1,2,1],
   * [2,1,1]]
   */
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    process(nums, 0, res);
    return res;
  }

  // 回溯：原地交换方式
  private void process(int[] nums, int i, List<List<Integer>> res) {
    if (i == nums.length) {
      List<Integer> temp = new ArrayList<>();
      for (int num : nums) {
        temp.add(num);
      }
      res.add(temp);
      return;
    }
    // 全排列，固定一个位置，递归后续位置
    // nums含有重复元素
    Set<Integer> set = new HashSet<>();
    for (int j = i; j < nums.length; j++) {
      if (!set.contains(nums[j])) {
        set.add(nums[j]);
        // 交换i，j位置
        swap(nums, i, j);

        process(nums, i + 1, res);

        // 回溯：i,j位置归位
        swap(nums, i, j);
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = new int[]{1, 1, 2};
    List<List<Integer>> res = solution.permuteUnique(nums);
    System.out.println(res);
  }
}
