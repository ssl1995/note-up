package com.ssl.note.leetcode.编号刷题.LC2132_用邮票贴满网格图;

public class Solution {

  /**
   * LC2132_用邮票贴满网格图
   * 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
   * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
   * 覆盖所有 空 格子。
   * 不覆盖任何 被占据 的格子。
   * 我们可以放入任意数目的邮票。
   * 邮票可以相互有 重叠 部分。
   * 邮票不允许 旋转 。
   * 邮票必须完全在矩阵 内 。
   * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
   * 示例：
   * 输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
   * 输出：true
   * 解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
   */
  public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
    int m = grid.length;
    int n = grid[0].length;
    // 1、累加和数组，查全为0
    int[][] sum = new int[m + 1][n + 1];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        sum[i + 1][j + 1] = grid[i][j];
      }
    }
    build(sum);
    // 2、二维差分数组，快速构建贴邮票
    int[][] diff = new int[m + 2][n + 2];
    for (int a = 1, c = a + stampHeight - 1; c <= m; a++, c++) {
      for (int b = 1, d = b + stampWidth - 1; d <= n; b++, d++) {
        if (getRegionSum(sum, a, b, c, d) == 0) {
          // 二维区域指定+1,表示填了邮票
          // 思考：原始有1，0+1=1也是1，不关心相同，所以可以加1
          add(diff, a, b, c, d, 2);
        }
      }
    }
    // 对差分数组做累加和，还原为原数组
    build(diff);
    // 3、判断是否能贴满邮票
    // 原始数组有0的地方，差分数据没有发生过累加，那必然不能贴满邮票
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) {
          return false;
        }
      }
    }
    return true;
  }

  // 构建二维累加和
  private void build(int[][] nums) {
    for (int i = 1; i < nums.length; i++) {
      for (int j = 1; j < nums[0].length; j++) {
        nums[i][j] += nums[i - 1][j] + nums[i][j - 1] - nums[i - 1][j - 1];
      }
    }
  }

  // 二维前缀和数组中求(a,b)-(c,d)的累加和
  private int getRegionSum(int[][] sum, int a, int b, int c, int d) {
    if (sum == null) {
      return 0;
    }
    return sum[c][d] - sum[c][b - 1] - sum[a - 1][d] + sum[a - 1][b - 1];
  }

  // 二维差分数组添加数据
  private void add(int[][] nums, int a, int b, int c, int d, int v) {
    nums[a][b] += v;
    nums[c + 1][b] -= v;
    nums[a][d + 1] -= v;
    nums[c + 1][d + 1] += v;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] grid = {{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
    int h = 4, w = 3;
    System.out.println(solution.possibleToStamp(grid, h, w));
  }
}
