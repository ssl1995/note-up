package com.ssl.note.leetcode.编号刷题.LC322_零钱兑换;

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
   * 输入：coins = [2], amount = 3
   * 输出：-1
   */
  public int coinChange(int[] coins, int amount) {
    // dp[i] 为凑成金额i所需的最少硬币数
    int[] dp = new int[amount + 1];
    // 初始化填充-1表示筹不出/尚未计算
    Arrays.fill(dp, -1);
    // 0元需要0个硬币
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if (i - coin >= 0 && dp[i - coin] != -1) {
          // dp默认是-1，需要特判
          if (dp[i] == -1) {
            dp[i] = dp[i - coin] + 1;
          } else {
            // 不是-1，就取最小值
            dp[i] = Math.min(dp[i], dp[i - coin] + 1);
          }
        }
      }
    }
    return dp[amount];
  }

  // 不用特判版本
  public int coinChange1(int[] coins, int amount) {
    if (coins == null || coins.length == 0) {
      return -1;
    }
    // dp[i] 为凑成金额 i 所需的最少硬币数
    int[] dp = new int[amount + 1];
    // 为了减少特判：dp[i]=-1取最小需要特判
    // 思考dp[i]的最大值有没有，有就是金额本身
    // 考虑初始化为amount+1或者整形最大，但是整形最大+1会越界
    // 所以 amount + 1 已经是一个"不可能达到"的值了。
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if (i >= coin) {
          // 这里就不需要特判
          dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
      }
    }

    return dp[amount] == amount + 1 ? -1 : dp[amount];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] coins = {2};
    int t = 3;
    System.out.println(solution.coinChange(coins, t) == solution.coinChange1(coins, t));
  }
}
