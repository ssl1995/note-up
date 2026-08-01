package com.ssl.note.leetcode.编号刷题.LC2208_将数组和减少一半的最小操作数;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

  /**
   * LC2208_将数组和减少一半的最小操作数
   * 给你一个正整数数组 nums 。每一次操作中，你可以从 nums 中选择 任意 一个数并将它减小到 恰好 一半。（注意，在后续操作中你可以对减半过的数继续执行操作）
   * 请你返回将 nums 数组和 至少 减少一半的 最少 操作数。
   * 输入：nums = [5,19,8,1]
   * 输出：3
   * 解释：初始 nums 的和为 5 + 19 + 8 + 1 = 33 。
   * 以下是将数组和减少至少一半的一种方法：
   * 选择数字 19 并减小为 9.5 。
   * 选择数字 9.5 并减小为 4.75 。
   * 选择数字 8 并减小为 4 。
   * 最终数组为 [5, 4.75, 4, 1] ，和为 5 + 4.75 + 4 + 1 = 14.75 。
   * nums 的和减小了 33 - 14.75 = 18.25 ，减小的部分超过了初始数组和的一半，18.25 >= 33/2 = 16.5 。
   * 我们需要 3 个操作实现题目要求，所以返回 3 。
   * 可以证明，无法通过少于 3 个操作使数组和减少至少一半。
   */
  public int halveArray(int[] nums) {
    if (nums == null) {
      return 0;
    }
    int n = nums.length;
    if (n == 1) {
      return 1;
    }
    // 大根堆
    PriorityQueue<Double> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    double sum = 0L;
    for (int num : nums) {
      sum += num;
      maxHeap.offer((double) num);
    }

    // 贪心：每次移动最大值减少一半，目标是减少的数>=一半就停止
    double minus = 0;
    double target = sum / 2;

    int res = 0;
    // 例子中给出了减少>=一半，反过来：减少<一半就遍历
    while (minus < target) {
      Double poll = maxHeap.poll();
      poll /= 2;
      // 出栈的变成一半还得入堆，重新计算
      maxHeap.offer(poll);

      minus += poll;
      res++;
    }

    return res;
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {5, 19, 8, 1};
    System.out.println(solution.halveArray(nums));
  }
}
