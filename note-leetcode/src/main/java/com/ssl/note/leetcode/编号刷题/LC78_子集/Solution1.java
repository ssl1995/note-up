package com.ssl.note.leetcode.编号刷题.LC78_子集;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/28 11:15 PM
 * @description
 */
public class Solution1 {

  /**
   * 子集
   * 数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）
   * 输入：nums = [1,2,3]
   * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
   */
  public List<List<Integer>> subsets(int[] nums) {
    int[] path = new int[nums.length];
    List<List<Integer>> res = new ArrayList<>();
    // 子集按任何顺序排列=需要排序
    Arrays.sort(nums);
    dfs(nums, 0, path,0, res);

    return res;
  }

  // 子集II的选or不选答案，也可以是子集I的答案
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
    Solution1 solution = new Solution1();
    int[] nums = {1, 2, 3};
    // [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    List<List<Integer>> res = solution.subsets(nums);
    System.out.println(res);
  }
}
