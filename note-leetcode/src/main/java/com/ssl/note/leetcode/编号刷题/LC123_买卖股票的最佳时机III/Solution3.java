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
    return getKTrade(prices, 2);
  }

  // 从交易进度思考
  // 交易次数：2次。交易状态：买入、卖出
  // 假设交易k次，通用化
  private int getKTrade(int[] prices, int k) {
    int[] buy = new int[k];
    int[] sell = new int[k];
    // 假设k=2，初始化buy数组，第 0 天买入并卖出第 1 笔（收益 0），
    // 紧接着第 0 天再买入第 2 笔 → 净收益 = 0 - prices[0] = -prices[0]
    Arrays.fill(buy, -prices[0]);

    for (int num : prices) {
      for (int i = 0; i < k; i++) {
        // 每来一个新价格，对每笔交易问一遍：保持原状，还是今天操作？
        int pre = i == 0 ? 0 : sell[i - 1];
        // 保持不买 vs 用上一笔赚的钱今天买入（花 num 元）
        buy[i] = Math.max(buy[i], pre - num);
        // 保持不卖 vs 今天卖出（收入 num 元）
        sell[i] = Math.max(sell[i], buy[i] + num);
      }
    }

    return sell[k - 1];
  }

  // 补充：如果允许同一天买卖，必须交易k次=至多交易k次，代码用上面的和下面的都行
  public int getKTrade2(int[] prices, int k) {
    if (prices.length <= 1) {
      return 0; // 恰好 k 笔至少需要 k 天（同日买卖算 1 笔）；天数不足按题意返回
    }
    // -INF 哨兵：状态不可达；除以 2 防止 buy[i] + num 溢出
    int NEG = Integer.MIN_VALUE / 2;

    int[] buy = new int[k];
    int[] sell = new int[k];
    Arrays.fill(buy, NEG);
    Arrays.fill(sell, NEG);
    // 只有 buy[0] 第 0 天可达：买入第 1 笔，成本 prices[0]
    // buy[1..k-1] 不初始化（保持 NEG），等循环里通过 sell[i-1] 接力才变得可达
    buy[0] = -prices[0];

    for (int num : prices) {
      for (int i = 0; i < k; i++) {
        int pre = i == 0 ? 0 : sell[i - 1]; // 第 1 笔买入不需要前置交易，仍为 0
        buy[i] = Math.max(buy[i], pre - num);
        sell[i] = Math.max(sell[i], buy[i] + num);
      }
    }
    // 必须完成 k 笔：sell[k-1] 仍为 NEG 说明天数不足，无法完成
    return sell[k - 1] == NEG ? 0 : sell[k - 1];
  }

  // 补充：如果不允许同一天买卖，必须交易k次!=至多交易k次，代码如下
  public int getKTrade3(int[] prices, int k) {
    int n = prices.length;
    // 每笔交易买入日 < 卖出日，至少占 2 天；n 不够 2k 必然无解
    if (n < 2 * k) {
      return 0; // 无解，按题意返回（也可返回 -1 表示不可行）
    }
    // -INF 哨兵：状态不可达；除以 2 防止 buy[i] + num 溢出
    int NEG = Integer.MIN_VALUE / 2;
    int[] buy = new int[k];
    int[] sell = new int[k];
    Arrays.fill(buy, NEG);
    Arrays.fill(sell, NEG);
    // 只有 buy[0] 第 0 天可达：买入第 1 笔，成本 prices[0]
    buy[0] = -prices[0];

    // 与 getKTrade2 的唯一区别：所有转移必须基于"昨天"的状态，当天不能连环操作
    // 技巧：倒序遍历 + 先卖后买 —— sell[i] 用的 buy[i]、buy[i] 用的 sell[i-1] 都还没被今天更新
    // 从第 2 天（idx=1）开始转移，第 0 天只有买入初始化
    for (int idx = 1; idx < n; idx++) {
      int num = prices[idx];
      for (int i = k - 1; i >= 0; i--) {
        // 卖出第 i+1 笔：buy[i] 还是昨天的值 → 买入日严格早于卖出日
        sell[i] = Math.max(sell[i], buy[i] + num);
        // 买入第 i+1 笔：倒序下 sell[i-1] 尚未被今天更新 → 上一笔卖出日也严格早于今天
        int pre = i == 0 ? 0 : sell[i - 1]; // 第 1 笔买入不需要前置交易，仍为 0
        buy[i] = Math.max(buy[i], pre - num);
      }
    }
    return sell[k - 1];
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};
    // buy/sell 状态推演（每行 = 当天循环结束后的数组值）：
    // 初始:  buy={-3,-3} sell={0,0}   第0天买入成本
    // num=3: buy={-3,-3} sell={0,0}   当天买卖收益 0，不亏不赚
    // num=3: buy={-3,-3} sell={0,0}   同上
    // num=5: buy={-3,-3} sell={2,2}   3买5卖收益2；buy[1]=max(-3, sell[0]-5=-3) 不变
    // num=0: buy={ 0, 2} sell={2,2}   buy[0]: 0元买入更优(0>-3)；buy[1] 接 sell[0]=2 买入 → 2
    // num=0: buy={ 0, 2} sell={2,2}   无更优操作，保持
    // num=3: buy={ 0, 2} sell={3,5}   sell[0]: 0买3卖=3；sell[1]: buy[1]=2 → 2+3=5
    // num=1: buy={ 0, 2} sell={3,5}   buy[1]=max(2, sell[0]-1=2) 不变，无更优操作
    // num=4: buy={ 0, 2} sell={4,6}   sell[1]: 2+4=6 ← 答案（0买3卖、1买4卖 = 3+3）
    System.out.println(solution.maxProfit(nums));

    // getKTrade2（允许同日买卖）vs getKTrade3（禁止同日买卖）差异验证：
    // 原例：最优解本身就是 2 笔真实交易，两版答案一致
    System.out.println(solution.getKTrade2(nums, 2)); // 6
    System.out.println(solution.getKTrade3(nums, 2)); // 6
    // {1,2,3,4}：getKTrade2 可用同日空气交易凑数（1买4卖=3 + 0收益凑1笔）；
    // getKTrade3 必须 2 笔真实不重叠交易，最优 (1→2)+(3→4) = 1+1 = 2
    System.out.println(solution.getKTrade2(new int[]{1, 2, 3, 4}, 2)); // 3
    System.out.println(solution.getKTrade3(new int[]{1, 2, 3, 4}, 2)); // 2
    // 天数不足 2k：getKTrade3 无解，按约定返回 0
    System.out.println(solution.getKTrade3(new int[]{3, 5}, 2)); // 0（n=2 < 2k=4，不可行）
  }
}
