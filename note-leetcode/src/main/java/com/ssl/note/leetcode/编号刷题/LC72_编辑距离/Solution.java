package com.ssl.note.leetcode.编号刷题.LC72_编辑距离;

public class Solution {

  /**
   * 编辑距离
   * 返回将word1转换成word2所使用的最少操作数
   * 输入：word1 = "intention", word2 = "execution"
   * 输出：5
   * 解释：
   * intention -> inention (删除 't')
   * inention -> enention (将 'i' 替换为 'e')
   * enention -> exention (将 'n' 替换为 'x')
   * exention -> exection (将 'n' 替换为 'c')
   * exection -> execution (插入 'u')
   */
  public int minDistance(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();
    // dp[i][j] 表示 word1 的前 i 个字符转换成 word2 的前 j 个字符所需的最少操作数
    int[][] dp = new int[m + 1][n + 1];
    // 初始化
    dp[0][0] = 0;
    // word2 为空，只能把 word1 的字符一个个删除
    for (int i = 0; i < m + 1; i++) {
      dp[i][0] = i;
    }
    // word1 为空，只能往 word2 一个个插入
    for (int j = 0; j < n + 1; j++) {
      dp[0][j] = j;
    }
    // 动态转移
    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        // 字母相同：不做操作
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
          continue;
        }
        // 删除：dp[i-1][j] + 1
        // 新增：dp[i][j-1] + 1
        // 替换：dp[i-1][j-1] + 1
        // 字母不相同：（删除、新增、替换取最小） + 1
        int min1 = Math.min(dp[i][j - 1], dp[i - 1][j - 1]);
        int min = Math.min(min1, dp[i - 1][j - 1]);
        dp[i][j] = min + 1;
      }
    }

    return dp[m][n];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String w1 = "intention";
    String w2 = "execution";
    System.out.println(solution.minDistance(w1, w2));
  }
}
