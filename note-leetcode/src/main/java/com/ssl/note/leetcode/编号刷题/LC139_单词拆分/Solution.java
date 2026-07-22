package com.ssl.note.leetcode.编号刷题.LC139_单词拆分;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author SongShengLin
 * @date 2022/2/21 11:11 PM
 * @description
 */
public class Solution {
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
  public boolean wordBreak1(String s, List<String> wordDict) {
    Set<String> set = new HashSet<>(wordDict);
    int n = s.length();
    // dp[i]：s[0..i]（以下标i结尾的前缀）是否能被拆分
    boolean[] dp = new boolean[n];

    for (int i = 0; i < n; i++) {
      // 1、[0..i]本身就是一个单词
      if (set.contains(s.substring(0, i + 1))) {
        dp[i] = true;
      } else {
        // 2、找切割点j：s[0..j]可拆分 且 s[j+1..i]在字典中
        for (int j = 0; j < i; j++) {
          if (dp[j] && set.contains(s.substring(j + 1, i + 1))) {
            dp[i] = true;
            break;
          }
        }
      }
    }

    return dp[n - 1];
  }

  // 数组n推导出n+1版本，时间复杂度虽然不变，但是耗时会降低
  public boolean wordBreak2(String s, List<String> wordDict) {
    Set<String> set = new HashSet<>(wordDict);
    int n = s.length();
    // dp[i]：s的前i个字符是否能被拆分
    boolean[] dp = new boolean[n + 1];
    dp[0] = true;

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        // 前i个字符串是否包含在字典中，一旦有一个满足条件就要跳出内层循环
        // 如果不跳过，后续的字符串可能会覆盖前面的结果
        if (dp[j] && set.contains(s.substring(j, i))) {
          dp[i] = true;
          break;
        }
      }
    }

    return dp[n];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "leetcode";
    List<String> wordDict = new ArrayList<>();
    wordDict.add("leet");
    wordDict.add("code");

    System.out.println("原始版本: " + solution.wordBreak1(s, wordDict));

    // 额外测试一个 false 用例
    String s2 = "leetcodex";
    System.out.println("原始版本(false): " + solution.wordBreak2(s2, wordDict));
  }
}
