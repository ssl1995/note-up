package com.ssl.note.leetcode.编号刷题.LC140_单词拆分II;

import java.util.*;

public class Test {

  public List<String> wordBreak(String s, List<String> wordDict) {
    if (s == null || s.length() == 0) {
      return new ArrayList<>();
    }
    Set<String> set = new HashSet<>(wordDict);
    Map<Integer, List<String>> mermory = new HashMap<>();
    return dfs(s, 0, set, mermory);
  }

  private List<String> dfs(String s, int start, Set<String> wordDict, Map<Integer, List<String>> mermory) {
    if (mermory.containsKey(start)) {
      return mermory.get(start);
    }
    List<String> res = new ArrayList<>();

    if (start == s.length()) {
      res.add("");
      return res;
    }

    for (int j = start + 1; j <= s.length(); j++) {
      String word = s.substring(start, j);
      if (wordDict.contains(word)) {
        List<String> post = dfs(s, j, wordDict, mermory);
        for (String postWord : post) {
          res.add(Objects.equals(postWord, "") ? word : word + " " + postWord);
        }
      }
    }

    mermory.put(start, res);

    return res;
  }
}
