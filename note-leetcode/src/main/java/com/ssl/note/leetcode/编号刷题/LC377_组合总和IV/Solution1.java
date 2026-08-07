package com.ssl.note.leetcode.编号刷题.LC377_组合总和IV;

public class Solution1 {

  /**
   * LC377_组合总和IV
   * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。
   * 请你从 nums 中找出并返回总和为 target 的元素组合的个数。
   * 题目数据保证答案符合 32 位整数范围。
   * 输入：nums = [1,2,3], target = 4
   * 输出：7
   * 解释：
   * 所有可能的组合为：
   * (1, 1, 1, 1)
   * (1, 1, 2)
   * (1, 2, 1)
   * (1, 3)
   * (2, 1, 1)
   * (2, 2)
   * (3, 1)
   * 请注意，顺序不同的序列被视作不同的组合。
   */
  private int res;

  public int combinationSum4(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    res = 0;
    dfs(nums, target);
    return res;
  }

  // 如果target较大，比如1000，递归层数很多，会超时
  private void dfs(int[] nums, int target) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res++;
      return;
    }
    for (int num : nums) {
      if (num > target) {
        continue;
      }

      dfs(nums, target - num);
    }
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {2, 1, 3};
    int target = 35;
    long start = System.currentTimeMillis();
    System.out.println(solution.combinationSum4(nums, target));
    long end = System.currentTimeMillis();
    // 画2s，会超时
    System.out.println("耗时，time=" + (end - start) / 1000 + "s");
  }
}
