package com.ssl.note.leetcode.编号刷题.LC70_爬楼梯;

/**
 * @author SongShengLin
 * @date 2022/1/27 8:57 AM
 * @description
 */
public class Solution {

  /**
   * 爬楼梯
   * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   * n=1,返回1;n=2,返回2;n=3，返回3;
   * - 时间复杂度：O(2^n)，因为每个递归调用都会产生两个子调用，导致指数级的时间复杂度
   * - 空间复杂度：O(n)，递归栈的深度为n
   */
  public int climbStairs(int n) {
    if (n <= 2) {
      return n;
    }
    // 递归
    return climbStairs(n - 1) + climbStairs(n - 2);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 4;
    System.out.println(solution.climbStairs(n));
  }

}
