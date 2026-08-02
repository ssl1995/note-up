package com.ssl.note.leetcode.编号刷题.LC461_汉明距离;

/**
 * @author SongShengLin
 * @date 2022/2/26 11:22 PM
 * @description
 */
public class Solution1 {

  /**
   * 汉明距离
   * 输入：x = 1, y = 4
   * 输出：2
   * 解释：有两个位置不同
   * 1   (0 0 0 1)
   * 4   (0 1 0 0)
   */
  public int hammingDistance(int x, int y) {
    // 二进制不同位置 = 将所有位置的1放在同一个数上 = 异或
    int num = x ^ y;

    int res = 0;
    while (num != 0) {
      num &= num - 1;
      res++;
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int x = 1;
    int y = 4;
    System.out.println(solution.hammingDistance(x, y));
  }
}
