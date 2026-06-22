package com.ssl.note.leetcode.编号刷题.LC1143_最长公共子序列;

public class Solution1 {

  /**
   * 最长公共子序列 - 更简洁的二维DP写法
   * 输入：text1 = "abcde", text2 = "ade" 输出：3
   * 解释：最长公共子序列是 "ade"，它的长度为 3。
   *
   * 思考过程：
   * 1. 为什么用二维DP？
   *    题目涉及两个字符串 text1 和 text2，要求它们的“最长公共子序列”。
   *    这种“两个字符串逐一匹配”的问题，天然适合用二维DP：
   *    行代表 text1 的前缀，列代表 text2 的前缀。
   *
   * 2. 状态定义
   *    dp[i][j] 表示：text1[0..i-1] 和 text2[0..j-1] 的最长公共子序列长度。
   *    注意：这里 i 和 j 从 1 开始计数，dp 数组大小为 (m+1) × (n+1)，
   *    第 0 行和第 0 列表示空字符串，作为天然的边界条件，值为 0。
   *
   * 3. 状态转移
   *    比较 text1[i-1] 和 text2[j-1]（当前两个字符）：
   *    - 如果相等：这个字符可以被选入公共子序列，
   *      所以 dp[i][j] = dp[i-1][j-1] + 1
   *    - 如果不相等：说明当前两个字符不能同时出现在公共子序列里，
   *      那么要么舍弃 text1[i-1]（看 dp[i-1][j]），
   *      要么舍弃 text2[j-1]（看 dp[i][j-1]），取较大值。
   *      所以 dp[i][j] = max(dp[i-1][j], dp[i][j-1])
   *
   * 4. 为什么这种写法更简洁？
   *    用 (m+1) × (n+1) 的数组，把空字符串也包含进来，
   *    这样 dp[0][j] 和 dp[i][0] 天然等于 0，不需要像 Solution.java 那样单独初始化第一行和第一列。
   *
   * 5. 遍历顺序
   *    按行从上到下，每行从左到右填表。
   *    因为 dp[i][j] 依赖上方、左方、左上方，这三个方向都已经计算过了。
   *
   * 6. 答案
   *    右下角 dp[m][n] 就是两个完整字符串的最长公共子序列长度。
   */
  public int longestCommonSubsequence(String text1, String text2) {
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
    Solution1 solution2 = new Solution1();
    String s1 = "abcde";
    String s2 = "ade";
    System.out.println(solution2.longestCommonSubsequence(s1, s2));  // 输出 3
  }
}
