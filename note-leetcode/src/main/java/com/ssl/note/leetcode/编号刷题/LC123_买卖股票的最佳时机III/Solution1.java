package com.ssl.note.leetcode.编号刷题.LC123_买卖股票的最佳时机III;

public class Solution1 {

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

    int res = 0;
    int n = prices.length;
    // 超时解：两笔交易不能重叠，所以一定存在某个分割点i，完成2笔交易分成2个区间
    for (int i = 0; i < n; i++) {
      // 大量重复计算导致超时，思考预处理解法
      int max = maxProfitBase(prices, 0, i) + maxProfitBase(prices, i, n - 1);
      res = Math.max(max, res);
    }
    return res;
  }

  // LC121：只能交易一次，只记录发生的最大值即可
  public int maxProfitBase(int[] prices, int start, int end) {
    if (prices == null) {
      return 0;
    }
    int min = Integer.MAX_VALUE;
    int res = 0;

    for (int i = start; i <= end; i++) {
      int num = prices[i];
      min = Math.min(min, num);
      res = Math.max(res, num - min);
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};
    System.out.println(solution.maxProfit(nums));
  }
}
