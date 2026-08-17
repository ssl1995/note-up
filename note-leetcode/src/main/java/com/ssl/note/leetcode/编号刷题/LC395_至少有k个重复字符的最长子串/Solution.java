package com.ssl.note.leetcode.编号刷题.LC395_至少有k个重复字符的最长子串;

import java.util.Arrays;

public class Solution {

  /**
   * LC395_至少有k个重复字符的最长子串
   * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
   * 如果不存在这样的子字符串，则返回 0。
   * 输入：s = "ababbc", k = 2
   * 输出：5
   * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
   * 提示：
   * 1 <= s.length <= 104
   * s 仅由小写英文字母组成
   * 1 <= k <= 105
   *
   * 经验总结：
   * “每种字符出现次数都 ≥ k”对窗口没有单调性（扩/缩都可能让条件从成立变不成立），不能直接滑。
   * 但答案子串的字符种类数一定是 1~26 中的某个值，于是枚举种类数 require，
   * 把窗口条件换成有单调性的“种类数 ≤ require”，内部再检查“每种都 ≥ k”（satisfy == require）。
   * 26 次 O(n) 滑动窗口，总复杂度 O(26n)。
   * 套路：条件无单调性时，枚举一个取值范围很小的“维度”（这里是种类数 1~26），人为构造单调性。
   */
  public int longestSubstring(String s, int k) {
    int n = s.length();
    int[] cnts = new int[256];
    char[] cs = s.toCharArray();

    int res = 0;
    // 构造单调性：枚举窗口内恰好 require 种字符（种类数 ≤ require 是单调的）
    // 所有“种类数恰好 require 且每种 ≥ k”的窗口中取最长，全局最大值就是答案
    for (int require = 1; require <= 26; require++) {
      Arrays.fill(cnts, 0);
      // collect: 窗口内字符种类数；satisfy: 窗口内出现次数 ≥ k 的字符种数
      for (int l = 0, r = 0, collect = 0, satisfy = 0; r < n; r++) {
        // 右端进窗口
        cnts[cs[r]]++;
        if (cnts[cs[r]] == 1) {
          collect++;  // 新出现的字符
        }
        if (cnts[cs[r]] == k) {
          satisfy++;  // 该字符次数恰好达到 k，达标种数 +1
        }

        // 种类数超了，收缩左端直到 ≤ require
        while (collect > require) {
          if (cnts[cs[l]] == 1) {
            collect--;  // 该字符将被移空
          }
          if (cnts[cs[l]] == k) {
            satisfy--;  // 该字符将跌破 k 次
          }
          cnts[cs[l++]]--;
        }

        // 窗口恰有 require 种字符且每种都 ≥ k（collect==require 隐含于 satisfy==require ≤ collect）
        if (satisfy == require) {
          res = Math.max(res, r - l + 1);
        }
      }
    }

    return res;
  }
}
