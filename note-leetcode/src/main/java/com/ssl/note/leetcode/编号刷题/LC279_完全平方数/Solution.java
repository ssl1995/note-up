package com.ssl.note.leetcode.编号刷题.LC279_完全平方数;

public class Solution {

  /**
   * 完全平方数
   * 输入：n = 12
   * 输出：3
   * 解释：12 = 4 + 4 + 4
   * 题目约束：1<=n<=10^4
   */
  public int numSquares(int n) {
    // 拆解子问题：n = (n - j²) + j²
    // 那么n = 组成 (n - j²) 的最少个数 + 1，j从1到根号i尝试
    // dp[i]表示组成整数i+1的最小平方数
    int[] dp = new int[n];
    // 初始化第一个数
    dp[0] = 1;

    for (int i = 1; i < n; i++) {
      int num = i + 1;
      dp[i] = num;
      // 初始化数组长度是n,枚举j时，dp[(i+1)-j*j]会越界
      // 比如，n=4，dp长度4，下标范围 0~3，j=2，dp[3-4]越界
      for (int j = 1; j * j <= num; j++) {
        int preNum = num - j * j;
        // 刚好整除，自己就是完全平方数
        if (preNum == 0) {
          dp[i] = 1;
        } else {
          // 剩余数preNum的下标是preNum-1
          dp[i] = Math.min(dp[i], dp[preNum - 1] + 1);
        }
      }
    }
    return dp[n - 1];
  }

  // 规避数组下标越界的特判，数组长度初始化n+1
  public int numSquares1(int n) {
    // 数组长度为什么是 n+1？
    // 因为：数组下标与数对应上，枚举j不会越界
    int[] dp = new int[n + 1];
    // 题目n>=1，思考如何推导dp[0]=0
    // dp[1] = min(dp[1 - 1*1] + 1)=dp[0]+1 =1
    // 推导出来dp[0]=0，表示数字0不需要任何完全平方数
    dp[0] = 0;

    for (int i = 1; i <= n; i++) {
      // 组成i的最少平方数，最多可以有i个1
      dp[i] = i;
      // 注意：j*j<=i,是小于等于
      for (int j = 1; j * j <= i; j++) {
        dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 12;
    // dp=[0, 1, 2, 3, 1, 2, 3, 4, 2, 1, 2, 3, 3]
    System.out.println(solution.numSquares(n) == solution.numSquares1(n));
  }
}

