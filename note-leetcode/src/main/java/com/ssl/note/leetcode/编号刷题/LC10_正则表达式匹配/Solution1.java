package com.ssl.note.leetcode.编号刷题.LC10_正则表达式匹配;

/**
 * LC10 正则表达式匹配 —— 空间压缩版（一维 DP）
 *
 * 核心思路：
 * dp[i][j] 只依赖上一行和当前行左边的状态，因此可以把二维数组压缩成一维。
 * 用 dp[j] 表示当前行 i 的状态，prev 保存上一行 dp[i-1][j-1] 的值。
 *
 * 状态转移：
 * 1. p[j-1] == '*' 时：
 *    - * 匹配 0 次：dp[j-2]（当前行已更新）
 *    - * 匹配 1+ 次：dp[j]（上一行） && (s[i-1] 与 p[j-2] 匹配)
 * 2. p[j-1] != '*' 时：
 *    - dp[j] = prev（上一行 dp[i-1][j-1]） && (s[i-1] == p[j-1] || p[j-1] == '.')
 */
public class Solution1 {

  /**
   * 正则表达式匹配
   * 动态规划空间优化版
   */
  public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[j] 表示：s 的前 i 个字符 与 p 的前 j 个字符是否匹配
        boolean[] dp = new boolean[n + 1];
        // 初始化：空串匹配空串
        dp[0] = true;

        // 初始化首行：s 为空串时，p 形如 a*b*c* 才能匹配
        for (int j = 2; j <= n; j += 2) {
            dp[j] = dp[j - 2] && p.charAt(j - 1) == '*';
        }

        // 逐行填表
        for (int i = 1; i <= m; i++) {
            // prev 表示上一行的 dp[i-1][j-1]
            boolean prev = dp[0];
            // 当前行 s 非空、p 为空，一定不匹配
            dp[0] = false;

            for (int j = 1; j <= n; j++) {
                // temp 表示上一行的 dp[i-1][j]，待会儿要赋给 prev
                boolean temp = dp[j];

                if (p.charAt(j - 1) == '*') {
                    // * 匹配 0 次：看 dp[j-2]（当前行已更新）
                    // * 匹配 1+ 次：看 dp[j]（上一行）且当前字符匹配
                    dp[j] = dp[j - 2]
                            || (dp[j]
                            && (s.charAt(i - 1) == p.charAt(j - 2)
                            || p.charAt(j - 2) == '.'));
                } else {
                    // 普通字符或 '.'：看左上角 prev（上一行 dp[i-1][j-1]）
                    dp[j] = prev
                            && (s.charAt(i - 1) == p.charAt(j - 1)
                            || p.charAt(j - 1) == '.');
                }

                // prev 滚动到下一列，代表下一轮的 dp[i-1][j-1]
                prev = temp;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();

        System.out.println(solution.isMatch("aa", "a"));       // false
        System.out.println(solution.isMatch("aa", "a*"));      // true
        System.out.println(solution.isMatch("ab", ".*"));      // true
        System.out.println(solution.isMatch("aaa", "ab*.*"));  // true
        System.out.println(solution.isMatch("aab", "c*a*b"));  // true
        System.out.println(solution.isMatch("mississippi", "mis*is*p*.")); // false
    }
}
