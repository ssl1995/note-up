package com.ssl.note.leetcode.编号刷题.LC137_只出现一次的数字II;

public class Solution {

  /**
   * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
   * 请你找出并返回那个只出现了一次的元素。
   * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
   * <p>
   * 示例 1：
   * 输入：nums = [2,2,3,2]
   * 输出：3
   * <p>
   * 示例 2：
   * 输入：nums = [0,1,0,1,0,1,99]
   * 输出：99
   * <p>
   * 解题思路：位运算（状态机）
   * <p>
   * 1. 为什么 LC136 的异或思路不能直接迁移？
   * LC136 中其他元素出现 2 次，利用 a ^ a = 0 可以抵消。
   * 但本题其他元素出现 3 次，a ^ a ^ a = a，无法直接抵消。
   * <p>
   * 2. 核心思想：按位统计 1 的个数
   * 对于每一位，如果某个数字出现 3 次，那么这一位上的 1 也会出现 3 次。
   * 把所有数字在该位上的 1 累加，对 3 取余，剩下的就是只出现一次的那个数字在该位上的值。
   * <p>
   * 3. 高效实现：用两个整数 ones 和 twos 模拟三进制计数
   * - ones：记录出现 1 次的位
   * - twos：记录出现 2 次的位
   * 当出现 3 次时，ones 和 twos 都清零。
   * <p>
   * 4. 状态转移：
   * ones = (ones ^ num) & ~twos
   * twos = (twos ^ num) & ~ones
   * <p>
   * 解释：
   * - 当某个位第 1 次出现时，ones 记录它，twos 不记录。
   * - 当某个位第 2 次出现时，ones 清零，twos 记录它。
   * - 当某个位第 3 次出现时，twos 也清零。
   * - & ~twos 和 & ~ones 保证状态正确转移，不会互相干扰。
   * <p>
   * 时间复杂度：O(n)
   * 空间复杂度：O(1)
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
