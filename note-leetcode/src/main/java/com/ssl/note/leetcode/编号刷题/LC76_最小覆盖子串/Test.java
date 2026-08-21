package com.ssl.note.leetcode.编号刷题.LC76_最小覆盖子串;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test {

  public String minWindow(String s, String t) {
    int m = s.length();
    int n = t.length();
    char[] cs = s.toCharArray();
    char[] ts = t.toCharArray();

    int[] cnts = new int[256];
    int debt = n;
    for (char c : ts) {
      cnts[c]--;
    }

    int start = 0;
    int len = Integer.MAX_VALUE;
    for (int l = 0, r = 0; r < m; r++) {
      if (cnts[cs[r]]++ < 0) {
        debt--;
      }
      if (debt == 0) {
        while (cnts[cs[l]] > 0) {
          cnts[cs[l++]]--;
        }
        if (len > r - l + 1) {
          start = l;
          len = r - l + 1;
        }
        // start一直取最大，和最短子串的起始位置没有必然关系
//         start=Math.max(start,l);
//         len = Math.min(len,r-l+1);
      }
    }
    return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
  }
}
