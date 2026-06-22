package com.ssl.note.leetcode.编号刷题.LC260_只出现一次的数字III;

public class Solution {

  /**
   * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
   * 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
   * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
   * 示例 1：
   * 输入：nums = [1,2,1,3,2,5]
   * 输出：[3,5]
   * 解释：[5, 3] 也是有效的答案。
   * 示例 2：
   * 输入：nums = [-1,0]
   * 输出：[-1,0]
   * 示例 3：
   * 输入：nums = [0,1]
   * 输出：[1,0]
   * 时间复杂度：O(n)
   * 空间复杂度：O(1)
   */
  public int[] singleNumber(int[] nums) {
    int xor = 0;
    // 第一步：全部异或，得到 a ^ b
    for (int num : nums) {
      xor ^= num;
    }

    // 第二步：取的是 a 和 b 不同的位中，位置最低的那个1
    int mask = xor & (-xor);

    int a = 0;
    int b = 0;

    // 第三步：按 mask 分组，分别异或
    for (int num : nums) {
      if ((num & mask) == 0) {
        // 该位为 0 的组
        a ^= num;
      } else {
        // 该位为 1 的组
        b ^= num;
      }
    }

    return new int[]{a, b};
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums1 = {1, 2, 1, 3, 2, 5};
    int[] res1 = solution.singleNumber(nums1);
    System.out.println(res1[0] + ", " + res1[1]);  // 输出 3, 5 或 5, 3

    int[] nums2 = {-1, 0};
    int[] res2 = solution.singleNumber(nums2);
    System.out.println(res2[0] + ", " + res2[1]);  // 输出 -1, 0 或 0, -1
  }
}
