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
    // 防御性判断：空矩阵无法接雨水
    if (heightMap == null) {
      return 0;
    }

    // m 为行数，n 为列数
    int m = heightMap.length;
    int n = heightMap[0].length;

    // 如果行数或列数小于 3，矩阵只有一圈边界，没有内部格子，直接返回 0
    if (m < 3 || n < 3) {
      return 0;
    }

    // 最小堆：排序是按格子高度升序
    // 数组含义：int[]{行坐标, 列坐标, 格子高度}
    // 每次弹出高度最小的边界，该高度就是当前能围住内部格子的“水面上限”
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
//    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);

    // visited 数组标记格子是否已经加入过堆，避免重复处理
    boolean[][] visited = new boolean[m][n];

    /**
     * 与数组中的xy轴不一样，这里是反的，以int[i][j]为例
     * i:上下。j：左右
     *       j=0 j=1 j=2 j=3 j=4 j=5
     * i=0    1   4   3   1   3   2
     * i=1    3  [2] [1]  3  [2]  4
     * i=2    2   3   3   2   3   1
     */

    // 第一步：把矩阵最外层的所有边界格子加入最小堆
    // 边界格子自身不可能存水，但它们会成为围堵内部格子的“围墙”
    // 处理左右两列边界
    for (int i = 0; i < m; i++) {
      minHeap.offer(new int[]{i, 0, heightMap[i][0]});          // 左边界
      minHeap.offer(new int[]{i, n - 1, heightMap[i][n - 1]});  // 右边界
      visited[i][0] = true;
      visited[i][n - 1] = true;
    }
    // 处理上下两行边界（注意角点已经在上面加入过，这里从 1 到 n-2 避免重复）
    for (int j = 1; j < n - 1; j++) {
      minHeap.offer(new int[]{0, j, heightMap[0][j]});          // 上边界
      minHeap.offer(new int[]{m - 1, j, heightMap[m - 1][j]});  // 下边界
      visited[0][j] = true;
      visited[m - 1][j] = true;
    }

    // 四个方向数组：上、下、左、右，用于向内部扩展
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // res 用于累计总接雨水量
    int res = 0;

    // 第二步：从最小边界开始向内部扩展，类似“灌水”过程
    while (!minHeap.isEmpty()) {
      // 弹出当前高度最小的边界格子
      // 每次弹出的cur是当前所有边界中最矮的，任何比它低的内部格子都不可能通过其他路径把水排出去，因此一定能存水
      int[] cur = minHeap.poll();
      int curX = cur[0];
      int curY = cur[1];
      int curHeight = cur[2];

      // 遍历当前边界的四个邻居
      for (int[] dir : dirs) {
        int dirX = curX + dir[0];
        int dirY = curY + dir[1];

        // 越界或者已经访问过的格子跳过
        if (dirX < 0 || dirX >= m || dirY < 0 || dirY >= n || visited[dirX][dirY]) {
          continue;
        }

        // 标记该邻居已经被处理过
        visited[dirX][dirY] = true;

        // 核心逻辑：
        // 当前最矮边界高度为 h，如果邻居格子比 h 还低，
        // 说明它处于当前最低水位之下，可以存水
        if (heightMap[dirX][dirY] < curHeight) {
          // 存水量 = 当前边界高度 - 邻居自身高度
          res += curHeight - heightMap[dirX][dirY];

          // 该格子接水后，相当于水面高度为 h，
          // 因此它作为新的边界时，能提供的最大高度就是 h
          minHeap.offer(new int[]{dirX, dirY, curHeight});
        } else {
          // 邻居高度不低于当前边界，接不住水，
          // 它作为新边界的高度就是自身高度
          minHeap.offer(new int[]{dirX, dirY, heightMap[dirX][dirY]});
        }
      }
    }

    // 返回最终累计的接雨水体积
    return res;
  }

  // 手撕测试
  public int trapRainWaterTest(int[][] heightMap) {
    if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
      return 0;
    }
    int m = heightMap.length;
    int n = heightMap[0].length;
    if (m < 3 || n < 3) {
      return 0;
    }

    PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    boolean[][] visited = new boolean[m][n];
    for (int i = 0; i < m; i++) {
      minHeap.offer(new int[]{i, 0, heightMap[i][0]});
      minHeap.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
      visited[i][0] = true;
      visited[i][n - 1] = true;
    }
    for (int j = 1; j < n - 1; j++) {
      minHeap.offer(new int[]{0, j, heightMap[0][j]});
      minHeap.offer(new int[]{m - 1, j, heightMap[m - 1][j]});
      visited[0][j] = true;
      visited[m - 1][j] = true;
    }

    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int res = 0;
    while (!minHeap.isEmpty()) {
      int[] poll = minHeap.poll();
      int curX = poll[0];
      int curY = poll[1];
      int curHeight = poll[2];

      for (int[] dir : dirs) {
        int dirX = curX + dir[0];
        int dirY = curY + dir[1];

        if (dirX < 0 || dirX >= m || dirY < 0 || dirY >= n || visited[dirX][dirY]) {
          continue;
        }

        visited[dirX][dirY] = true;

        if (curHeight > heightMap[dirX][dirY]) {
          res += curHeight - heightMap[dirX][dirY];

          minHeap.offer(new int[]{dirX, dirY, curHeight});
        } else {
          minHeap.offer(new int[]{dirX, dirY, heightMap[dirX][dirY]});
        }
      }

    }

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] h = {
        {1, 4, 3, 1, 3, 2},
        {3, 2, 1, 3, 2, 4},
        {2, 3, 3, 2, 3, 1}};
    System.out.println(solution.trapRainWater(h) == solution.trapRainWaterTest(h));
  }
}
