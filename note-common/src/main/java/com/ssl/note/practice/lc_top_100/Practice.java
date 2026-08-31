package com.ssl.note.practice.lc_top_100;

import java.util.ArrayDeque;
import java.util.Deque;

public class Practice {

  private int fresh = 0;

  public int orangesRotting(int[][] grid) {
    if (grid == null) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    Deque<int[]> queue = new ArrayDeque<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          fresh++;
        }
        if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j});
        }
      }
    }

    if (fresh == 0) {
      return 0;
    }

    int res = -1;
    while (!queue.isEmpty()) {
      int size = queue.size();

      while (size-- > 0) {
        int[] pos = queue.poll();
        int i = pos[0];
        int j = pos[1];
        f(grid, i - 1, j, queue);
        f(grid, i + 1, j, queue);
        f(grid, i, j - 1, queue);
        f(grid, i, j + 1, queue);
      }
      res++;
    }

    return fresh == 0 ? res : -1;
  }

  private void f(int[][] grid, int i, int j, Deque<int[]> queue) {
    if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1) {
      return;
    }
    if (grid[i][j] == 1) {
      grid[i][j] = 2;
      fresh--;
      queue.offer(new int[]{i, j});
    }
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "10[abc]";

  }

}
