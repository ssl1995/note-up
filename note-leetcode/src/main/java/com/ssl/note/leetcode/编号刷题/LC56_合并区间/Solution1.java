package com.ssl.note.leetcode.编号刷题.LC56_合并区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/26 11:08 PM
 * @description
 */
public class Solution1 {
  /**
   * 合并区间
   * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
   * 输出：[[1,6],[8,10],[15,18]]
   */
  public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return new int[0][2];
    }
    // 按照左端点排序:[[1,3],[2,6],[8,10],[15,18]]
    Arrays.sort(intervals, Comparator.comparingInt(num -> num[0]));

    // list套int[]：1、循环判断好赋值 2、返回值短
    List<int[]> res = new ArrayList<>();
    for (int[] nums : intervals) {
      int a = nums[0];
      int b = nums[1];

      // 直接加：结果空 或 当前数左端点>上一个区间的右边
      if (res.isEmpty() || a > res.get(res.size() - 1)[1]) {
        res.add(new int[]{a, b});
      } else {
        // 不加需要更新：当前数右端点<=上一个区间有点，更新上一个区间右端点为彼此最右
        res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], b);
      }
    }
    // new int[0][]:告诉JVM，需要什么类型
    return res.toArray(new int[res.size()][]);
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[][] nums = {{1, 3}, {8, 10}, {15, 18}, {2, 6}};
    System.out.println(Arrays.deepToString(solution.merge(nums)));
  }
}
