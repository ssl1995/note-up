package com.ssl.note.leetcode.编号刷题.LC32_最长有效括号;

/**
 * @author SongShengLin
 * @date 2022/1/22 11:10 PM
 * @description
 */
public class Solution1 {

  /**
   * 最长有效括号
   * 输入：s = ")()())"
   * 输出：4
   * 解释：最长有效括号子串是 "()()"
   * 方法二：动态规划
   * dp[i] 表示以 s[i] 结尾的最长有效括号子串长度
   * 时间复杂度：O(n)，空间复杂度：O(n)
   */
  public int longestValidParentheses(String s) {
    if (s == null || s.length() < 2) {
      return 0;
    }

    int n = s.length();
    // dp[i] = 以 s[i] 结尾的最长有效括号子串长度
    int[] dp = new int[n];
    int max = 0;

    for (int i = 1; i < n; i++) {
      // 右括号结尾才有可能有效
      if (s.charAt(i) == ')') {
        // 前一个是左括号
        // 情况1：...()
        if (s.charAt(i - 1) == '(') {
          dp[i] = (i < 2 ? 0 : dp[i - 2]) + 2;
        } else {
          // 前一个是右括号
          // 情况2：...(...)) ，即 s[i-1] 也是 ')'，中间隔着一段已配对的有效块
          boolean preIsRight = i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(';
          if (preIsRight) {
            // ((...))，前面没有
            dp[i] = dp[i - 1] + 2;
            // ..((...))，前面还有..，需要加上
            if (i - dp[i - 1] - 2 >= 0) {
              dp[i] += dp[i - dp[i - 1] - 2];
            }
          }
        }
        max = Math.max(max, dp[i]);
      }
    }

    return max;
  }
}
