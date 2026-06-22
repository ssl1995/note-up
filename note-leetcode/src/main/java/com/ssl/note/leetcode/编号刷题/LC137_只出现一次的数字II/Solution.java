package com.ssl.note.leetcode.编号刷题.LC137_只出现一次的数字II;

public class Solution {

  /**
   * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
   * 请你找出并返回那个只出现了一次的元素。
   * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
   */
  public int singleNumber(int[] nums) {

    int res = 0;
    for (int i = 0; i < 32; i++) {
      int count = 0;
      for (int num : nums) {
        // >>>:无符号右移，把负数也当做二进制看
        // (num >>> i) & 1: num的第i位
        count += (num >>> i) & 1;
      }
      // 只出现一次的数，它的二进制的1一定会留下
      if (count % 3 != 0) {
        // res 的第 i 位设置为 1
        // 左移只有 <<，没有 <<<
        // 因为左移本来就不会涉及符号位，不需要区分有符号和无符号。
        res |= (1 << i);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums1 = {2, 2, 3, 2};
    System.out.println(solution.singleNumber(nums1));  // 输出 3

    int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
    System.out.println(solution.singleNumber(nums2));  // 输出 99

    System.out.println("---");
    int num = 99;
    System.out.println(num >>> 1 & 1);
  }
}
