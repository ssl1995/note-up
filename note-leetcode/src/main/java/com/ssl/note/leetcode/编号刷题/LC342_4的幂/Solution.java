package com.ssl.note.leetcode.编号刷题.LC342_4的幂;

public class Solution {

  /**
   * LC342_4的幂
   */
  public boolean isPowerOfFour(int n) {
    // 1、n是2的幂的特性判断n > 0 && (n == (n & (-n))
    // 2、n是4的幂，必有偶数位进制位上是1
    return n > 0 && (n == (n & (-n)) && (n & 0xaaaaaaaa) == 0);
//    return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
  }
}
