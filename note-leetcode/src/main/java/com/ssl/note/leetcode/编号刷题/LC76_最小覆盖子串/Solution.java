package com.ssl.note.leetcode.编号刷题.LC76_最小覆盖子串;

/**
 * @author SongShengLin
 * @date 2022/1/28 8:33 AM
 * @description
 */
public class Solution {
  /**
   * 最小覆盖子串
   * 返回 s 中涵盖 t 所有字符的最小子串
   * 输入：s = "ADOBECODEBANC", t = "ABC"
   * 输出："BANC"
   */
  public String minWindow(String str, String tar) {
    char[] cs = str.toCharArray();
    char[] ct = tar.toCharArray();

    // 需要的
    int[] cnts = new int[256];
    for (char c : ct) {
      cnts[c]--;
    }
    // 总的债务
    int debt = ct.length;

    // 求子串，考虑初始坐标和长度
    int start = 0;
    int len = Integer.MAX_VALUE;

    for (int right = 0, left = 0; right < cs.length; right++) {
      // ++/-- 写进条件里，条件成功or失败都会执行
      // 这里是必须发生的，所以加1后还<0,说明还需要
      // 也可以写成：++cnts[cs[right]] <= 0
      if (cnts[cs[right]]++ < 0) {
        debt--;
      }

      if (debt == 0) {
        // ++/-- 写进条件里，条件成功or失败都会执行
        // 这里不是必须发生的，所以不能写成cnts[cs[left]]-->0
        while (cnts[cs[left]] > 0) {
          cnts[cs[left++]]--;
        }

        if (right - left + 1 < len) {
          len = right - left + 1;
          start = left;
        }
      }
    }
    return len == Integer.MAX_VALUE ? "" : str.substring(start, start + len);
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "aa";
    String t = "aa";
    System.out.println(solution.minWindow(s, t));
  }
}
