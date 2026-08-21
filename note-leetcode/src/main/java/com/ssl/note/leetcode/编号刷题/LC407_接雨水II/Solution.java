package com.ssl.note.leetcode.编号刷题.LC407_接雨水II;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

  /**
   * LC407_接雨水II
   * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
   * 输入: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
   * 输出: 4
   * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
   * 提示:
   * m == heightMap.length
   * n == heightMap[i].length
   * 1 <= m, n <= 200
   * 0 <= heightMap[i][j] <= 2 * 104
   */
  public int trapRainWater(int[][] heightMap) {
    // 防御性判断：空矩阵或行列数小于 3 时，只有一圈边界，无法存水
    if (heightMap == null || heightMap.length < 3 || heightMap[0].length < 3) {
      return 0;
    }
    int m = heightMap.length;
    int n = heightMap[0].length;

    // 最小堆：int[]{行, 列, 高度}，按高度升序，弹出的即当前“围墙”的最低点
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    boolean[][] visited = new boolean[m][n];

    // 1. 边界格子不可能存水，作为初始“围墙”全部入堆（角点只加一次）
    for (int i = 0; i < m; i++) {
      minHeap.offer(new int[]{i, 0, heightMap[i][0]});          // 左边界
      minHeap.offer(new int[]{i, n - 1, heightMap[i][n - 1]});  // 右边界
      visited[i][0] = visited[i][n - 1] = true;
    }
    for (int j = 1; j < n - 1; j++) {
      minHeap.offer(new int[]{0, j, heightMap[0][j]});          // 上边界
      minHeap.offer(new int[]{m - 1, j, heightMap[m - 1][j]});  // 下边界
      visited[0][j] = visited[m - 1][j] = true;
    }

    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int res = 0;

    // 2. 不断弹出最矮围墙向内部“灌水”：最矮围墙决定了内部低洼处的水位上限
    while (!minHeap.isEmpty()) {
      int[] cur = minHeap.poll();
      int h = cur[2];

      for (int[] dir : dirs) {
        int dirX = cur[0] + dir[0];
        int dirY = cur[1] + dir[1];
        if (dirX < 0 || dirX >= m || dirY < 0 || dirY >= n || visited[dirX][dirY]) {
          continue;
        }
        visited[dirX][dirY] = true;

        int dirH = heightMap[dirX][dirY];
        // 邻居比围墙低则存水 h-dirH，否则存 0；
        res += Math.max(h - dirH, 0);
        // 新围墙高度取 max(h, dirH)：低格子被水填平到 h，高格子以自身高度围水
        minHeap.offer(new int[]{dirX, dirY, Math.max(h, dirH)});
      }
    }
    return res;
  }

  // 手撕测试
  public int trapRainWaterTest(int[][] heightMap) {
    int m = heightMap.length;
    int n = heightMap[0].length;
    if (m < 3 || n < 3) {
      return 0;
    }

    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    boolean[][] visited = new boolean[m][n];

    for (int i = 0; i < n; i++) {
      queue.offer(new int[]{0, i, heightMap[0][i]});
      queue.offer(new int[]{m - 1, i, heightMap[m - 1][i]});
      visited[0][i] = true;
      visited[m - 1][i] = true;
    }
    for (int i = 1; i < m - 1; i++) {
      queue.offer(new int[]{i, 0, heightMap[i][0]});
      queue.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
      visited[i][0] = true;
      visited[i][n - 1] = true;
    }

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int res = 0;
    while (!queue.isEmpty()) {
      int[] poll = queue.poll();
      int x = poll[0];
      int y = poll[1];
      int h = poll[2];

      for (int i = 0; i < 4; i++) {
        int[] offset = dirs[i];
        int dirX = x + offset[0];
        int dirY = y + offset[1];
        if (dirX < 0 || dirX > m - 1 || dirY < 0 || dirY > n - 1 || visited[dirX][dirY]) {
          continue;
        }
        visited[dirX][dirY] = true;

        int dirH = heightMap[dirX][dirY];

        res += Math.max(h - dirH, 0);
        queue.offer(new int[]{dirX, dirY, Math.max(h, dirH)});
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] h = {
        {3, 3, 3},
        {3, 2, 3},
        {3, 3, 3}};
    System.out.println(solution.trapRainWater(h));
    System.out.println(solution.trapRainWater(h) == solution.trapRainWaterTest(h));
  }
}
