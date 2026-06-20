package com.ssl.note.leetcode.编号刷题.LC322_零钱兑换;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/6/20 23:07
 * @description
 */
public class Solution {

  /**
   * 零钱兑换
   * =完全背包问题
   * 输入：coins = [1, 2, 5], amount = 11
   * 输出：3
   * 解释：11 = 5 + 5 + 1
   * 1 <= coins.length <= 12
   * 1 <= coins[i] <= 231 - 1
   * 0 <= amount <= 104
   */
  public int coinChange(int[] coins, int amount) {
    if (coins == null || coins.length == 0) {
      return -1;
    }
    // dp[i] 为凑成金额 i 所需的最少硬币数
    int[] dp = new int[amount + 1];
    // 由于是求最小值，如果用Integer.MAX_VALUE，+1会越界
    // 凑成金额 amount 最多只需要 amount 枚 1 元硬币（如果硬币里有 1 的话）
    // 所以 amount + 1 已经是一个"不可能达到"的值了。
    Arrays.fill(dp,amount+1);
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
      for (int j = 0; j < coins.length; j++) {
        // 金额够大才比较
        if (i >= coins[j]) {
          dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }

    return dp[amount] == amount+1 ? -1 : dp[amount];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] coins = {2};
    int t = 3;
    System.out.println(solution.coinChange(coins, t));
  }
}
