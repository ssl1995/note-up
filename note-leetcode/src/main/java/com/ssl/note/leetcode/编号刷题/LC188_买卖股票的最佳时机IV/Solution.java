package com.ssl.note.leetcode.编号刷题.LC188_买卖股票的最佳时机IV;

import java.util.Arrays;

public class Solution {

  /**
   * LC188 买卖股票的最佳时机IV
   * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
   * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
   * 示例 1：
   * 输入：k = 2, prices = [2,4,1]
   * 输出：2
   * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
   */
  public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    if (n < 2 || k == 0) {
      return 0;
    }
    // n天中，最多完成n/2笔交易，比如一天买、一天卖，
    // 1、k如果超过n/2,交易次数类似无限=贪心累加
    if (k >= n / 2) {
      return maxProfitNoLimit(prices);
    }
    // 2、状态机DP
    return maxProfitDP(k, prices);
  }

  private int maxProfitNoLimit(int[] prices) {
    int res = 0;
    for (int i = 1; i < prices.length; i++) {
      int diff = prices[i] - prices[i - 1];
      if (diff > 0) {
        res += diff;
      }
    }
    return res;
  }

  private int maxProfitDP(int k, int[] prices) {
    int[] buy = new int[k];
    int[] sell = new int[k];
    Arrays.fill(buy, -prices[0]);

    for (int num : prices) {
      for (int i = 0; i < k; i++) {
        int prevSell = i == 0 ? 0 : sell[i - 1];
        buy[i] = Math.max(buy[i], prevSell - num);
        sell[i] = Math.max(sell[i], buy[i] + num);
      }
    }
    return sell[k - 1];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 4, 1};
    int k = 2;
    System.out.println(solution.maxProfit(k, nums));
  }
}
