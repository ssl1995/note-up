package com.ssl.note.practice.lc_top_100;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Practice {

  private int fresh;

  public int orangesRotting(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    Deque<int[]> queue = new ArrayDeque<>();
    fresh = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          fresh++;
        } else if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j});
        }
      }
    }

    if (fresh == 0) {
      return 0;
    }

    int times = -1;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        int[] poll = queue.poll();
        int x = poll[0];
        int y = poll[1];
        dfs(grid, x + 1, y, queue);
        dfs(grid, x - 1, y, queue);
        dfs(grid, x, y + 1, queue);
        dfs(grid, x, y - 1, queue);
      }
      times++;
    }

    return fresh != 0 ? -1 : times;
  }

  private void dfs(int[][] grid, int x, int y, Queue<int[]> queue) {
    if (x < 0 || x > grid.length - 1 || y < 0 || y > grid[0].length - 1) {
      return;
    }
    if (grid[x][y] == 1) {
      grid[x][y] = 2;
      queue.offer(new int[]{x, y});
      fresh--;
    }
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[][] grod = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    System.out.println(practice.orangesRotting(grod));
  }

}
