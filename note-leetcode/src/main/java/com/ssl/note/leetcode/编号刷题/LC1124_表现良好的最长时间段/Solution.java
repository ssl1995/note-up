package com.ssl.note.leetcode.编号刷题.LC1124_表现良好的最长时间段;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  /**
   * LC1124_表现良好的最长时间段
   * 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数。
   * 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」。
   * 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」。
   * 请你返回「表现良好时间段」的最大长度。
   * 示例：
   * 输入：hours = [9,9,6,0,6,6,9]
   * 输出：3
   * 解释：最长的表现良好时间段是 [9,9,6]。
   */
  public int longestWPI(int[] hours) {
    Map<Integer, Integer> map = new HashMap<>();

    int res = 0;
    for (int i = 0, sum = 0; i < hours.length; i++) {
      int num = hours[i] > 8 ? 1 : -1;
      sum += num;
      // 某个位置sum>0,代表0-i位置满足条件，长度i+1
      if (sum > 0) {
        res = Math.max(res, i + 1);
      } else {
        // 某个位置sum<=0，找sum-1出现的最早位置j
        // 等价 j+1到i的位置前缀和>0 -> 长度 i-j
        if (map.containsKey(sum - 1)) {
          res = Math.max(res, i - map.get(sum - 1));
        }
      }

      if (!map.containsKey(sum)) {
        map.put(sum, i);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] hours = {9, 9, 6, 0, 6, 6, 9};
    System.out.println(solution.longestWPI(hours));
  }
}
