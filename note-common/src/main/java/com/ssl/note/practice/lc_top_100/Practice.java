package com.ssl.note.practice.lc_top_100;

import java.util.ArrayList;
import java.util.List;

public class Practice {

  public List<List<String>> partition(String s) {
    if (s == null || s.isEmpty()) {
      return new ArrayList<>();
    }
    int n = s.length();
    boolean[][] dp = getDp(s, n);
    List<String> path = new ArrayList<>();
    List<List<String>> res = new ArrayList<>();
    dfs(s, 0, n, dp, path, res);

    return res;
  }

  private void dfs(String s, int i, int n, boolean[][] dp, List<String> path, List<List<String>> res) {
    if (i == n) {
      res.add(new ArrayList<>(path));
      return;
    }
    for (int j = i; j < n; j++) {
      if (!dp[i][j]) {
        continue;
      }
      path.add(s.substring(i, j + 1));
      dfs(s, j + 1, n, dp, path, res);
      path.remove(path.size() - 1);
    }
  }

  private boolean[][] getDp(String s, int n) {
    boolean[][] dp = new boolean[n][n];

    for (int len = 1; len <= n; len++) {
      for (int i = 0; i + len - 1 < n; i++) {
        int j = i + len - 1;
        if (len == 1) {
          dp[i][j] = true;
        } else if (s.charAt(i) != s.charAt(j)) {
          dp[i][j] = false;
        } else {
          dp[i][j] = len == 2 || dp[i + 1][j - 1];
        }
      }
    }
    return dp;
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "aab";
    System.out.println(practice.partition(s));
  }

}
