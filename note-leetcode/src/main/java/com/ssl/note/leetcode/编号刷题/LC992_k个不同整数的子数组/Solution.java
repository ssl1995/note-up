package com.ssl.note.leetcode.编号刷题.LC992_k个不同整数的子数组;

import java.util.Arrays;

public class Solution {

  /**
   * LC999_k个不同整数的子数组
   * 给定一个正整数数组 nums和一个整数 k，返回 nums 中 「好子数组」 的数目。
   * 如果 nums 的某个子数组中不同整数的个数恰好为 k，则称 nums 的这个连续、不一定不同的子数组为 「好子数组 」。
   * 例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。
   * 子数组 是数组的 连续 部分。
   * 输入：nums = [1,2,1,2,3], k = 2
   * 输出：7
   * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
   * 提示：
   * 1 <= nums.length <= 2 * 104
   * 1 <= nums[i], k <= nums.length
   *
   * 经验总结（怎么想到滑动窗口）：
   * 1. 滑动窗口（双指针不回退）的前提是被统计的性质有【单调性】：
   *    “不同整数个数 ≤ k”满足单调性——窗口右扩，种类数只增不减；左缩，只减不增。
   *    所以对每个 r，合法的 l 是一段连续区间，l 只需单调右移，总共 O(n)。
   * 2. “恰好 = k”本身没有单调性（扩一下可能从 k 变 k+1，缩一下又变回去），直接统计不好做。
   *    技巧：恰好k = 不超过k − 不超过k−1，把没有单调性的问题拆成两个有单调性的问题。
   *    同类题：LC1248 统计优美子数组（恰好k个奇数 = 最多k − 最多k−1）。
   */
  public int subarraysWithKDistinct(int[] nums, int k) {
    // 恰好k个 = 不超过k个 − 不超过k-1个
    return find(nums, k) - find(nums, k - 1);
  }

  private int MAX = 20001;
  private int[] cnts = new int[MAX];

  // arr中有多少子数组，数字种类不超过k
  private int find(int[] arr, int k) {
    Arrays.fill(cnts, 0);

    int res = 0;
    // collect: 当前窗口 [l, r] 内不同整数的个数
    for (int l = 0, r = 0, collect = 0; r < arr.length; r++) {
      // 右端进窗口：计数从 0 变 1，种类数 +1
      if (++cnts[arr[r]] == 1) {
        collect++;
      }

      // 种类数超了，左端收缩直到恢复 ≤ k（单调性保证 l 不用回退）
      while (collect > k) {
        if (--cnts[arr[l++]] == 0) {
          collect--;
        }
      }

      // 以 r 结尾且种类数 ≤ k 的子数组共 r-l+1 个：起点可以是 [l, r] 任意位置
      res += r - l + 1;
    }

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 1, 2, 3};
    int k = 2;
    System.out.println(solution.subarraysWithKDistinct(nums, k));
  }

}
