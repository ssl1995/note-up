package com.ssl.note.leetcode.编号刷题.LC1371_每个原音包含偶数次的最长子字符串;

import java.util.Arrays;

public class Solution {

  /**
   * LC1371_每个原音包含偶数次的最长子字符串
   * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
   * 示例：
   * 输入：s = "eleetminicoworoep"
   * 输出：13
   * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
   * 【思路】前缀状态（奇偶性）+ 状态压缩 + 最早出现位置
   * 1. 核心观察：只关心"奇偶性"，不关心具体次数
   *    每个元音出现次数 % 2 只有 0(偶)/1(奇) 两种，用 1 个 bit 表示；
   *    5 个元音压缩成一个 5 位二进制状态 status：
   *      位:   4   3   2   1   0
   *            u   o   i   e   a
   *      例:   0   1   0   1   0  = 二进制 01010 = 10
   *            表示 o、e 出现奇数次，a、i、u 出现偶数次
   *    每遇到一个元音，异或翻转对应位：status ^= 1 << m（0变1，1变0）
   *    翻转偶数次回到 0，翻转奇数次停在 1 → 该位 = 出现次数 % 2
   *    状态共 2^5 = 32 种，所以 map 是 int[32]
   * 2. map[status] 的含义：状态 status 【最早】出现时的下标
   * 【示例推演1】s = "aaaa"（a 出现 4 次，看 a 位的翻转过程）
   *    初始 status=...0（偶）→ i=0 翻成 1（奇）→ i=1 翻回 0（偶）
   *    → i=2 翻成 1 → i=3 翻回 0。翻转 4 次（偶数次）回到 0，a 位 = 0 = "偶数次"
   * 【示例推演2】s = "aae"（位序只看 ...iea 三位）
   *    初始 map[0]=-1（状态 000）
   *    i=0: 'a' → status=001（a奇），没见过 → map[1]=0
   *    i=1: 'a' → status=000（全偶），map[0]=-1 → 长度 1-(-1)=2，"aa" 合法
   *    i=2: 'e' → status=010（e奇），没见过 → map[2]=2
   *    返回 2（"aae" 中 e 只有 1 次，不合法）
   * 【复杂度】时间 O(n)，空间 O(2^5) = O(1)
   */
  public int findTheLongestSubstring(String s) {
    // aeiou 看成 5 个二进制位
    // 状态范围 0~31 共 32 种（含全偶数状态 0）
    int[] map = new int[32];
    // -2 = 该状态还没出现过的哨兵值
    Arrays.fill(map, -2);
    // 状态 0（0 个元音/全偶数）在空前缀已出现，下标记为 -1
    map[0] = -1;

    int res = 0;
    // status = 当前前缀 s[0..i] 的元音奇偶状态（5 位二进制）
    int status = 0;
    for (int i = 0; i < s.length(); i++) {
      // 当前字符若是元音，返回其对应位 0~4；否则返回 -1
      int m = move(s.charAt(i));
      if (m != -1) {
        // 异或翻转对应位：0变1、1变0，即该元音出现次数的奇偶性翻转
        status ^= 1 << m;
      }
      if (map[status] != -2) {
        // 状态之前出现过（下标 j）：pre[i] == pre[j]
        // ⟺ 子串 s[j+1..i] 每个元音都出现偶数次，长度 i - j
        res = Math.max(res, i - map[status]);
        // 注意：求最长，保留最早下标，不覆盖
      } else {
        // 状态第一次出现，记录下标（越早，后续 i - j 越大）
        map[status] = i;
      }
    }
    return res;
  }

  private int move(char c) {
    switch (c) {
      case 'a':
        return 0;
      case 'e':
        return 1;
      case 'i':
        return 2;
      case 'o':
        return 3;
      case 'u':
        return 4;
      default:
        return -1;
    }
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    // 13: "leetminicowor"
//    String str = "eleetminicoworoep";
    // 2: "aa"
//    String str = "aaaz";
    // 6: 没有元音，0 次也是偶数次，整个字符串合法
    String str = "bcbcbc";
    System.out.println(s.findTheLongestSubstring(str));
  }
}
