package com.ssl.note.leetcode.编号刷题.LC1804_实现前缀树II;

public class Trie {

  // 类结构
  class TrieNode {
    public int pass;
    public int end;
    public TrieNode[] nexts;

    public TrieNode() {
      pass = 0;
      end = 0;
      nexts = new TrieNode[26];
    }
  }

  // 根节点
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode node = root;
    node.pass++;
    for (int i = 0, path; i < word.length(); i++) { // 从左往右遍历字符
      path = word.charAt(i) - 'a'; // 由字符，对应成走向哪条路
      if (node.nexts[path] == null) {
        node.nexts[path] = new TrieNode();
      }
      node = node.nexts[path];
      node.pass++;
    }
    node.end++;
  }

  // 查询前缀树里，word单词出现了几次
  public int countWordsEqualTo(String word) {
    TrieNode node = root;
    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';
      if (node.nexts[path] == null) {
        return 0;
      }
      node = node.nexts[path];
    }
    return node.end;
  }

  // 查询前缀树里，有多少单词以pre做前缀
  public int countWordsStartingWith(String pre) {
    TrieNode node = root;
    for (int i = 0, path; i < pre.length(); i++) {
      path = pre.charAt(i) - 'a';
      if (node.nexts[path] == null) {
        return 0;
      }
      node = node.nexts[path];
    }
    return node.pass;
  }

  // 如果之前word插入过前缀树，那么此时删掉一次
  // 如果之前word没有插入过前缀树，那么什么也不做
  public void erase(String word) {
    if (countWordsEqualTo(word) > 0) {
      TrieNode node = root;
      node.pass--;
      for (int i = 0, path; i < word.length(); i++) {
        path = word.charAt(i) - 'a';
        if (--node.nexts[path].pass == 0) {
          node.nexts[path] = null;
          return;
        }
        node = node.nexts[path];
      }
      node.end--;
    }
  }
}
