package com.ssl.note.leetcode.编号刷题.LC216_组合总和III;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
   * 只使用数字1到9
   * 每个数字 最多使用一次
   * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
   * 输入: k = 3, n = 7
   * 输出: [[1,2,4]]
   * 解释:
   * 1 + 2 + 4 = 7
   * 没有其他符合的组合了。
   */
  public List<List<Integer>> combinationSum3(int k, int n) {
    if (k == 0) {
      return new ArrayList<>();
    }
    int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    dfs(nums, 0, n, k, path, res);

    return res;
  }

  private void dfs(int[] nums, int i, int n, int k, List<Integer> path, List<List<Integer>> res) {
    if (n < 0) {
      return;
    }
    if (n == 0 && k == 0) {
      res.add(new ArrayList<>(path));
      return;
    }
    for (int j = i; j < nums.length; j++) {
      // 剪枝1：当前数字和>n
      // 剪枝2:自己+剩下的数不够k个
      if (nums[j] > n || nums.length - j < k) {
        continue;
      }

      path.add(nums[j]);

      dfs(nums, j + 1, n - nums[j], k - 1, path, res);

      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int k = 3;
    int n = 7;
    System.out.println(solution.combinationSum3(k, n));
  }
}
