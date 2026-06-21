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
        if (s.charAt(i - 1) == '(') {
          // 情况1：...()
          dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
        } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
          // 情况2：...((...))
          // 索引:  ...  k   k+1  ...  i-1   i
          // 字符:  ...  (   xxxx       )    )
          //                ↑__________↑
          //                  dp[i-1]
          // 当前 ) 与 dp[i-1] 前面那个位置的 ( 配对，形成新的有效括号，
          // 新匹配的：dp[i - 1] + 2
          // k前面匹配的括号：k+1位置=i-dp[i-1],k-1=i-dp[i-1]-2,此时k前面可能还有已经配对的括号
          dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
        }
        max = Math.max(max, dp[i]);
      }
    }

    return max;
  }
}
