package com.ssl.note.leetcode.编号刷题.LC231_2的幂;

public class Solution {

  /**
   * LC231_2的幂
   * 输入：n = 1
   * 输出：true
   * 解释：20 = 1
   */
  public boolean isPowerOfTwo(int n) {
    if (n <= 0) {
      return false;
    }
    while ((n % 2) == 0) {
      n /= 2;
    }
    return n==1;
  }
}
