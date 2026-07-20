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
    // 1、k大当无限，贪心随便买
    if (k >= n / 2) {
      return maxProfit2(prices);
    }
    // 2、k小要计数，用状态机
    return maxProfit3(k, prices);
  }

  // LC122买卖股票II
  private int maxProfit2(int[] prices) {
    // min=谷底，同时也是本轮买入价
    int min = Integer.MAX_VALUE;
    int res = 0;

    for (int num : prices) {
      // 谷底买入
      min = Math.min(min, num);
      if (num > min) {
        // LC122:多次交易，涨了就卖
        res += num - min;
        // 卖了后，立马买入，等待下一波上涨
        min = num;
      }
    }

    return res;
  }

  // LC123买卖股票III
  private int maxProfit3(int k, int[] prices) {
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
