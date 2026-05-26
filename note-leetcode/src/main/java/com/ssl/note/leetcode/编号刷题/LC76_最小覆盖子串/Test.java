package com.ssl.note.leetcode.编号刷题.LC76_最小覆盖子串;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test {

  public String minWindow(String s, String t) {
    if (s.length() < t.length()) {
      return "";
    }
    Map<Character, Integer> need = new HashMap<>();
    Map<Character, Integer> windows = new HashMap<>();
    int m = s.length();
    int n = t.length();

    for (int i = 0; i <= n - 1; i++) {
      char cur = t.charAt(i);
      need.put(cur, need.getOrDefault(cur, 0) + 1);
    }

    int start = 0;
    int minLen = Integer.MAX_VALUE;

    int left = 0;
    int valid = 0;
    for (int i = 0; i <= m - 1; i++) {
      char cur = s.charAt(i);

      if (need.containsKey(cur)) {
        windows.put(cur, windows.getOrDefault(cur, 0) + 1);
        if (Objects.equals(windows.get(cur), need.get(cur))) {
          valid++;
        }

      }

      while (valid == need.size()) {
        cur = s.charAt(left);
        if (i - left + 1 < minLen) {
          start = left;
          minLen = i - left + 1;
        }
        if (need.containsKey(cur)) {
          if (Objects.equals(windows.get(cur), need.get(cur))) {
            valid--;
          }

          windows.put(cur, windows.getOrDefault(cur, 0) - 1);

        }
        left++;
      }

    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
  }
}
