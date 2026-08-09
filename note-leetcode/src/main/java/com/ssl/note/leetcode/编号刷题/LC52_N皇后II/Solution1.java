package com.ssl.note.leetcode.编号刷题.LC52_N皇后II;

public class Solution1 {

  /**
   * LC52_N皇后II
   * 求有效n皇后的个数
   * 示例：
   * n=4,返回2
   * 限制：
   * 1 <= n <= 9
   */
  public int totalNQueens(int n) {
    if (n < 0) {
      return 0;
    }
    int limit = (1 << n) - 1;
    return dfs(limit, 0, 0, 0);
  }

  /**
   * 位运算法
   *
   * @param limit n位置的限制，比如n=4，二进制001000，1前面的2个0必须都为0
   * @param col   列位置
   * @param left  右上到左下位置
   * @param right 左上到右下位置
   */
  private int dfs(int limit, int col, int left, int right) {
    if (col == limit) {
      return 1;
    }
    // 1不能放、0能放
    int ban = col | left | right;
    // 1能放，0不能放,这样的设计方便while循环条件+找右侧1
    int candidate = limit & (~ban);
    int place;

    int res = 0;
    while (candidate != 0) {
      // 找最右侧1作为新位置放皇后
      place = candidate & -candidate;
      // 抹掉候选位置最右侧的1作为新候选
      candidate ^= place;

      res += dfs(limit, col | place, (left | place) >> 1, (right | place) << 1);
    }

    return res;
  }
}
