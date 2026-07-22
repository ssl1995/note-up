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
    int n = s.length();
    int begin = 0;
    char[] cs = s.toCharArray();
    // 单个字符也是回文串，初始化长度=1
    int maxLen = 1;
    // 中心扩展法：假设每个位置都作为回文中心，往外扩展，记录最长
    for (int i = 0; i < n; i++) {
      // 假设回文串长度是奇数
      int len1 = getPalindromeCenterLen(cs, n, i, i);
      // 假设回文串长度是偶数
      int len2 = getPalindromeCenterLen(cs, n, i, i + 1);
      int curLen = Math.max(len1, len2);

      if (curLen > maxLen) {
        maxLen = curLen;
        // 根据回文中心i和当前最长回文长度 maxLen，反推回文子串的起始位置begin
        // 奇数：i-maxLen/2
        // 偶数：i-maxLen/2+1

        // 分奇偶讨论、公式只差个±1，代入被除数里，可以统一
        // 奇数：maxLen=5，maxLen/2=2，(maxLen-1)/2=2
        // 偶数：maxLen=4，maxLen/2=2，(maxLen-1)/2=1=maxLen/2-1
        // 统一：i-(maxLen-1)/2 = 中心位置-中心到回文左边界的长度
        begin = i - (maxLen - 1) / 2;
      }
    }
    return s.substring(begin, begin + maxLen);
  }

  /**
   * 返回cs中[i,j]作为回文中心，往外扩的回文子串的最大长度
   */
  private int getPalindromeCenterLen(char[] cs, int n, int left, int right) {
    int i = left;
    int j = right;
    while (i >= 0 && j < n) {
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
