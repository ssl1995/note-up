package com.ssl.note.leetcode.编号刷题.LC438_找到字符串中所有字符异位词;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/2/26 6:25 PM
 * @description
 */
public class Solution3 {
  /**
   * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引
   * s和p仅包含小写字母
   * 输入: s = "cbaebabacd", p = "abc"
   * 输出: [0,6]
   * 解释:
   * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
   * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
   * zero 的理解（账户对账模型）：
   * 1.把 26 个字母看作 26 个账户，diff[i] 是账户余额：<0 欠账、=0 平账、>0 多出；
   * zero = 当前平账的账户数，异位词要求每个字母数量精确相等 ⇔ 26 个账户全部平账 ⇔ zero==26
   * 2.zero 的意义：把"每轮扫 diff 数组查匹配 O(26)"压缩成"看一眼 zero==26 O(1)"
   * 3.zero 能 O(1) 维护的原因：账户余额只有"跨过 0"的瞬间才影响 zero
   * （0→非0 破坏 zero--；±1→0 修复 zero++；-2→-1 欠着还是欠着、2→3 多着还是多着，都不动），
   * 故进窗/出窗各改一个账户，只需改前查一次、改后查一次
   * 4.初始值不是 0：p 没出现过的字母账户天然平账，zero 初始 = 26 - p中不同字母种数
   * 5.与 LC76 对照：debt 数"欠多少字符"(个数)，zero 数"平几户字母"(种类)；
   * 一个容忍盈余(看<0)，一个要求精确(看==0)
   */
  public List<Integer> findAnagrams(String s, String p) {
    // p 比 s 还长，不可能存在异位词子串
    if (s.length() < p.length()) {
      return new ArrayList<>();
    }
    int m = s.length();
    int n = p.length();
    char[] cs = s.toCharArray();
    char[] ps = p.toCharArray();

    // diff[i] = 窗口内词频 - p词频，先减去 p 的词频
    int[] diff = new int[26];
    for (char c : ps) {
      diff[c - 'a']--;
    }
    // zero平账的数量，初始化是未出现的字母天然平账的
    int zero = 0;
    for (int d : diff) {
      if (d == 0) {
        zero++;
      }
    }

    List<Integer> res = new ArrayList<>();
    for (int r = 0; r < m; r++) {
      // 右端进窗口：改前若为0则zero--，修改，改后若为0则zero++
      int in = cs[r] - 'a';
      // 三段式：zero含义=diff中0的个数
      // 改之前先把 in 的"旧贡献"从 zero 里扣掉（销账），
      // 改完之后再把"新贡献"加回去（入账）
      if (diff[in] == 0) {
        zero--;
      }
      diff[in]++;
      if (diff[in] == 0) {
        zero++;
      }
      // 超过n位置，更新左窗口
      if (r >= n) {
        int l = r - n;
        int out = cs[l] - 'a';
        // 显示三段式：改前改后都查
        if (diff[out] == 0) {
          zero--;
        }
        diff[out]--;
        if (diff[out] == 0) {
          zero++;
        }
      }
      // 到达n-1位置开始统计
      // 窗口内字符相等=词频相同=欠债还完，zero=26
      if (r >= n - 1 && zero == 26) {
        res.add(r - n + 1);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    // [0, 6]
    System.out.println(solution.findAnagrams("cbaebabacd", "abc"));
    // [2]
//    System.out.println(solution.findAnagrams("bpaa", "aa"));
//    // [0, 1, 2]
//    System.out.println(solution.findAnagrams("abab", "ab"));
//    // []
//    System.out.println(solution.findAnagrams("aa", "bb"));
  }
}
