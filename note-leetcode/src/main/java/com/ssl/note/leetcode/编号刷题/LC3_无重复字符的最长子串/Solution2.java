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
    // l初始化-1，表示(l,r]的左边界之外
    for (int r = 0, l = -1; r < cs.length; r++) {
      // l表示上次出现的位置,只有重复时才更新
      if (map[cs[r]] != -1) {
        l = Math.max(l, map[cs[r]]);
      }
      map[cs[r]] = r;

      // 无重复的长度：r-(l+1)+1=r-l
      res = Math.max(res, r - l);
    }

    return res;
  }

  public int lengthOfLongestSubstring2(String s) {
    int[] map = new int[256];
    Arrays.fill(map, -1);

    char[] cs = s.toCharArray();

    int res = 0;
    for (int r = 0, l = 0; r < cs.length; r++) {
      // 滑动窗口r和l不能回退,l来到过的位置不能再回去了
      l = Math.max(l, map[cs[r]] + 1);
      map[cs[r]] = r;

      res = Math.max(res, r - l + 1);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    String s = "aabbaa";// 2
    System.out.println(solution.lengthOfLongestSubstring(s));
  }
}
