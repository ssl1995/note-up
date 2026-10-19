package com.ssl.note.leetcode.编号刷题.LC123_买卖股票的最佳时机III;

public class Solution2 {

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
    int n = prices.length;

    // 超时解进一步，预处理解法：正着扫一遍 + 反着扫一遍，降低时间复杂度
    int[] left = new int[n];
    int min = prices[0];
    for (int i = 1; i < n; i++) {
      min = Math.min(min, prices[i]);
      left[i] = Math.max(left[i - 1], prices[i] - min);
    }

    int[] right = new int[n];
    int max = prices[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      max = Math.max(max, prices[i]);
      right[i] = Math.max(right[i + 1], max - prices[i]);
    }

    int res = 0;
    for (int i = 0; i < n; i++) {
      res = Math.max(res, left[i] + right[i]);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};
    System.out.println(solution.maxProfit(nums));
  }
}
