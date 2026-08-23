package com.ssl.note.leetcode.编号刷题.LC208_实现前缀树.array;

import java.util.Arrays;

public class Trie {

  // 前缀树：静态数组实现方式,笔试和面试推荐，工程不推荐
  // 核心思想：用整数编号代替对象引用，tree[cur][path]存储子节点编号
  private final int MAX = 150001;// 最大长度尝试出来
  // tree[cur][path]: 节点cur的第path个子节点编号，0表示空
  private final int[][] tree = new int[MAX][26];
  // pass[cur]: 经过节点cur的字符串数量（用于startsWith）
  private final int[] pass = new int[MAX];
  // end[cur]: 以节点cur结尾的字符串数量（用于search）
  private final int[] end = new int[MAX];
  // 闭区间，当前申请到了哪个位置，0号弃用作为空标识
  private int cnt;

  public Trie() {
    cnt = 1;
  }

  public void insert(String word) {
    if (word == null) {
      return;
    }
    int cur = 1;// 从根节点1开始
    pass[cur]++;// 经过根节点

    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';// 计算字符路径0-25
      if (tree[cur][path] == 0) {// 子节点不存在，创建新节点
        tree[cur][path] = ++cnt;
      }
      cur = tree[cur][path];// 移动到子节点
      pass[cur]++;// 经过该节点
    }

    end[cur]++;// 标记单词结尾
  }

  public boolean search(String word) {
    if (word == null) {
      return false;
    }
    int cur = 1;
    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';
      if (tree[cur][path] == 0) {// 路径不存在，单词不存在
        return false;
      }
      cur = tree[cur][path];
    }

    return end[cur] > 0;// 必须是以该节点结尾的完整单词
  }

  public boolean startsWith(String prefix) {
    if (prefix == null) {
      return false;
    }
    int cur = 1;
    for (int i = 0, path; i < prefix.length(); i++) {
      path = prefix.charAt(i) - 'a';
      if (tree[cur][path] == 0) {// 路径不存在，前缀不存在
        return false;
      }
      cur = tree[cur][path];
    }

    return pass[cur] > 0;// 只要有字符串经过该节点即可
  }

  public void delete(String word) {
    if (word == null) {
      return;
    }
    if (!search(word)) {// 单词不存在，直接返回
      return;
    }
    int cur = 1;
    pass[cur]--;// 经过根节点次数减1
    for (int i = 0, path; i < word.length(); i++) {
      path = word.charAt(i) - 'a';
      if (--pass[tree[cur][path]] == 0) {// 子节点pass减为0，断开链接
        tree[cur][path] = 0;// 后续节点自动失效，无需逐个清理
        return;
      }
      cur = tree[cur][path];
    }
    end[cur]--;// 结尾标记减1
  }

  public void clear() {
    for (int i = 1; i <= cnt; i++) {
      Arrays.fill(tree[i], 0);
      end[i] = 0;
      pass[i] = 0;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== Trie State (cnt=").append(cnt).append(") ===\n");
    sb.append("Node:  ");
    for (int i = 1; i <= cnt; i++) {
      sb.append(String.format("%4d", i));
    }
    sb.append("\npass:  ");
    for (int i = 1; i <= cnt; i++) {
      sb.append(String.format("%4d", pass[i]));
    }
    sb.append("\nend:   ");
    for (int i = 1; i <= cnt; i++) {
      sb.append(String.format("%4d", end[i]));
    }
    sb.append("\n\ntree(non-zero edges):\n");
    for (int i = 1; i <= cnt; i++) {
      for (int j = 0; j < 26; j++) {
        if (tree[i][j] != 0) {
          sb.append(String.format("  tree[%d][%d]=%d  (Node%d --%c--> Node%d)%n", 
              i, j, tree[i][j], i, (char)('a'+j), tree[i][j]));
        }
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Trie trie = new Trie();
    trie.insert("abcd");
    trie.insert("abc");
    trie.insert("abd");
    System.out.println(trie);
  }
}
