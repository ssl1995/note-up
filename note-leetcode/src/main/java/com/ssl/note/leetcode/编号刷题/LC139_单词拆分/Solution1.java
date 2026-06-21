package com.ssl.note.leetcode.编号刷题.LC139_单词拆分;

import java.util.*;

public class Solution1 {

  /**
   * 单词拆分
   * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
   * 输出: true
   * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
   * 注意，你可以重复使用字典中的单词。
   * 1 <= s.length <= 300
   * 1 <= wordDict.length <= 1000
   * 1 <= wordDict[i].length <= 20
   * s 和 wordDict[i] 仅由小写英文字母组成
   * wordDict 中的所有字符串 互不相同
   */
  public boolean wordBreak(String s, List<String> wordDict) {
    int n = s.length();
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;

    // 按单词长度分组：{长度 -> 该长度下的所有单词集合}
    Map<Integer, Set<String>> lenMap = new HashMap<>();
    int maxLen = 0;
    for (String word : wordDict) {
      int len = word.length();
      lenMap.computeIfAbsent(len, k -> new HashSet<>()).add(word);
      maxLen = Math.max(maxLen, len);
    }

    for (int i = 1; i <= n; i++) {
      // 只尝试长度合法且存在的单词长度
      for (int len = 1; len <= Math.min(i, maxLen); len++) {
        Set<String> words = lenMap.get(len);
        if (words == null) {
          continue;
        }
        // 前 i-len 个字符能拆，且 s[i-len..i-1] 在字典中
        if (dp[i - len] && words.contains(s.substring(i - len, i))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[n];
  }


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "leetcode";
    List<String> wordDict = new ArrayList<>();
    wordDict.add("leet");
    wordDict.add("code");

    System.out.println("优化版本: " + solution.wordBreak(s, wordDict));

    // 额外测试一个 false 用例
    String s2 = "leetcodex";
    System.out.println("优化版本: " + solution.wordBreak(s2, wordDict));
  }
}
