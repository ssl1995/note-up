package com.ssl.note.leetcode.编号刷题.LC76_最小覆盖子串;

/**
 * @author SongShengLin
 * @date 2022/1/28 8:33 AM
 * @description
 */
public class Solution1 {
  /**
   * 最小覆盖子串
   * 返回 s 中涵盖 t 所有字符的最小子串
   * 输入：s = "ADOBECODEBANC", t = "ABC"
   * 输出："BANC"
   */
  public String minWindow(String s, String t) {
    if (s == null || t == null || s.length() < t.length()) {
      return "";
    }
    int m = s.length();
    // n=match，滑动窗口内还需要匹配多少个字符
    int n = t.length();

    char[] cs = s.toCharArray();
    char[] ct = t.toCharArray();
    // 计数器：map记录t中字符需要的数量
    int[] map = new int[256];
    for (char c : ct) {
      map[c]++;
    }

    int j = 0;
    String res = "";

    for (int i = 0; i < m; i++) {
      // 扩张：如果减完之后 >= 0，说明这个字符本来就是 t 需要的，
      if (--map[cs[i]] >= 0) {
        // 真正匹配了一个需要的字符，所以 match--。
        n--;
      }
      // 收缩：移除多余字符
      while (n == 0 && map[cs[j]] < 0) {
        map[cs[j]]++;
        j++;
      }
      // 更新最小子串
      if (n == 0) {
        if (res.isEmpty() || res.length() > i - j + 1) {
          res = s.substring(j, i + 1);
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(solution.minWindow(s, t));
  }
}
