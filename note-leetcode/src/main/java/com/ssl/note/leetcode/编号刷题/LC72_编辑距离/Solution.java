package com.ssl.note.leetcode.编号刷题.LC72_编辑距离;

public class Solution {

  /**
   * 编辑距离
   * 返回将word1转换成word2所使用的最少操作数
   * 输入：word1 = "horse", word2 = "ros"
   * 输出：3
   * 解释：
   * horse -> rorse (将 'h' 替换为 'r')
   * rorse -> rose (删除 'r')
   * rose -> ros (删除 'e')
   */
  public int minDistance1(String word1, String word2) {
    // minDistance1是m*n空间的方法，初始化0行0列太麻烦了，理解用
    // minDistance2面试用
    int m = word1.length();
    int n = word2.length();
    if (m == 0) {
      return n;
    }
    if (n == 0) {
      return m;
    }
    // dp[i][j]表示word1[0..i]转换成word2[0..j]所需的最少操作数
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

        // 最后一次操作必为增/删/改之一，取三种情况最小值：
        // 删除（上边）：word1多一个字符，删掉word1[i]，剩下word1[0..i-1]->word2[0..j]
        int delete = dp[i - 1][j] + 1;
        // 新增（左边）：word2多一个字符，往word1末尾插入word2[j]，剩下word1[0..i]->word2[0..j-1]
        int insert = dp[i][j - 1] + 1;
        // 替换（左上）：word1[i]与word2[j]配对，把word1[i]改成word2[j]，剩下word1[0..i-1]->word2[0..j-1]
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

    // 本题即使是m+1和n+1的初始化，也需要处理首行首列
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
          // dp沿用上一个数据
          dp[i][j] = dp[i - 1][j - 1];
          continue;
        }
        // 字母不相同：才有有需要操作

        // 最后一次操作必为增/删/改之一，取三种情况最小值：
        // 删除（上边）：word1多一个字符，删掉word1[i-1]，剩下word1前i-1个->word2前j个
        int delete = dp[i - 1][j];
        // 新增（左边）：word2多一个字符，往word1末尾插入word2[j-1]，剩下word1前i个->word2前j-1个
        int insert = dp[i][j - 1];
        // 替换（左上）：word1[i-1]与word2[j-1]配对，把word1[i-1]改成word2[j-1]，剩下word1前i-1个->word2前j-1个
        int update = dp[i - 1][j - 1];

        dp[i][j] = Math.min(Math.min(delete, insert), update) + 1;
      }
    }

    return dp[m][n];
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    String w1 = "horse";
    String w2 = "ros";
    System.out.println(solution.minDistance1(w1, w2));
    System.out.println(solution.minDistance2(w1, w2));
  }
}
