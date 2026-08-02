package com.ssl.note.leetcode.编号刷题.LC326_3的幂;

public class Solution {

  /**
   * LC326_3的幂
   * 示例 3：
   * 输入：n = 9
   * 输出：true
   */
  public boolean isPowerOfThree(int n) {
    if (n <= 0) {
      return false;
    }
    while (n % 3 == 0) {
      n /= 3;
    }
    return n == 1;
  }
}
