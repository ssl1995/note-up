package com.ssl.note.leetcode.编号刷题.LC2560_打家劫舍IV;

import java.util.Arrays;

public class Solution {

  /**
   * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
   * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
   * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
   * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
   * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
   * 返回小偷的 最小 窃取能力。
   * 输入：nums = [2,3,5,9], k = 2
   * 输出：5
   * 解释：
   * 小偷窃取至少 2 间房屋，共有 3 种方式：
   * - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
   * - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
   * - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
   * 因此，返回 min(5, 9, 9) = 5 。
   */
  public int minCapability(int[] nums, int k) {
    int left = Arrays.stream(nums).min().getAsInt();
    int right = Arrays.stream(nums).max().getAsInt();
    while (left < right) {
      int mid = left + (right - left) / 2;
      // 转换思维：求最小盗窃能力=判断行不行
      // 给定一个能力值 cap，能不能做到：只偷金额 ≤ cap 的房子，不偷相邻，且至少偷 k 间
      if (canRob(nums, k, mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    // left会越过去
    return left;
  }

  /**
   * 判断：nums中，给定能力值 cap，能否偷至少 k 间房子
   */
  private boolean canRob(int[] nums, int k, int cap) {
    int count = 0;
    int i = 0;
    while (i < nums.length) {
      // 贪心：cap够的话，偷当前这间，不比不偷更差
      // 注意：这里是<=cap
      if (nums[i] <= cap) {
        i += 2;
        count++;
      } else {
        i++;
      }
      if (count >= k) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 3, 5, 9};
    int k = 2;
    System.out.println(solution.minCapability(nums, k));
  }
}
