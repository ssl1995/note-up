package com.ssl.note.leetcode.编号刷题.LC47_全排列II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

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
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    boolean[] used = new boolean[nums.length];
    // 去重
    Arrays.sort(nums);
    backtrack(nums, used, path, res);

    return res;
  }

  private void backtrack(int[] nums, boolean[] used,
                         List<Integer> path, List<List<Integer>> res) {
    if (path.size() == nums.length) {
      res.add(new ArrayList<>(path));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      // 每次都从头开始选，选过的都跳过
      if (used[i]) {
        continue;
      }
      // 重复剪枝：used[i - 1]和 !used[i - 1]都行，但是后者的效率更高
      // !used[i - 1]:同一组重复元素必须按顺序使用，前一个没用就不用当前
      // used[i - 1]:同一层里相同元素只选一次
      if (i > 0 && nums[i - 1] == nums[i] && used[i - 1]) {
        continue;
      }

      path.add(nums[i]);
      used[i] = true;

      backtrack(nums, used, path, res);

      path.remove(path.size() - 1);
      used[i] = false;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = new int[]{1, 1, 2};
    List<List<Integer>> res = solution.permuteUnique(nums);
    System.out.println(res);
  }
}
