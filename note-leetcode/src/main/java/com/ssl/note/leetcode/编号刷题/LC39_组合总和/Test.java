package com.ssl.note.leetcode.编号刷题.LC39_组合总和;

import java.util.ArrayList;
import java.util.List;

public class Test {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    dfs(candidates, 0, target, path, res);

    return res;
  }

  private void dfs(int[] candidates, int start, int target, List<Integer> path, List<List<Integer>> res) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(path));
      return;
    }
    for (int i = start; i < candidates.length; i++) {
      if (candidates[i] > target) {
        continue;
      }
      path.add(candidates[i]);

      dfs(candidates, i,target - candidates[i],  path, res);

      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    Test test = new Test();
    int[] candidates = {2, 3, 6, 7};
    int target = 7;
    List<List<Integer>> res2 = solution1.combinationSum(candidates, target);
    List<List<Integer>> res1 = test.combinationSum(candidates, target);
    System.out.println(res2);
    System.out.println(res1);
  }
}
