package com.ssl.note.leetcode.编号刷题.LC1109_航班预定统计;

import java.util.Arrays;

public class Solution {

  /**
   * LC1109_航班预定统计
   * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
   * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
   * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
   * 示例：
   * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
   * 输出：[10,55,45,25,25]
   * 解释：
   * 航班编号        1   2   3   4   5
   * 预订记录 1 ：   10  10
   * 预订记录 2 ：       20  20
   * 预订记录 3 ：       25  25  25  25
   * 总座位数：      10  55  45  25  25
   * 因此，answer = [10,55,45,25,25]
   */
  public int[] corpFlightBookings(int[][] bookings, int n) {
    // 1、设置差分数组,一开始航班的预定座位都为0
    int[] cnt = new int[n + 2];
    for (int[] book : bookings) {
      cnt[book[0]] += book[2];
      cnt[book[1] + 1] -= book[2];
    }
    // 2、加工前缀和
    for (int i = 1; i < n + 2; i++) {
      cnt[i] += cnt[i - 1];
    }
    // 3、找结果
    int[] res = new int[n];
    System.arraycopy(cnt, 1, res, 0, n);
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
    int n = 5;
    System.out.println(Arrays.toString(solution.corpFlightBookings(bookings, n)));
  }
}
