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
    // p 比 s 还长，不可能存在异位词子串
    if (s.length() < p.length()) {
      return new ArrayList<>();
    }
    int m = s.length();
    int n = p.length();
    // 异位词:统计词频+字母种类数
    int[] sMap = new int[26];
    int[] pMap = new int[26];

    // 记录p所需要字符种类和数量
    int pClassify = 0;
    for (int i = 0; i < n; i++) {
      int index = p.charAt(i) - 'a';
      if (pMap[index] == 0) {
        pClassify++;
      }
      pMap[index]++;
    }

    List<Integer> res = new ArrayList<>();
    int sClassify = 0;
    for (int r = 0, l = 0; r < m; r++) {
      // 窗口右边:先+1,再判断种类
      int index = s.charAt(r) - 'a';
      sMap[index]++;
      if (sMap[index] == pMap[index]) {
        sClassify++;
      }

      // 窗口超过p长度,缩小左边界
      // 每轮 r 只前进 1 步，窗口最多比 n 大 1，缩一次即可，无需 while
      if (r - l + 1 > n) {
        // 窗口左边:先判断种类,再-1
        int leftIndex = s.charAt(l) - 'a';
        if (sMap[leftIndex] == pMap[leftIndex]) {
          sClassify--;
        }
        sMap[leftIndex]--;
        // 缩小，移动左指针
        l++;
      }

      // 窗口长度恰好等于n且字符种类全部匹配
      if (r - l + 1 == n && pClassify == sClassify) {
        res.add(l);
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
