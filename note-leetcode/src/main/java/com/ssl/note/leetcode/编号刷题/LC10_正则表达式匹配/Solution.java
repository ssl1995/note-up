package com.ssl.note.leetcode.编号刷题.LC10_正则表达式匹配;

public class Solution {

  /**
   * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
   * '.' 匹配任意单个字符
   * '*' 匹配零个或多个前面的那一个元素
   * 返回一个布尔值，表示匹配是否覆盖整个输入字符串（而非部分）。
   * 示例 1：
   * 输入：s = "aa", p = "a"
   * 输出：false
   * 解释："a" 无法匹配 "aa" 整个字符串。
   * 示例 2:
   * 输入：s = "aa", p = "a*"
   * 输出：true
   * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
   * 示例 3：
   * 输入：s = "ab", p = ".*"
   * 输出：true
   * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
   * 提示：
   * 1 <= s.length <= 20
   * 1 <= p.length <= 20
   * s 只包含从 a-z 的小写字母。
   * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
   * 保证每次出现字符 * 时，前面都匹配到有效的字符
   */
  public boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();

    // dp[i][j]表示s的前i个字符和p的前j个字符是否匹配
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;// 空串彼此匹配

    // 初始化状态:题目规定了s是原始串，p是含有正则的匹配串
    for (int j = 2; j < n + 1; j += 2) {
      // s:空串
      // p=a*b*c*这种形式
      dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
    }

    // 动态转移
    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (p.charAt(j - 1) != '*') {
          // 情况1:p[j-1]不是*
          dp[i][j] = dp[i - 1][j - 1] // 前面都匹配
              && // 并且(当前位置能匹配 或 p当前是.)
              (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
        } else {
          // 情况2:p[j-1]是*
          // *匹配0次：删除p的最近2个字符，比如s=a,p=ab*
          if (dp[i][j - 2]) {
            dp[i][j] = true;
          } else {
            // *匹配1次或多次:s[0..i-2] 已经和 p[0..j-1] 匹配
            dp[i][j] = dp[i - 1][j]
                &&// 并且s[i-1] 和 p[j-2] 匹配（或者 p[j-2] 是 .）
                (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.');
          }
        }
      }
    }

    return dp[m][n];
  }

  public static void main(String[] args) {
    String s = "aaa";
    String p = "ab*.*";
    Solution solution = new Solution();
        /*
         true	false	false	false	false	false
         false	true	false	true	false	true
         false	false	false	false	true	true
         false	false	false	false	false	true
         */
    System.out.println(solution.isMatch(s, p));
  }
}


