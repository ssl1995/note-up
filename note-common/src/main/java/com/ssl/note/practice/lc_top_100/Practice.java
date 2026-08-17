package com.ssl.note.practice.lc_top_100;

public class Practice {

  /**
   * 1. 16-多维动态规划 LC 62 不同路径
   * 2. 15-动态规划 LC 152 乘积最大子数组
   */
  public String minWindow(String s, String t) {
    char[] cs = s.toCharArray();
    char[] ct = t.toCharArray();

    // 需要的
    int[] cnts = new int[256];
    for (char c : ct) {
      cnts[c]--;
    }
    // 总的债务
    int debt = ct.length;

    String res = "";
    for (int right = 0, left = 0; right < cs.length; right++) {
      if (++cnts[cs[right]] <= 0) {
        debt--;
      }
      if (debt == 0) {
        while (left < right && cnts[cs[left]] != 0) {
          cnts[cs[left]]--;
          left++;
        }
        String temp = s.substring(left, right + 1);
        if (res.isBlank() || res.length() > temp.length()) {
          res = temp;
        }
      }
    }
    return res;
  }


  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(practice.minWindow(s, t));
  }
}
