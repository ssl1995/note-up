package com.ssl.note.leetcode.编号刷题.LC9_回文数;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/05 12:29
 * @Describe:
 */
public class Solution {

  /**
   * 回文数
   * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
   * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
   * 例如，121 是回文，而 123 不是。
   */
  public boolean isPalindrome(int x) {
    // 负数永远不是回文数
    if (x < 0) {
      return false;
    }
    // 1、比如x=121，找到100
    int offset = 1;
    // x的最大值是整形最大，为了防止*10会溢出
    while (x / offset >= 10) {
      offset *= 10;
    }
    // 2、比较每次的高位和最末尾的个位
    while (x != 0) {
      int num1 = x / offset;
      int num2 = x % 10;
      if (num1 != num2) {
        return false;
      }

      x = (x % offset) / 10;
      // 偏移每次都缩小100
      offset /= 100;
    }

    return true;
  }
}
