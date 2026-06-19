package com.ssl.note.leetcode.编号刷题.LC198_打家劫舍;

public class Solution1 {
  /**
   * 打家劫舍
   * 要求：不能盗窃相邻房间
   * 输入：[2,7,9,3,1]
   * 输出：12
   * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
   * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
   */
  public int rob(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    if (n == 2) {
      return Math.max(nums[0], nums[1]);
    }
    // 动态规划优化成2个变量迭代
    int a = nums[0];
    int b = Math.max(nums[0], nums[1]);

    for (int i = 2; i < n; i++) {
      int c = Math.max(a + nums[i], b);
      a = b;
      b = c;
    }

    return b;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {2, 7, 9, 3, 1};
    System.out.println(solution.rob(nums));
  }
}
