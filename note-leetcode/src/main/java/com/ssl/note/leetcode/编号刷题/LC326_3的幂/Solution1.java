package com.ssl.note.leetcode.编号刷题.LC326_3的幂;

public class Solution1 {

  /**
   * LC326_3的幂
   * 示例 3：
   * 输入：n = 9
   * 输出：true
   */
  public boolean isPowerOfThree(int n) {
    return n > 0 && 1162261467 % n == 0;
  }
}
