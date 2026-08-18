package com.ssl.note.leetcode.编号刷题.LC881_救生艇;

import java.util.Arrays;

public class Solution {

  /**
   * LC881_救生艇
   * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
   * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
   * 返回 承载所有人所需的最小船数 。
   * 输入：people = [3,2,2,1], limit = 3
   * 输出：3
   * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
   */
  public int numRescueBoats(int[] people, int limit) {
    if (people == null) {
      return 0;
    }
    Arrays.sort(people);
    int n = people.length;
    int left = 0;
    int right = n - 1;
    int res = 0;

    while (left <= right) {
      int sum = left == right ? people[left] : people[left] + people[right];

      // 累加和小，两端都入一艘船
      // 累加和大，最右端入一艘船
      if (sum <= limit) {
        left++;
      }
      right--;

      res++;
    }

    return res;
  }
}
