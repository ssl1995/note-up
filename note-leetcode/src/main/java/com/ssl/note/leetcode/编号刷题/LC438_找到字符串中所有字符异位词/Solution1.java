package com.ssl.note.leetcode.编号刷题.LC438_找到字符串中所有字符异位词;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/2/26 6:25 PM
 * @description
 */
public class Solution1 {
  /**
   * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引
   * s和p仅包含小写字母
   * 输入: s = "cbaebabacd", p = "abc"
   * 输出: [0,6]
   * 解释:
   * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
   * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
   */
  public List<Integer> findAnagrams(String s, String p) {
    if (p.length() > s.length()) {
      return new ArrayList<>();
    }
    int m = s.length();
    int n = p.length();
    // p位置的唯一key
    String pkey = getKey(p);
    List<Integer> res = new ArrayList<>();

    for (int i = 0; i + n - 1 < m; i++) {
      // s子串的唯一key
      String part = s.substring(i, i + n);
      String key = getKey(part);
      if (!pkey.equals(key)) {
        continue;
      }
      res.add(i);
    }
    return res;
  }

  private String getKey(String part) {
    char[] cs = part.toCharArray();
    int[] map = new int[26];
    for (char c : cs) {
      map[c - 'a']++;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      if (map[i] != 0) {
        // 加的是i位置，表示偏移量
        char letter = (char) ('a' + i);
        // a1b1c1:字母+次数
        sb.append(letter).append(map[i]);
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "cbaebabacd";
    String t = "abc";
    System.out.println(solution.findAnagrams(s, t));
//    System.out.println((char) ('a' + 0));
//    System.out.println((char) ('a' + 1));
//    System.out.println((char) ('a' + 2));
  }
}
