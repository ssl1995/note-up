package com.ssl.note.leetcode.编号刷题.LC121_买卖股票的最佳时机;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/15 2:56 PM
 * @Describe:
 */
public class Solution {

  /**
   * 买卖股票的最佳时机
   * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
   * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
   * 输入：[7,1,5,3,6,4]
   * 输出：5
   * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
   * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
   */
  public int maxProfit(int[] prices) {
    if (prices == null) {
      return 0;
    }
    int min = Integer.MAX_VALUE;
    int res = 0;

    // 贪心：每次遍历到一个数，局部最优解是之前的最小值-当前数就是最大利润
    for (int num : prices) {
      min = Math.min(min, num);
      if (num > min) {
        // LC121：只能交易一次，只记录发生的最大值即可
        res = Math.max(res, num - min);
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
