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
   *
   * LC78子集通用模板写法：每个节点都收集 + for从start开始
   * 相比LC78只多一步：同层去重（和LC40组合总和II的去重方式完全一样）
   */
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    }
    // 排序让相同元素相邻，是去重的前提
    Arrays.sort(nums);

    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    dfs(nums, 0, path, res);

    return res;
  }

  private void dfs(int[] nums, int i, List<Integer> path, List<List<Integer>> res) {
    // 子集问题：一进来就收集（每个递归节点都是一个合法子集，包括空集[]）
    res.add(new ArrayList<>(path));

    for (int j = i; j < nums.length; j++) {
      // 同层去重：i != start 说明是本轮for中后面的数，和前面相同则跳过
      // 不影响下一层递归中选相同的数（如[1,2,2]中两个2都进子集）
      if (j != i && nums[j] == nums[j - 1]) {
        continue;
      }

      path.add(nums[j]);
      // 每个元素最多用一次：i+1
      dfs(nums, j + 1, path, res);
      path.remove(path.size() - 1);
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
