package com.ssl.note.leetcode.编号刷题.LC309_最佳买卖股票时机含冷冻期;

/**
 * @author SongShengLin
 * @date 2022/2/23 4:54 PM
 * @description
 */
public class Solution1 {

  /**
   * 最佳买卖股票时机含冷冻期
   * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格
   * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
   * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
   * 输入: prices = [1,2,3,0,2]
   * 输出: 3
   * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
   */
  public int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }

    int buy = -prices[0];  // 持有
    int sellDown = 0;      // 不持有-冷冻
    int sellNoDown = 0;    // 不持有-可买

    for (int price : prices) {
      int preBuy = buy;
      int preDown = sellDown;
      int preSell = sellNoDown;
      // 持有
      buy = Math.max(preBuy, preSell - price);
      // 不持有，但冷冻期：昨天持有，今天卖出
      sellDown = preBuy + price;
      // 不持有，过了冷冻期
      sellNoDown = Math.max(preSell, preDown);
    }

    return Math.max(sellDown, sellNoDown);
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {2, 1};
    System.out.println(solution.maxProfit(nums));
  }
}
