package com.ssl.note.leetcode.编号刷题.LC190_颠倒二进制位;

public class Solution {

  /**
   * LC190_逆序二进制位
   * 颠倒给定的 32 位有符号整数的二进制位。
   * n：43261596	二进制：00000010100101000001111010011100
   * 答：964176192	二进制：00111001011110000010100101000000
   */
  public int reverseBits(int n) {
    int[] count = new int[32];
    // 原0位置，放31位置
    // 原1位置，放30位置
    // 原i位置，放31-i位置
    for (int i = 0; i < 31; i++) {
      count[31 - i] = (n >> i) & 1;
    }
    int res = 0;
    for (int i = 0; i < 31; i++) {
      if (count[i] == 0) {
        continue;
      }
      res |= 1 << i;
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 43261596;
    System.out.println(solution.reverseBits(n));
  }
}
