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
public class Solution {
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

    // list套int[]
    List<int[]> res = new ArrayList<>();
    for (int[] nums : intervals) {
      int a = nums[0];
      int b = nums[1];

      // 结果空|| 6<8就记录结果
      if (res.isEmpty() || res.get(res.size() - 1)[1] < a) {
        res.add(new int[]{a, b});
      } else {
        // 3>2就要更新
        // 选择最大的返回：[1,4]和[2,3]比较，输出[1,4]
        res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], b);

      }
    }
    // new int[0][]:告诉JVM，需要什么类型
//    return convert(res);
    return res.toArray(new int[res.size()][]);
  }

  private int[][] convert(List<int[]> res) {
    int[][] nums = new int[res.size()][];

    for (int i = 0; i < res.size(); i++) {
      int[] ints = res.get(i);
      for (int j = 0; j < ints.length; j++) {
        nums[i][j] = ints[j];
      }
    }

    return nums;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] nums = {{1, 3}, {8, 10}, {15, 18}, {2, 6}};
    System.out.println(Arrays.deepToString(solution.merge(nums)));
  }
}
