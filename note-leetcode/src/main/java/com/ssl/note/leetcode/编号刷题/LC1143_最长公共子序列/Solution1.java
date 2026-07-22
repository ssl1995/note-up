package com.ssl.note.leetcode.编号刷题.LC1143_最长公共子序列;

public class Solution1 {

  /**
   * 最长公共子序列 - 更简洁的二维DP写法
   * 输入：text1 = "abcde", text2 = "ade" 输出：3
   * 解释：最长公共子序列是 "ade"，它的长度为 3。
   */
  public int longestCommonSubsequence1(String text1, String text2) {
    if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
      return 0;
    }

    int m = text1.length();
    int n = text2.length();

    // dp[i][j]:表示text1[0,i]和text2[0,j]最长公共子序列
    // 初始化为m和n的长度，就需要特判初始化
    int[][] dp = new int[m][n];

    // 初始化第一行和第一列
    dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
    for (int i = 1; i < m; i++) {
      dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[i - 1][0];
    }
    for (int i = 1; i < n; i++) {
      dp[0][i] = text1.charAt(0) == text2.charAt(i) ? 1 : dp[0][i - 1];
    }

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (text1.charAt(i) == text2.charAt(j)) {
          // 当前字符相等，可以被选入公共子序列
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          // 当前字符不相等，舍弃其中一个，取较大值
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[m - 1][n - 1];
  }

  // 优化成n+1的初始化数组长度，特判就会被优化掉
  public int longestCommonSubsequence2(String text1, String text2) {
    if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
      return 0;
    }

    int m = text1.length();
    int n = text2.length();

    // dp[i][j] 表示 text1[0..i-1] 和 text2[0..j-1] 的最长公共子序列长度
    // 大小为 (m+1) × (n+1)，第0行和第0列表示空字符串，默认值0
    int[][] dp = new int[m + 1][n + 1];

    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          // 当前字符相等，可以被选入公共子序列
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          // 当前字符不相等，舍弃其中一个，取较大值
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[m][n];
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    String s1 = "abcde";
    String s2 = "ade";
    System.out.println(solution1.longestCommonSubsequence1(s1, s2) == solution1.longestCommonSubsequence2(s1, s2));  // 输出 3
  }
}
