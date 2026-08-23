package com.ssl.note.leetcode.编号刷题.LC208_实现前缀树;

public class Trie {

  // 前缀树：类结构实现，工程代码用类信息
  static class TrieNode {
    public int pass;// 经过
    public int end;// 终止
    public TrieNode[] nexts;

    public TrieNode() {
      pass = 0;
      end = 0;
      nexts = new TrieNode[26];
    }
  }

  // 前缀树的根节点
  private final TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  // 插入一个单词
  public void insert(String word) {
    TrieNode cur = root;
    cur.pass++;
    for (int i = 0, path; i < word.length(); i++) { // 从左往右遍历字符
      path = word.charAt(i) - 'a'; // 由字符，对应成走向哪条路
      if (cur.nexts[path] == null) {
        cur.nexts[path] = new TrieNode();
      }
      cur = cur.nexts[path];
      cur.pass++;
    }
    cur.end++;
  }

  // 查询前缀树里，word单词存在不
  public boolean search(String word) {
    TrieNode cur = root;
    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';
      if (cur.nexts[path] == null) {
        return false;
      }
      cur = cur.nexts[path];
    }
    return cur.end > 0;
  }

  // 查询前缀树里，有多少单词以pre做前缀
  public boolean startsWith(String pre) {
    TrieNode cur = root;
    for (int i = 0, path; i < pre.length(); i++) {
      path = pre.charAt(i) - 'a';
      if (cur.nexts[path] == null) {
        return false;
      }
      cur = cur.nexts[path];
    }
    return cur.pass > 0;
  }

}
