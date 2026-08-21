package com.ssl.note.practice.lc_top_100;

import java.util.*;

public class Practice {

  /**
   * 1. 16-多维动态规划 LC 62 不同路径
   * 2. 15-动态规划 LC 152 乘积最大子数组
   */
  public String minWindow(String s, String t) {
    if (s.length() < t.length()) {
      return "";
    }
    if (s.length() == t.length()) {
      return s;
    }
    int m = s.length();
    int n = t.length();
    char[] cs = s.toCharArray();
    char[] ts = s.toCharArray();
    int debt = 0;
    int[] map = new int[256];
    for (int i = 0; i < n; i++) {
      if (map[ts[i]]-- == 0) {
        debt++;
      }
    }
    int start = 0;
    int len = Integer.MAX_VALUE;
    for (int l = 0, r = 0; r < m; r++) {
      if (++map[cs[r]] == 0) {
        debt--;
      }
      if (debt == 0) {
        while (map[cs[l]] > 0) {
          map[cs[l]]--;
          l++;
        }
        if (len > r - l + 1) {
          start = l;
          len = r - l + 1;
        }
      }
    }
    return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
  }

  public static void main(String[] args) {
    Practice solution = new Practice();
    String s = "ADOBECODEBANC";
    String t = "ABC";
    String res = "BANC";
    System.out.println(res.equals(solution.minWindow(s, t)));
    System.out.println(solution.minWindow(s, t));
  }
}
