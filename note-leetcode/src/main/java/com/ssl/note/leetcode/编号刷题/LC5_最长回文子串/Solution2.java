package com.ssl.note.leetcode.编号刷题.LC5_最长回文子串;


public class Solution2 {
    /**
     * 最长回文子串
     * 动态规划法:面试选这个解法
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     */
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int n = s.length();
        // dp[j][i]：子串s[j..i]（从第j个字符到第i个字符）是否为回文串
        boolean[][] dp = new boolean[n][n];
        // 初始化：单个字符一定是回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        // 单个字符也是回文串，初始化长度=1
        int maxLen = 1;
        int begin = 0;
        for (int i = 0; i < n; i++) {// i是右边结束位置
            for (int j = 0; j < i; j++) {// j是左边起始位置
                if (s.charAt(i) == s.charAt(j)) {
                    // 左右相等时，子串长度<=3（也是边界保护），必须是回文串：end - start + 1 <= 3
                    // 否则就判断中间子串是否是回文串：dp[start + 1][end - 1]
                    dp[j][i] = i - j + 1 <= 3 || dp[j + 1][i - 1];
                } else {
                    dp[j][i] = false;
                }
                // 记录最长子串的长度和起始位置
                if (dp[j][i] && i - j + 1 > maxLen) {
                    begin = j;// 开始位置
                    maxLen = i - j + 1;// 长度
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        Solution2 solution3 = new Solution2();
        String s = "babad";
        System.out.println(solution3.longestPalindrome(s));
    }

}
