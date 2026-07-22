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
  public int minDistance1(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();
    if (m == 0) {
      return n;
    }
    if (n == 0) {
      return m;
    }

    // dp[i][j]表示word1的前i个字符转换成word2的前j个字符所需的最少操作数
    int[][] dp = new int[m][n];
    // 初始化
    dp[0][0] = word1.charAt(0) == word2.charAt(0) ? 0 : 1;
    // word1[0..i]转换成word2[0]
    // 若word1[i]==word2[0]，该字符可保留匹配，只需删掉前面i个字符 -> i
    // 否则在前一格基础上多一次操作 -> dp[i-1][0]+1
    for (int i = 1; i < m; i++) {
      dp[i][0] = word1.charAt(i) == word2.charAt(0) ? i : dp[i - 1][0] + 1;
    }
    // word1[0]转换成word2[0..j]
    for (int j = 1; j < n; j++) {
      dp[0][j] = word1.charAt(0) == word2.charAt(j) ? j : dp[0][j - 1] + 1;
    }

    // 动态转移
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        // 字母相同：不做操作
        if (word1.charAt(i) == word2.charAt(j)) {
          dp[i][j] = dp[i - 1][j - 1];
          continue;
        }
        // 字母不相同：才有有需要操作

        // 删除：删掉word1[i-1]-> dp[i-1][j] + 1
        int delete = dp[i - 1][j] + 1;
        // 新增：往word1末尾插入word2[j-1]-> dp[i][j-1] + 1
        // 新增最难理解，可以反过来想，word1新增=word2删除
        int insert = dp[i][j - 1] + 1;
        // 替换：把 word1[i-1] 改成 word2[j-1]-> dp[i-1][j-1] + 1
        int update = dp[i - 1][j - 1] + 1;

        dp[i][j] = Math.min(Math.min(delete, insert), update);
      }
    }

    return dp[m - 1][n - 1];
  }

  // 优化成m+1和n+1的版本
  public int minDistance2(String word1, String word2) {
    int m = word1.length();
    int n = word2.length();
    // dp[i][j]表示word1的前i个字符转换成word2的前j个字符所需的最少操作数
    int[][] dp = new int[m + 1][n + 1];
    // 初始化
    dp[0][0] = 0;
    // word2为空，只能把word1的字符一个个删除
    for (int i = 0; i < m + 1; i++) {
      dp[i][0] = i;
    }
    // word1为空，只能往word2一个个插入
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
        // 字母不相同：才有有需要操作

        // 删除：删掉word1[i-1]-> dp[i-1][j] + 1
        int delete = dp[i - 1][j] + 1;
        // 新增：往word1末尾插入word2[j-1]-> dp[i][j-1] + 1
        // 新增最难理解，可以反过来想，word1新增=word2删除
        int insert = dp[i][j - 1] + 1;
        // 替换：把 word1[i-1] 改成 word2[j-1]-> dp[i-1][j-1] + 1
        int update = dp[i - 1][j - 1] + 1;

        dp[i][j] = Math.min(Math.min(delete, insert), update);
      }
    }

    return dp[m][n];
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    String w1 = "intention";
    String w2 = "execution";
    System.out.println(solution.minDistance1(w1, w2));
    System.out.println(solution.minDistance2(w1, w2));
  }
}
