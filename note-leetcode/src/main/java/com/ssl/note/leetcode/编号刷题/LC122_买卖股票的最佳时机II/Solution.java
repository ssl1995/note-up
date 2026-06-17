package com.ssl.note.leetcode.编号刷题.LC122_买卖股票的最佳时机II;

public class Solution {

  /**
   * LC122 买卖股票的最佳时机II
   * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
   * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。然而，你可以在 同一天 多次买卖该股票，但要确保你持有的股票不超过一股。   *
   * 返回 你能获得的 最大 利润 。
   * 示例 1：
   * 输入：prices = [7,1,5,3,6,4]
   * 输出：7
   * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4。
   * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3。
   * 最大总利润为 4 + 3 = 7 。
   */
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }
    int res = 0;
    // 题目：允许同一天多次买卖
    // 贪心：在低点买入、高点卖出=每天涨了都买卖一次的收入和是一样的
    for (int i = 1; i < prices.length; i++) {
      int diff = prices[i] - prices[i - 1];
      if (diff > 0) {
        res += diff;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {7, 1, 5, 3, 6, 4};
    System.out.println(solution.maxProfit(nums));
  }
}
