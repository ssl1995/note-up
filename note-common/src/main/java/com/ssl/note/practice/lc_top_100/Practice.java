package com.ssl.note.practice.lc_top_100;

public class Practice {
  public String minWindow(String s, String t) {
    if (s.length() < t.length()) {
      return "";
    }
    int m = s.length();
    int n = t.length();
    char[] cs = s.toCharArray();
    char[] ct = t.toCharArray();

    int[] cnts = new int[256];
    for (char c : ct) {
      cnts[c]--;
    }
    int debt = n;
    int start = 0;
    int len = -1;
    for (int i = 0, l = 0; i < m; i++) {
      cnts[cs[i]]++;
      if (cnts[cs[i]] == 0) {
        debt--;
      }

      if (debt == 0) {
        while (cnts[cs[l]] > 0) {
          cnts[cs[l++]]--;
        }
        if (len > i - l + 1) {
          start = l;
          len = i - l + 1;
        }
      }
    }
    return len == -1 ? "" : s.substring(start, start + len);
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[][] grod = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    // aaabcbc
  }

}
