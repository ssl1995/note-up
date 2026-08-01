package com.ssl.note.leetcode.编号刷题.LC2406_将区间分为最小组数;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

  /**
   * LC2406_将区间分为最小组数
   * 给你一个二维整数数组 intervals
   * 你需要将 intervals 划分为一个或者多个区间 组 ，每个区间只属于一个组，且同一个组中任意两个区间不相交 。
   * 请你返回 最少 需要划分成多少个组。
   * 如果两个区间覆盖的范围有重叠（即至少有一个公共数字），那么我们称这两个区间是 相交 的。比方说区间 [1, 5] 和 [5, 8] 相交。
   * 输入：intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
   * 输出：3
   * 解释：我们可以将区间划分为如下的区间组：
   * - 第 1 组：[1, 5] ，[6, 8] 。
   * - 第 2 组：[2, 3] ，[5, 10] 。
   * - 第 3 组：[1, 10] 。
   * 可以证明无法将区间划分为少于 3 个组。
   */
  public int minGroups(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return 0;
    }
    // 1、开始时间升序
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    // 2、小根堆，存结束时间
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    minHeap.offer(intervals[0][1]);

    // 3、遍历
    for (int i = 1; i < intervals.length; i++) {
      int start = intervals[i][0];
      int end = intervals[i][1];

      if (!minHeap.isEmpty() && minHeap.peek() < start) {
        minHeap.poll();
      }

      minHeap.offer(end);
    }

    return minHeap.size();
  }
}
