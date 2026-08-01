package com.ssl.note.leetcode.编号刷题.LC253_会议室II;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author SongShengLin
 * @date 2022/2/22 9:32 PM
 * @description
 */
public class Solution {

  /**
   * 会议室II
   * 给你一个会议时间安排的数组 intervals ，
   * 每个会议时间都会包括开始和结束的时间 intervals[i] = [start, end] ，
   * 返回 所需能安排的会议室的最多数量。
   * 输入：intervals = [[0,30],[5,10],[15,20]]
   * 输出：2
   */
  public int minMeetingRooms(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return 0;
    }
    // 1、开始时间升序
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    // 2、小根堆，存会议室结束时间
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    minHeap.offer(intervals[0][1]);

    // 3、遍历
    for (int i = 1; i < intervals.length; i++) {
      int start = intervals[i][0];
      int end = intervals[i][1];
      // 上一个会议的结束时间<= 新开始时间，表示会议室需要更新
      if (!minHeap.isEmpty() && minHeap.peek() <= start) {
        minHeap.poll();
      }
      // 最小堆保存当前会议的结束时间
      minHeap.offer(end);
    }

    return minHeap.size();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] nums = {{0, 30}, {5, 10}, {15, 20}};
    System.out.println(solution.minMeetingRooms(nums));
  }
}
