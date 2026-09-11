package com.ssl.note.leetcode.编号刷题.LC416_分割等和子集;

import java.util.Arrays;

public class Solution1 {
  /**
   * 分割等和子集
   * 将这个数组分割成两个子集，使得两个子集的元素和相等。
   * 输入：nums = [1,5,11,5]
   * 输出：true
   * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
   */
  public boolean canPartition(int[] nums) {
    // 边界检查：数组为空直接返回false
    if (nums == null) {
      return false;
    }
    // 如果总和是奇数，无法分割成两个和相等的子集，直接返回false
    int sum = Arrays.stream(nums).sum();
    // 换成:(sum % 2) != 0
    if ((sum & 1) == 1) {
      return false;
    }
    // 目标值为总和的一半
    int target = sum / 2;
    // 目标转成：nums中是否有子序列是target
    // dp数组：dp[j]能够凑出和为j的子集
    boolean[] dp = new boolean[target + 1];
    // 初始化：和为0不需要任何子集=true
    dp[0] = true;
    // 遍历数组中的每个元素
    for (int num : nums) {
      // 从target开始倒序遍历，避免重复选择同一元素 =01背包
      for (int j = target; j >= num; j--) {
        // 状态转移方程：
        // dp[j] = dp[j] || dp[j - num]
        // dp[j]:表示不选当前元素时的状态，或选
        // dp[j - num]:当前元素时的状态（即j - num是否可以实现）
        dp[j] = dp[j] || dp[j - num];
      }
    }
    return dp[target];
  }

  /**
   * 变体：dp数组长度初始化为 target（而不是 target + 1）
   * 核心变化：下标需要偏移1位
   * - 原写法：dp[j] 表示「能否凑出和为 j」，下标 0~target
   * - 本写法：dp[j-1] 表示「能否凑出和为 j」，下标 0~target-1
   * - 因此「和为0」没有对应下标，需要在状态转移时单独判断 j - num == 0 的情况
   */
  public boolean canPartition2(int[] nums) {
    if (nums == null) {
      return false;
    }
    int sum = Arrays.stream(nums).sum();
    if ((sum & 1) == 1) {
      return false;
    }
    int target = sum / 2;

    // dp[j-1]：能否凑出和为 j（j 范围 1~target）
    boolean[] dp = new boolean[target];
    // 注意：dp[0]=true 的初始化没有了，因为「和为0」在数组中没有位置

    for (int num : nums) {
      for (int j = target; j >= num; j--) {
        // j - num == 0：当前元素 num 自己就能凑出和 j（相当于原写法的 dp[0]=true）
        // j - num > 0：看「和为 j-num」是否可达，即 dp[j-num-1]
        dp[j - 1] = dp[j - 1] || (j - num == 0 || dp[j - num - 1]);
      }
    }

    // 「和为 target」对应下标 target-1
    return dp[target - 1];
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    int[] nums = {1, 5, 11, 5};
    // dp:[ true, true, false, false, false, true, true, false, false, false, true, true ]
    System.out.println(solution1.canPartition(nums));
  }
}