package com.ssl.note.leetcode.编号刷题.LC377_组合总和IV;

import java.util.Arrays;

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
  public int combinationSum4(int[] nums, int target) {
    // memo[t] 表示凑出 t 的排列个数，-1 表示还没算过
    int[] memo = new int[target + 1];
    Arrays.fill(memo, -1);
    // 边界：凑出0只有空序列这1种
    memo[0] = 1;

    return dfs(nums, target, memo);
  }

  private int dfs(int[] nums, int t, int[] memo) {
    // 算过的直接查缓存，避免重复展开递归树
    if (memo[t] != -1) {
      return memo[t];
    }
    int sum = 0;
    // 枚举序列的最后一个数num，累加凑出 t-num 的所有方案数
    for (int num : nums) {
      if (t >= num) {
        sum += dfs(nums, t - num, memo);
      }
    }
    // 结果落缓存后再返回
    memo[t] = sum;
    return sum;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {2, 1, 3};
    int target = 35;
    long start = System.currentTimeMillis();
    System.out.println(solution.combinationSum4(nums, target));
    long end = System.currentTimeMillis();
    System.out.println("耗时，time=" + (end - start) + "ms");
  }
}
