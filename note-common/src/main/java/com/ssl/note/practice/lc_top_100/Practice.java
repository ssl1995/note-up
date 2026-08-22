package com.ssl.note.practice.lc_top_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Practice {

  /**
   * 1. 16-多维动态规划 LC 62 不同路径
   * 2. 15-动态规划 LC 152 乘积最大子数组
   */
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

    List<int[]> res = new ArrayList<>();
    for (int[] interval : intervals) {
      int a = interval[0];
      int b = interval[1];

      if (res.isEmpty() || res.get(res.size() - 1)[1] < a) {
        res.add(new int[]{a, b});
      } else {
        //
        res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], b);
      }
    }

    return res.toArray(new int[res.size()][]);
  }


  public static void main(String[] args) {
    Practice solution = new Practice();
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> l1 = new ArrayList<>();
    l1.add(1);
    l1.add(2);
    List<Integer> l2 = new ArrayList<>();
    l2.add(3);
    l2.add(4);
    res.add(l1);
    res.add(l2);
  }
}
