package com.ssl.note.leetcode.编号刷题.LC410_分割数组的最大值;

public class Solution {

  /**
   * LC410_分割数组的最大值
   * 给定一个非负整数数组 nums 和一个整数 k ，
   * 你需要将这个数组分成 k 个非空的连续子数组，使得这 k 个子数组各自和的最大值 最小。
   * 返回分割后最小的和的最大值。
   * 输入：nums = [7,2,5,10,8], k = 2
   * 输出：18
   * 解释：
   * 一共有四种方法将 nums 分割为 2 个子数组。
   * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
   * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
   */
  public int splitArray(int[] nums, int k) {
    long sum = 0;
    for (int num : nums) {
      sum += num;
    }
    // 答案范围：[0,sum]二分，粒度大没关系
    // 有单调性，可以二分
    long l = 0, r = sum;
    long res = 0;
    while (l <= r) {
      long m = l + (r - l) / 2;
      // nums中划分<=m累加和的子数组需要need个
      int need = f(nums, m);
      // 构建的f返回值与答案k的比较
      if (need <= k) {
        res = m;
        // 求最小，往左二分
        r = m - 1;
      } else {
        l = m + 1;
      }
    }

    return (int) res;
  }

  // 必须让数组每一部分的累加和 <= limit，返回需要的几个部分
  private int f(int[] nums, long limit) {
    int count = 1;

    long sum = 0;
    // 贪心从左往右累加，超过 limit 就开新的一段
    for (int num : nums) {
      // 无法分组，返回值需要与k比较，返回整形最大
      if (num > limit) {
        return Integer.MAX_VALUE;
      }
      // 加上这个数超过限制
      if (sum + num > limit) {
        count++;
        sum = num;// 下一组sum从num开始
      } else {
        // 没有超过限制
        sum += num;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {7, 2, 5, 10, 8};
    int k = 2;
    System.out.println(solution.splitArray(nums, k));
  }
}
