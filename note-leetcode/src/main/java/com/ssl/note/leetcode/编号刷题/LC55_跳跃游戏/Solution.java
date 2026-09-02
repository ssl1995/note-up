package com.ssl.note.leetcode.编号刷题.LC55_跳跃游戏;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:58 AM
 * @description 跳跃游戏 - 判断能否到达终点
 */
public class Solution {

  /**
   * 跳跃游戏
   * 数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标。
   *
   * 【核心思路】贪心 + 最远可达距离
   * 维护 maxReach 表示当前能到达的最远位置
   * 遍历数组，如果当前下标 i 已经超过 maxReach，说明走不动了，返回 false
   * 否则更新 maxReach，如果 maxReach 能覆盖终点，返回 true
   *
   * 【统一写法】与 LC45 对比记忆：
   * - LC55 问"能不能到" → 关注 i > maxReach（走不动了）
   * - LC45 问"最少几步" → 关注 i == end（必须起跳了）
   *
   * 输入：nums = [2,3,1,1,4]
   * 输出：true
   * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
   */
  public boolean canJump(int[] nums) {
    if (nums == null || nums.length == 0) {
      return false;
    }
    int n = nums.length;
    int maxReach = 0;  // 当前能到达的最远位置

    for (int i = 0; i < n; i++) {
      // 关键判断：当前位置已经超出能到达的范围，走不动了
      if (i > maxReach) {
        return false;
      }
      // 更新最远可达距离
      maxReach = Math.max(maxReach, i + nums[i]);
      // 已经能到达或超过终点
      if (maxReach >= n - 1) {
        return true;
      }
    }
    return true;  // 遍历完所有位置，说明能到达终点
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    // 测试用例
    System.out.println(solution.canJump(new int[]{2, 3, 1, 1, 4}));  // true
    System.out.println(solution.canJump(new int[]{3, 2, 1, 0, 4}));  // false
    System.out.println(solution.canJump(new int[]{0}));              // true
    System.out.println(solution.canJump(new int[]{0, 2, 3}));        // false
  }
}
