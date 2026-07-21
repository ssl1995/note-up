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
    // 初始位置在下标0，不能跳，比如[0],返回0
    if (n == 1) {
      return 0;
    }
    // 当前能够达到的最远位置
    int max = 0;
    // 走到边界需要再走一次
    int minCount = 0;
    // 当前跳跃的最远边界
    int end = 0;
    for (int i = 0; i < n; i++) {
      max = Math.max(max, i + nums[i]);
      // 贪心：每一跳都扩到最远，走到边界再跳一次
      if (i == end) {
        end = max;
        minCount++;
      }
      // end曾经超过终点，直接返回
      if (end >= n - 1) {
        return minCount;
      }
    }
    return minCount;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 3, 1, 1, 4};
    System.out.println(solution.jump(nums));
  }
}
