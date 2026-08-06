package com.ssl.note.leetcode.编号刷题.LC90_子集II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  /**
   * LC90_子集II
   * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
   * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
   * 示例 1：
   * 输入：nums = [1,2,2]
   * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
   */
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    }
    // 子集按任何顺序排列=需要排序
    Arrays.sort(nums);

    int[] path = new int[nums.length];
    List<List<Integer>> res = new ArrayList<>();
    dfs(nums, 0, path, 0, res);

    return res;
  }

  private void dfs(int[] nums, int i, int[] path, int size, List<List<Integer>> res) {
    if (i == nums.length) {
      List<Integer> temp = new ArrayList<>();
      for (int j = 0; j < size; j++) {
        temp.add(path[j]);
      }
      res.add(temp);
      return;
    }
    // 下一组数的第一个
    int j = i + 1;
    while (j < nums.length && nums[j] == nums[j - 1]) {
      j++;
    }
    // nums[i...j..]在[i,j)中选0个加入
    dfs(nums, j, path, size, res);
    // nums[i...j..]在[i,j)中选任意个加入
    for (int k = 0; k < j - i; k++) {
      path[size++] = nums[i + k];
      dfs(nums, j, path, size, res);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 2};
    List<List<Integer>> res = solution.subsetsWithDup(nums);
    res.sort((a, b) -> {
      int n = Math.min(a.size(), b.size());
      for (int i = 0; i < n; i++) {
        int cmp = Integer.compare(a.get(i), b.get(i));
        if (cmp != 0) {
          return cmp;
        }
      }
      // 前缀相同，短的排前面
      return Integer.compare(a.size(), b.size());
    });
    // [[],[1],[1,2],[1,2,2],[2],[2,2]]
    System.out.println(res);
  }
}
