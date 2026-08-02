package com.ssl.note.leetcode.编号刷题.LC201_按位与的结果;

public class Solution {

  /**
   * LC201_按位与的结果
   * 示例 1：
   * 输入：left = 5, right = 7
   * 区间[5-7]
   * 5:0101
   * 6:0110
   * 7:0111
   * &:0100  (按位与=&:每一列全部为1结果才是1,有一个0就是0)
   * 输出：4
   * 思路:区间内所有数的AND结果 = left和right的二进制公共前缀(低位必被洗掉)
   * 做法:不断削掉right最低位的1,直到right<=left,剩下的就是公共前缀
   */
  public int rangeBitwiseAnd(int left, int right) {
    while (left < right) {
      right -= right & (-right);
    }
    return right;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int left = 5;
    int right = 7;
    System.out.println(solution.rangeBitwiseAnd(left, right));
  }
}
