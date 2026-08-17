package com.ssl.note.practice.lc_top_100;


import java.util.Arrays;

public class Practice {

  /**
   * 1. 16-多维动态规划 LC 62 不同路径
   * 2. 15-动态规划 LC 152 乘积最大子数组
   */
  public int lengthOfLongestSubstring(String s) {
    int[] map = new int[256];
    Arrays.fill(map, -1);

    char[] cs = s.toCharArray();

    int res = 0;
    for (int right = 0, left = 0; right < cs.length; right++) {
      // 左窗口：重复字符上次出现位置在窗口内时，左边界右移；在窗口外时left不能往回退
      left = Math.max(left, map[cs[right]] + 1);

      res = Math.max(res, right - left + 1);
      map[cs[right]] = right;
    }

    return res;
  }


  public static void main(String[] args) {
    Practice practice = new Practice();
    System.out.println(practice.lengthOfLongestSubstring("abcabcbb")); // 3
    System.out.println(practice.lengthOfLongestSubstring("bbbbb"));    // 1
    System.out.println(practice.lengthOfLongestSubstring("pwwkew"));   // 3
    System.out.println(practice.lengthOfLongestSubstring("abba"));     // 2
    System.out.println(practice.lengthOfLongestSubstring(""));         // 0

  }
}
