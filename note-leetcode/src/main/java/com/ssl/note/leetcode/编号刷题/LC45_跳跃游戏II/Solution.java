package com.ssl.note.leetcode.编号刷题.LC45_跳跃游戏II;

public class Solution {

  /**
   * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
   * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在索引 i 处，你可以跳转到任意 (i + j) 处：
   * 0 <= j <= nums[i] 且
   * i + j < n
   * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
   * 示例 1:
   * 输入: nums = [2,3,1,1,4]
   * 输出: 2
   * 解释: 跳到最后一个位置的最小跳跃数是 2。
   * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
   */
  public int jump(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int n = nums.length;
    int allMax = 0;
    int end = 0;
    int res = 0;
    // 优化：遍历到 n-2 即可，因为到达 n-1 时不需要再跳
    // 这样自然处理了 n==1 的情况（循环不执行，直接返回0）
    for (int i = 0; i < n - 1; i++) {
      allMax = Math.max(allMax, i + nums[i]);
      // 贪心：每一步只有走到边界才能跳
      if (i == end) {
        end = allMax;
        res++;
      }
      // end 已经能到达或超过终点
      if (end >= n - 1) {
        return res;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 3, 1, 1, 4};
  }
}
