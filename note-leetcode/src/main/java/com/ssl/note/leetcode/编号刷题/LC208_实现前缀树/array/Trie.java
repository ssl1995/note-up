package com.ssl.note.leetcode.编号刷题.LC208_实现前缀树.array;

import java.util.Arrays;

public class Trie {

  // 前缀树：静态数组实现方式,笔试和面试推荐，工程不推荐
  private final int MAX = 150001;// 最大长度尝试出来

  private final int[][] tree = new int[MAX][26];
  private final int[] pass = new int[MAX];
  private final int[] end = new int[MAX];
  private int cnt;// 闭区间，当前申请到了哪个位置

  public Trie() {
    cnt = 1;
  }

  public void insert(String word) {
    if (word == null) {
      return;
    }
    int cur = 1;
    pass[cur]++;

    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';
      if (tree[cur][path] == 0) {
        tree[cur][path] = ++cnt;
      }
      cur = tree[cur][path];
      pass[cur]++;
    }

    end[cur]++;
  }

  public boolean search(String word) {
    if (word == null) {
      return false;
    }
    int cur = 1;
    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';
      if (tree[cur][path] == 0) {
        return false;
      }
      cur = tree[cur][path];
    }

    return end[cur] > 0;
  }

  public boolean startsWith(String prefix) {
    if (prefix == null) {
      return false;
    }
    int cur = 1;
    for (int i = 0, path; i < prefix.length(); i++) {
      path = prefix.charAt(i) - 'a';
      if (tree[cur][path] == 0) {
        return false;
      }
      cur = tree[cur][path];
    }

    return pass[cur] > 0;
  }

  public void delete(String word) {
    if (word == null) {
      return;
    }
    if (!search(word)) {
      return;
    }
    int cur = 1;
    pass[cur]--;
    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';
      if (--pass[tree[cur][path]] == 0) {
        tree[cur][path] = 0;
        return;
      }
      cur = tree[cur][path];
    }
    end[cur]--;
  }

  public void clear() {
    for (int i = 1; i <= cnt; i++) {
      Arrays.fill(tree[i], 0);
      end[i] = 0;
      pass[i] = 0;
    }
  }
}
