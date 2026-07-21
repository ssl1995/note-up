package com.ssl.note.leetcode.编号刷题.LC55_跳跃游戏;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:58 AM
 * @description
 */
public class Solution {
  /**
   * 跳跃游戏
   * 数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标。
   * 输入：nums = [2,3,1,1,4]
   * 输出：true
   * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
   */
  public boolean canJump(int[] nums) {
    if (nums == null || nums.length == 0) {
      return false;
    }
    int n = nums.length;
    if (n == 1) {
      return true;
    }
    int max = 0;

    for (int i = 0; i < n; i++) {
      // 当前位置没有跳跃到过，返回false
      if (i > max) {
        return false;
      }
      max = Math.max(max, i + nums[i]);
      if (max >= n - 1) {
        return true;
      }
    }
    return max >= n - 1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {0, 2, 3};
    System.out.println(solution.canJump(nums));
  }
}
