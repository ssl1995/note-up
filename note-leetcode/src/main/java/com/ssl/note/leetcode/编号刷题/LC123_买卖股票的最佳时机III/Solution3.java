package com.ssl.note.leetcode.编号刷题.LC123_买卖股票的最佳时机III;

import java.util.Arrays;

public class Solution3 {

  /**
   * LC122 买卖股票的最佳时机III
   * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
   * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
   * 示例 1：
   * 输入：prices = [3,3,5,0,0,3,1,4]
   * 输出：6
   * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
   * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
   */
  public int maxProfit(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }
    int[] buy = new int[2];
    int[] sell = new int[2];
    Arrays.fill(buy, -prices[0]);

    for (int num : prices) {
      for (int i = 0; i < 2; i++) {
        int prevSell = i == 0 ? 0 : sell[i - 1];
        buy[i] = Math.max(buy[i], prevSell - num);
        sell[i] = Math.max(sell[i], buy[i] + num);
      }
    }
    return sell[1];
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};
    System.out.println(solution.maxProfit(nums));
  }
}
