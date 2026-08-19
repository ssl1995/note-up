package com.ssl.note.leetcode.编号刷题.LC2141_同时运行N台电脑的最长时间;

public class Solution {

  /**
   * LC2141_同时运行N台电脑的最长时间
   * 你有 n 台电脑。给你整数 n 和一个下标从 0 开始的整数数组 batteries ，
   * 其中第 i 个电池可以让一台电脑 运行 batteries[i] 分钟。你想使用这些电池让 全部 n 台电脑 同时 运行。
   * 请你返回你可以让 n 台电脑同时运行的 最长 分钟数。
   * 输入：n = 2, batteries = [3,3,3]
   * 输出：4
   * 解释：
   * 一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 1 连接。
   * 2 分钟后，将第二台电脑与电池 1 断开连接，并连接电池 2 。注意，电池 0 还可以供电 1 分钟。
   * 在第 3 分钟结尾，你需要将第一台电脑与电池 0 断开连接，然后连接电池 1 。
   * 在第 4 分钟结尾，电池 1 也被耗尽，第一台电脑无法继续运行。
   * 我们最多能同时让两台电脑同时运行 4 分钟，所以我们返回 4 。
   */
  /**
   * 核心思路：二分答案 + 贪心验证
   *
   * 关键观察：
   * 1. 答案具有单调性：如果 n 台电脑能同时运行 m 分钟，那么一定能运行 m-1, m-2, ... 分钟；
   *    反之如果不能运行 m 分钟，则 m+1, m+2, ... 也都不行。因此可以用二分法求最大可行分钟数。
   * 2. 电池可以中途更换，所以电池电量可以看成一个「总量池」，不存在绑定关系。
   * 3. 理论上限：n 台电脑同时运行 t 分钟，总耗电量为 n * t，所以 t <= sum / n（sum 为总电量）。
   *
   * 时间复杂度：O(m * log(max))，m 为电池个数，max 为最大电池电量。
   */
  public long maxRunTime(int n, int[] batteries) {
    // 第一步：统计总电量 sum 和最大单块电池电量 max
    long sum = 0;
    int max = 0;
    for (int num : batteries) {
      max = Math.max(max, num);
      sum += num;
    }

    // 剪枝1：处理「电量充足」的特殊情况，直接返回答案，避免进入二分
    // 条件 sum >= max * n 的含义：
    //   把最大电量的那块电池单独供给一台电脑，它能独占运行 max 分钟；
    //   剩余电量 sum - max 足以支撑其余 n-1 台电脑也运行 max 分钟
    //   （因为 sum - max >= max * (n - 1)，即剩余电量池 >= 其余电脑所需）。
    // 此时所有电池都没有「溢出浪费」，答案达到理论上限 sum / n，直接返回。
    if (sum >= ((long) max * n)) {
      return sum / n;
    }

    long res = 0;
    // 剪枝2：二分上界的优化
    // 一般情况下上界应为 sum / n（理论上限），但由于没通过剪枝1，说明 sum < max * n，
    // 即 sum / n < max，所以可以直接用 max 作为上界，缩小二分范围。
    // 在 [0, max] 区间内二分最大的可行分钟数
    for (int l = 0, r = max, m; l <= r; ) {
      // 防止 (l + r) 溢出的中点写法
      m = l + (r - l) / 2;
      // 验证：能否让 n 台电脑同时运行 m 分钟？
      if (f(batteries, n, m)) {
        // 能运行 m 分钟：记录当前答案，并尝试更大的分钟数（向右收缩）
        res = m;
        l = m + 1;
      } else {
        // 不能运行 m 分钟：说明 m 太大了，尝试更小的分钟数（向左收缩）
        r = m - 1;
      }
    }

    return res;
  }

  // 让num台电脑共同运行time分钟，能不能做到
  private boolean f(int[] batteries, int num, int time) {
    // sum:碎片电量
    long sum = 0;
    for (int batter : batteries) {
      if (batter > time) {
        num--;
      } else {
        sum += batter;
      }

      // 碎片电量累加和 >= 电脑数*要求的时间，说明肯定能完成
      if (sum >= ((long) num * time)) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {18, 54, 2, 53, 87, 31, 71, 4, 29, 25};
    int n = 9;
    // 答案：6
    System.out.println(solution.maxRunTime(n, nums));
  }
}
