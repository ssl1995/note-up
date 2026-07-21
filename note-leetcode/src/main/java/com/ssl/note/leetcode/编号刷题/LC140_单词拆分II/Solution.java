package com.ssl.note.leetcode.编号刷题.LC140_单词拆分II;

import java.util.*;

public class Solution {

  /**
   * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。以任意顺序 返回所有这些可能的句子。
   * 注意：词典中的同一个单词可能在分段中被重复使用多次。
   * 示例 1：
   * 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
   * 输出:["cats and dog","cat sand dog"]
   * 示例 2：
   * 输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
   * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
   * 解释: 注意你可以重复使用字典中的单词。
   */
  public List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> wordSet = new HashSet<>(wordDict);
    // 记忆化搜索优化：memo[start] 表示：从 s[start..末尾] 能拆出的所有句子
    Map<Integer, List<String>> memory = new HashMap<>();
    return dfs(s, 0, wordSet, memory);
  }

  private List<String> dfs(String s, int start, Set<String> wordSet, Map<Integer, List<String>> memory) {
    // 1.记忆化搜素优化，已经算过，直接返回
    if (memory.containsKey(start)) {
      return memory.get(start);
    }

    List<String> res = new ArrayList<>();

    // 2.递归边界：走到末尾，返回[""]，让上一层拼出最后一个单词（且不会多出空格）
    if (start == s.length()) {
      res.add("");
      return res;
    }

    // 3.枚举切分点end，尝试把s[start..end-1]当作一个单词,递归[end,n]
    for (int end = start + 1; end <= s.length(); end++) {
      String word = s.substring(start, end);
      if (wordSet.contains(word)) {
        // 4. 递归获取后续部分的所有拆分方案
        List<String> subSentences = dfs(s, end, wordSet, memory);
        for (String sub : subSentences) {
          // 5. 把当前单词和后续句子拼起来
          if (sub.isEmpty()) {
            res.add(word);
          } else {
            res.add(word + " " + sub);
          }
        }
      }
    }

    memory.put(start, res);
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    Test test = new Test();

    String s1 = "catsanddog";
    List<String> dict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");
    System.out.println(solution.wordBreak(s1, dict1));
    System.out.println(test.wordBreak(s1, dict1));
  }
}
