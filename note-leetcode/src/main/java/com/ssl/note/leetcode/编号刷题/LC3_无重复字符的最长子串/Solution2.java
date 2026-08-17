package com.ssl.note.leetcode.编号刷题.LC3_无重复字符的最长子串;

import java.util.Arrays;

public class Solution2 {
  /**
   * 无重复字符的最长子串
   * 输入: s = "abcabcbb"
   * 输出: 3
   * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
   * 概念：1.子串 = 连续的 2.子序列 = 不连续的
   */
  public int lengthOfLongestSubstring(String s) {
    int[] map = new int[256];
    Arrays.fill(map, -1);

    char[] cs = s.toCharArray();

    int res = 0;
    for (int right = 0, left = 0; right < cs.length; right++) {
      // 左窗口：重复字符上次出现位置在窗口内时，左边界右移；在窗口外时left不能往回退
      left = Math.max(left, map[cs[right]] + 1);

      res = Math.max(res, right - left + 1);
      map[cs[right]] = right;
    }

    return res;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    String s = "abcabcbb";// 3
    System.out.println(solution.lengthOfLongestSubstring(s));
  }
}
