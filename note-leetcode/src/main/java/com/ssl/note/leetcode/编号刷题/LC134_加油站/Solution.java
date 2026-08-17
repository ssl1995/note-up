package com.ssl.note.leetcode.编号刷题.LC134_加油站;

public class Solution {

  /**
   * LC134_加油站
   * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
   * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
   * 输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
   * 输出: 3
   * 解释:
   * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
   * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
   * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
   * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
   * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
   * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
   * 因此，3 可为起始索引。
   */
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int n = gas.length;
    // l: 候选起点，r: 当前尝试到达的站，sum: 从 l 出发到 r 后油箱剩余油量
    // 外层 for 的更新式 l = r + 1, r = l 是精髓：
    //   从 l 出发最多只能开到 r（到不了 r+1），说明 [l, r] 内任何站做起点都失败
    //   （中途油箱从未为负，从中间出发只会更少），直接跳到 r+1 重新尝试
    for (int l = 0, r = 0, sum = 0; r < n; l = r + 1, r = l) {
      sum = 0;
      // r % n: 环路绕圈；只要还能开到下一站就继续走
      while (sum + gas[r % n] - cost[r % n] >= 0) {
        // 绕满一整圈回到 l，找到答案
        if (r - l + 1 == n) {
          return l;
        }
        sum += gas[r % n] - cost[r % n];
        r++;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] gas = {1, 2, 3, 4, 5};
    int[] cost = {3, 4, 5, 1, 2};
    System.out.println(solution.canCompleteCircuit(gas, cost));
  }
}
