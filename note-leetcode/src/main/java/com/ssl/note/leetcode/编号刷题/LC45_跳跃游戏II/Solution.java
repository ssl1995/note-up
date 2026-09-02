package com.ssl.note.leetcode.编号刷题.LC45_跳跃游戏II;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:58 AM
 * @description 跳跃游戏II - 求最少跳跃次数
 */
public class Solution {

  /**
   * 跳跃游戏II
   * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置在下标 0。
   * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。
   * 返回到达 n - 1 的最小跳跃次数。测试用例保证可以到达 n - 1。
   *
   * 【核心思路】贪心 + 分层扩展
   * 把跳跃想象成"层"的概念：
   * - end 表示当前这一步能到达的边界（当前层的右边界）
   * - maxReach 表示下一步能到达的最远位置（下一层的右边界）
   * - 当遍历到当前层边界 end 时，必须再跳一步，进入下一层
   *
   * 【统一写法】与 LC55 对比记忆：
   * - LC55 问"能不能到" → 关注 i > maxReach（走不动了）
   * - LC45 问"最少几步" → 关注 i == end（必须起跳了）
   *
   * 【示例演示】nums = [2,3,1,1,4]
   * 索引:   0   1   2   3   4
   * 数值:   2   3   1   1   4
   *
   * i=0: maxReach=max(0,0+2)=2, i==end(0), end=2, steps=1
   *      → 第1步能到 [0,2]，下一步最远到2
   * i=1: maxReach=max(2,1+3)=4, i!=end(2)
   * i=2: maxReach=max(4,2+1)=4, i==end(2), end=4, steps=2
   *      → 第2步能到 [3,4]，已覆盖终点
   * 返回 steps=2
   *
   * 输入: nums = [2,3,1,1,4]
   * 输出: 2
   */
  public int jump(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return 0;
    }
    int n = nums.length;
    int maxReach = 0;  // 下一步能到达的最远位置
    int end = 0;       // 当前这一步能到达的边界
    int steps = 0;     // 跳跃次数

    // 注意：只需遍历到 n-2，因为到达 n-1 时不需要再跳
    for (int i = 0; i < n - 1; i++) {
      // 更新下一步能到达的最远位置
      maxReach = Math.max(maxReach, i + nums[i]);

      // 关键判断：到达当前步的边界，必须再跳一步
      if (i == end) {
        end = maxReach;  // 更新边界为下一步的最远位置
        steps++;
      }
    }
    return steps;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    // 测试用例
    System.out.println(solution.jump(new int[]{2, 3, 1, 1, 4}));  // 2
    System.out.println(solution.jump(new int[]{2, 3, 0, 1, 4}));  // 2
    System.out.println(solution.jump(new int[]{1, 2, 3}));        // 2
    System.out.println(solution.jump(new int[]{0}));              // 0
    System.out.println(solution.jump(new int[]{1}));              // 0
  }
}
