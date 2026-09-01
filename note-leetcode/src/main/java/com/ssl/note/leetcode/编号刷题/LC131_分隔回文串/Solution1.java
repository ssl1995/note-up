package com.ssl.note.leetcode.编号刷题.LC131_分隔回文串;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

  /**
   * 分割回文串
   * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
   * 输入：s = "aab"
   * 输出：[["a","a","b"],["aa","b"]]
   */
  public List<List<String>> partition(String s) {
    if (s == null) {
      return new ArrayList<>();
    }
    int n = s.length();
    // 将s任意长度是否是字符串放到dp表里
    boolean[][] dp = getDp1(s, n);

    List<List<String>> res = new ArrayList<>();
    dfs(s, 0, dp, new ArrayList<>(), res);
    return res;
  }

  private void dfs(String s, int i, boolean[][] dp, List<String> path, List<List<String>> res) {
    if (i == s.length()) {
      res.add(new ArrayList<>(path));
      return;
    }
    for (int j = i; j < s.length(); j++) {
      // 用dp判断回文串，时间复杂度O(1)
      if (!dp[i][j]) {
        continue;
      }
      path.add(s.substring(i, j + 1));
      // 一定是从j+1开始
      dfs(s, j + 1, dp, path, res);
      path.remove(path.size() - 1);
    }
  }

  // 写法1：按区间长度从小到大（区间DP通用模板）
  // 子问题区间一定比当前短，短区间已填好
  private boolean[][] getDp1(String s, int n) {
    boolean[][] dp = new boolean[n][n];

    for (int len = 1; len <= n; len++) {
      for (int i = 0; i + len - 1 < n; i++) {
        int j = i + len - 1;
        // 区间1
        if (len == 1) {
          dp[i][j] = true;
        } else if (s.charAt(i) != s.charAt(j)) {
          dp[i][j] = false;
        } else {
          // 区间>1
          dp[i][j] = len == 2 || dp[i + 1][j - 1];
        }
      }
    }
    return dp;
  }

  // 写法2：j 从左到右（外层），i 从上到下
  // dp[i][j] 依赖左下方 dp[i+1][j-1]，它在第 j-1 列，外层先扫到，必然已填好
  private boolean[][] getDp2(String s, int n) {
    boolean[][] dp = new boolean[n][n];
    // 外层是长度=列优先
    for (int j = 0; j < n; j++) {
      // 内层是起点
      for (int i = 0; i <= j; i++) {
        if (s.charAt(i) != s.charAt(j)) {
          continue;
        }
        dp[i][j] = (j - i <= 1) || dp[i + 1][j - 1];
      }
    }
    return dp;
  }

  // 写法3：i 从下到上，j 从左到右
  private boolean[][] getDp3(String s, int n) {
    boolean[][] dp = new boolean[n][n];
    // i倒序，j正序
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j <= n - 1; j++) {
        if (s.charAt(i) != s.charAt(j)) {
          continue;
        }

        dp[i][j] = (j - i + 1 <= 2) || dp[i + 1][j - 1];
      }
    }
    return dp;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "aab";
    int n = s.length();
    // 三种写法结果应一致
    System.out.println(Arrays.deepEquals(solution.getDp1(s, n), solution.getDp2(s, n)));
    System.out.println(Arrays.deepEquals(solution.getDp1(s, n), solution.getDp3(s, n)));

    System.out.println(solution.partition(s));
  }
}
