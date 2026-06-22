package com.ssl.note.leetcode.编号刷题.LC136_只出现一次的数字;

public class Solution {

  /**
   * 只出现一次的数字
   * 输入: [4,1,2,1,2]
   * 输出: 4
   */
  public int singleNumber(int[] nums) {
    int res = 0;
    for (int num : nums) {
      // 数组中出现2次的都异或彼此，然后=0
      // 异或：0^x=x;x^x=0
      res ^= num;
    }
    return res;
  }


  public static void main(String[] args) {
    int num = 10;
    System.out.println(0 ^ num);// 10
    System.out.println(num ^ num);// 0
  }
}
