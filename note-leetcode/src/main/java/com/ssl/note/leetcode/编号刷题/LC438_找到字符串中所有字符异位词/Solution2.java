package com.ssl.note.leetcode.编号刷题.LC438_找到字符串中所有字符异位词;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/2/26 6:25 PM
 * @description
 */
public class Solution2 {
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
    int m = s.length();
    int n = p.length();

    int[] sMap = new int[26];
    int[] pMap = new int[26];

    int needCount = 0;
    // 记录p所需要字符种类和数量
    for (int i = 0; i < n; i++) {
      int index = p.charAt(i) - 'a';
      if (pMap[index] == 0) {
        needCount++;
      }
      pMap[index]++;
    }

    List<Integer> res = new ArrayList<>();
    int valid = 0;
    int left = 0;
    for (int i = 0; i < m; i++) {
      int index = s.charAt(i) - 'a';
      sMap[index]++;

      // 数量对上，种类+1
      if (sMap[index] == pMap[index]) {
        valid++;
      }
      // 缩小：窗口过长
      while (i - left + 1 > n) {
        int leftIndex = s.charAt(left) - 'a';
        // 前面加过，缩小时候也要减少种类
        if (sMap[leftIndex] == pMap[leftIndex]) {
          valid--;
        }
        sMap[leftIndex]--;
        // 缩小，移动左指针
        left++;
      }
      // 记录结果
      if (needCount == valid) {
        res.add(left);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    String s = "bpaa";
    String t = "aa";
    System.out.println(solution.findAnagrams(s, t));
  }
}
