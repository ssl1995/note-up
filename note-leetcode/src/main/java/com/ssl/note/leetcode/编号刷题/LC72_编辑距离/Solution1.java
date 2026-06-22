package com.ssl.note.leetcode.编号刷题.LC72_编辑距离;

/**
 * @author SongShengLin
 * @date 2022/1/27 9:11 AM
 * @description
 */
public class Solution1 {

  /**
   * 编辑距离 - 一维数组空间优化版本
   * 返回将 word1 转换成 word2 所使用的最少操作数
   *
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
    // 技巧：让 word1 是较长的字符串，word2 是较短的字符串
    // 这样 dp 数组长度取 min(m, n)，空间更优
    if (word1.length() < word2.length()) {
      return minDistance(word2, word1);
    }

    int m = word1.length();  // 较长字符串
    int n = word2.length();  // 较短字符串

    // dp[j]：word1[0..i-1] 和 word2[0..j-1] 在当前行 i 下的最少编辑距离
    int[] dp = new int[n + 1];
    // 初始化 dp 数组：当 word1 为空串时
    for (int j = 1; j <= n; j++) {
      dp[j] = j;
    }

    // 遍历 word1 的每个字符，逐行更新 dp 数组
    for (int i = 1; i <= m; i++) {
      // prev=dp[i-1][j-1]，也就是"左上角"的值
      int prev = dp[0];
      // 特殊处理：更新 dp[0]：当 word2 为空串时
      // 需要把 word1[0..i-1] 全部删除，操作数就是 i
      dp[0] = i;

      for (int j = 1; j <= n; j++) {
        // temp=dp[i-1][j]=二维表中的"上方"
        int temp = dp[j];

        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          // 当前字符相等：不需要任何操作
          // 直接继承左上角 dp[i-1][j-1] 的值，也就是 prev
          dp[j] = prev;
        } else {
          // 当前字符不相等：三种操作取最小值 + 1
          // dp[j]      = 二维表中的 dp[i-1][j]，表示"删除"操作
          // dp[j - 1]  = 二维表中的 dp[i][j-1]，表示"插入"操作
          // prev       = 二维表中的 dp[i-1][j-1]，表示"替换"操作
          dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), prev) + 1;
        }

        // 更新 prev：当前 temp 是 dp[i-1][j]
        // 对于下一列 j+1 来说，它就是左上角 dp[i-1][j]
        prev = temp;
      }
    }

    // dp[n] 最终表示 word1[0..m-1] 和 word2[0..n-1] 的最少编辑距离
    return dp[n];
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    String w1 = "intention";
    String w2 = "execution";
    System.out.println(solution1.minDistance(w1, w2));
  }
}
