package com.ssl.note.practice.lc_top_100;

import java.util.ArrayList;
import java.util.List;

public class Practice {

  private int fresh;

  public List<List<String>> partition(String s) {
    if (s == null) {
      return new ArrayList<>();
    }
    List<String> path = new ArrayList<>();
    List<List<String>> res = new ArrayList<>();
    dfs(s, 0, path, res);

    return res;
  }

  private void dfs(String s, int i, List<String> path, List<List<String>> res) {
    if (i == s.length()) {
      res.add(new ArrayList<>(path));
      return;
    }

    for (int j = i; j < s.length(); j++) {
      if (!check(s, i, j)) {
        continue;
      }
      path.add(s.substring(i, j + 1));
      dfs(s, j + 1, path, res);
      path.remove(path.size() - 1);
    }
  }

  private boolean check(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "aab";
    System.out.println(practice.partition(s));
  }

}
