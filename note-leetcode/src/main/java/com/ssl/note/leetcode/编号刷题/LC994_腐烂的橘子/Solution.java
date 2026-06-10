package com.ssl.note.leetcode.编号刷题.LC994_腐烂的橘子;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

  /**
   * 腐烂的橘子
   * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
   * 值 0 代表空单元格；
   * 值 1 代表新鲜橘子；
   * 值 2 代表腐烂的橘子。
   * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
   * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
   * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
   * 输出：4
   */
  public int orangesRotting(int[][] grid) {
    if (grid == null) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    // 1、多路BFS，腐烂橘子入队列，同时计算新鲜橘子数量
    int fresh = 0;
    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          fresh++;
        } else if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j});
        }
      }
    }
    // 2、如果没有新鲜橘子
    if (fresh == 0) {
      return 0;
    }
    // 4叉树的高度，根节点需要-1分钟，第二层节点需要0分钟
    int times = -1;
    // 遍历四个方向
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    // 3、层次遍历队列
    while (!queue.isEmpty()) {
      int size = queue.size();
      // 4叉树的高度 = 层数，就是需要的时间数
      times++;

      while (size-- > 0) {
        int[] position = queue.poll();
        for (int i = 0; i < 4; i++) {
          int[] dir = dirs[i];
          int x = position[0] + dir[0];
          int y = position[1] + dir[1];
          if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
            grid[x][y] = 2;
            fresh--;
            queue.offer(new int[]{x, y});
          }
        }
      }
    }

    return fresh == 0 ? times : -1;
  }
}
