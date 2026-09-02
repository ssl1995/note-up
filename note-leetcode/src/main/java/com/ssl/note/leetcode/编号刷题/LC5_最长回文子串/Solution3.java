package com.ssl.note.leetcode.编号刷题.LC5_最长回文子串;


public class Solution3 {

  /**
   * 最长回文子串
   * 返回一个字符串的最长回文子串
   * 输入：s = "babad"
   * 输出："bab"
   * 解释："aba" 同样是符合题意的答案。
   * 中心扩展法
   * 时间复杂度：n^2
   * 空间复杂度：1
   */
  public String longestPalindrome(String s) {
    if (s.length() < 2) {
      return s;
    }
    char[] cs = s.toCharArray();
    // 记录最长回文串的起点和长度
    int begin = 0;
    int maxLen = 0;
    // 中心扩展法：假设每个位置都作为回文中心，往外扩展，记录最长
    for (int i = 0; i < cs.length; i++) {
      // 假设回文串长度是奇数或偶数，中心位置往外扩取最大值
      int len1 = getPalindromeCenterLen(cs, i, i);
      int len2 = getPalindromeCenterLen(cs, i, i + 1);
      len1 = Math.max(len1, len2);

      if (len1 > maxLen) {
        maxLen = len1;
        // 根据回文中心i和当前最长回文长度 maxLen，反推回文子串的起始位置begin
        // 奇数：i-maxLen/2
        // 偶数：i-maxLen/2+1
        // 统一：i-(maxLen-1)/2 = 中心位置-中心到回文左边界的长度
        begin = i - (maxLen - 1) / 2;
      }
    }
    return s.substring(begin, begin + maxLen);
  }

  /**
   * 返回cs中[i,j]作为回文中心，往外扩的回文子串的最大长度
   * 是往外扩的最大长度，不是最大回文半径
   */
  private int getPalindromeCenterLen(char[] cs, int i, int j) {
    while (i >= 0 && j <= cs.length - 1) {
      // 不能写:cs[i++]!=cs[j--],否则会多算
      if (cs[i] != cs[j]) {
        break;
      }
      i--;
      j++;
    }
    // 循环跳出：cs[i]!=cs[j],如abbc,cs[i]=a,cs[j]=c,回文中心长度为2
    // 此时的回文中心长度：j-i+1-2=3-0-1=2
    return j - i - 1;
  }

  public static void main(String[] args) {
    Solution3 solution2 = new Solution3();
//    String s = "abc";
    // a
//    System.out.println(solution2.longestPalindrome(s));
    String s1 = "abbc";
    // b
    System.out.println(solution2.longestPalindrome(s1));
  }
}
