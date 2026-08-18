package com.ssl.note.leetcode.编号刷题.LC475_供暖器;

import java.util.Arrays;

public class Solution {

  /**
   * LC475_供暖器
   * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
   * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
   * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
   * 注意：所有供暖器 heaters 都遵循你的半径标准，加热的半径也一样。
   * 输入: houses = [1,2,3,4], heaters = [1,4]
   * 输出: 1
   * 解释: 在位置 1, 4 上有两个供暖器。我们需要将加热半径设为 1，这样所有房屋就都能得到供暖。
   */
  public int findRadius(int[] houses, int[] heaters) {
    // 房屋和供暖器排序
    Arrays.sort(houses);
    Arrays.sort(heaters);

    int res = 0;
    for (int i = 0, j = 0; i < houses.length; i++) {
      // i号房屋是否对j号供暖器取到最优
      // 单调性：房屋越靠右，其最优供暖器的下标不可能变小
      while (!best(houses, heaters, i, j)) {
        j++;
      }
      // 所有房屋都供上暖，取最大值
      res = Math.max(res, Math.abs(houses[i] - heaters[j]));
    }

    return res;
  }

  // i房屋到达j位置是否是最优？
  // a < b 说明最优，不该跳下一个；a >= b 说明应跳下一个位置
  private boolean best(int[] houses, int[] heaters, int i, int j) {
    return j == heaters.length - 1
        ||
        Math.abs(houses[i] - heaters[j]) < Math.abs(houses[i] - heaters[j + 1]);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] houses = {1, 2, 3, 4};
    int[] heaters = {1, 4};
    System.out.println(solution.findRadius(houses, heaters));
  }
}
