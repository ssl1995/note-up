package com.ssl.note.leetcode.编号刷题.LC3_无重复字符的最长子串;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {
  /**
   * 无重复字符的最长子串
   * 输入: s = "abcabcbb"
   * 输出: 3
   * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
   * 概念：1.子串 = 连续的 2.子序列 = 不连续的
   */
  public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    char[] cs = s.toCharArray();

    int res = 0;
    for (int right = 0, left = 0; right < cs.length; right++) {
      // 左窗口：重复字符上次出现位置在窗口内时，左边界右移；在窗口外时left不能往回退
      if (map.containsKey(cs[right])) {
        left = Math.max(left, map.get(cs[right]) + 1);
      }
      map.put(cs[right], right);

      res = Math.max(res, right - left + 1);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "abba";// 答案是ab，长度=2，这样容易理解
    System.out.println(solution.lengthOfLongestSubstring(s));
  }
}
