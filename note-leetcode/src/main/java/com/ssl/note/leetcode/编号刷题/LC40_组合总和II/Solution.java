package com.ssl.note.leetcode.编号刷题.LC40_组合总和II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  /**
   * LC40_组合总和II
   * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
   * candidates 中的每个数字在每个组合中只能使用 一次 。
   * 注意：解集不能包含重复的组合。
   * 示例 1:
   * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
   * 输出:
   * [
   * [1,1,6],
   * [1,2,5],
   * [1,7],
   * [2,6]
   * ]
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    if (candidates == null || candidates.length == 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    // 对原始数组进行排序
    Arrays.sort(candidates);
    dfs(candidates, 0, target, temp, res);
    return res;
  }

  private void dfs(int[] candidates, int i, int target, List<Integer> temp, List<List<Integer>> res) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(temp));
      return;
    }
    // 还是从j开始
    for (int j = i; j < candidates.length; j++) {
      // 排序，剪枝优化
      if (candidates[j] > target) {
        break;
      }

      // 本轮for中后面的数和前面的相同，要跳过
      // 但是不会影响下一个递归中相同的数字
      if (i != j && candidates[j] == candidates[j - 1]) {
        continue;
      }

      temp.add(candidates[j]);
      // 每个数字只能出现一次：i+1
      dfs(candidates, j + 1, target - candidates[j], temp, res);
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {10, 1, 2, 7, 6, 1, 5};
    int target = 8;
    System.out.println(solution.combinationSum2(nums, target));
  }
}
