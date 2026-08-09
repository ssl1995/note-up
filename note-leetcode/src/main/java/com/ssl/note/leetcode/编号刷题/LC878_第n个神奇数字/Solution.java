package com.ssl.note.leetcode.编号刷题.LC878_第n个神奇数字;

public class Solution {

  /**
   * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
   * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 109 + 7 取模 后的值。
   * 示例：
   * 输入：n = 1, a = 2, b = 3
   * 输出：2
   */
  public int nthMagicalNumber(int n, int a, int b) {
    long lcm = lcm(a, b);
    // 二分确定范围:[1,n*最小]
    long left = 1;
    long right = (long) n * Math.min(a, b);

    long res = 0;
    while (left <= right) {
      long mid = (left + right) / 2;
      if (gt(mid, a, b, lcm) >= n) {
        res = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return (int) (res % (1000000007));
  }

  // [0,x]中有多少个被a或b整除
  private long gt(long x, int a, int b, long lcm) {
    return (x / a + x / b) - (x / lcm);
  }

  // 最小公约数
  private int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  // 最大公倍数
  private long lcm(int a, int b) {
    return (long) (a / gcd(a, b)) * b;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 1000000000;
    int a = 40000;
    int b = 40000;
    System.out.println(solution.nthMagicalNumber(n, a, b));
  }
}
