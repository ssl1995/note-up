package com.ssl.note.leetcode.编号刷题.LC121_买卖股票的最佳时机;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/15 2:56 PM
 * @Describe:
 */
public class Solution1 {

  /**
   * 买卖股票的最佳时机
   * 输入：[7,1,5,3,6,4]
   * 输出：5
   * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
   * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
   */
  public int maxProfit(int[] prices) {
    if (prices == null) {
      return 0;
    }
    // 股票 + 最大利润 + 有限交易次数 = 状态机DP
    int hold1 = -prices[0];
    int sold1 = 0;
    for (int price : prices) {
      hold1 = Math.max(hold1, -price);
      sold1 = Math.max(sold1, hold1 + price);
    }
    return sold1;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {7, 1, 5, 3, 6, 4};
    System.out.println(solution.maxProfit(nums));
  }
}
