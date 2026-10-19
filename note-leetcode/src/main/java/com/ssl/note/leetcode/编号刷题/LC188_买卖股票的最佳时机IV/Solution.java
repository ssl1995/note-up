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
    // 力扣的k<100,n<1000,k<n
    // k<n且量级不大，LC123通用化
    return getKTrade(prices, k);
  }

  // LC123买卖股票III,通用化
  private int getKTrade(int[] prices, int k) {
    int[] buy = new int[k];
    int[] sell = new int[k];
    Arrays.fill(buy, -prices[0]);

    for (int num : prices) {
      for (int i = 0; i < k; i++) {
        int pre = i == 0 ? 0 : sell[i - 1];
        buy[i] = Math.max(buy[i], pre - num);
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
