package com.ssl.note.leetcode.编号刷题.LC46_全排列;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/24 8:43 AM
 * @description
 */
public class Solution {
  /**
   * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列 。
   * 你可以 按任意顺序 返回答案。
   * 输入：nums = [1,2,3]
   * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   */
  public List<List<Integer>> permute(int[] nums) {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    // 不含重复数字，就使用used数组
    boolean[] used = new boolean[nums.length];
    backtrack(nums, used, path, res);

    return res;
  }

  // 全排列
  private void backtrack(int[] nums, boolean[] used,// 全排列不含重复，不从start开始，所以需要used数组
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
      path.add(nums[i]);
      used[i] = true;

      backtrack(nums, used, path, res);

      path.remove(path.size() - 1);
      used[i] = false;
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 3};
    List<List<Integer>> permute = solution.permute(nums);
    System.out.println(permute);
  }
}
