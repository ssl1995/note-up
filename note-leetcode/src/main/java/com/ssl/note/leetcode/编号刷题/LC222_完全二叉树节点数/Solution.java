package com.ssl.note.leetcode.编号刷题.LC222_完全二叉树节点数;

import com.ssl.note.common.utils.TreeNode;

public class Solution {

  /**
   * LC222_完全二叉树节点数
   * 学习：满二叉树节点数：2^高度-1
   */
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int treeH = mostLeftCount(root, 1);
    return dfs(root, 1, treeH);
  }

  private int dfs(TreeNode cur, int level, int h) {
    // Base:当子树层数到达整棵树高度，该子树节点数=1
    if (level == h) {
      return 1;
    }
    // 1、当前节点的右子树，扎到了整棵树的高度，当前节点的左子树是满二叉树
    if (mostLeftCount(cur.right, level + 1) == h) {
      // 左孩子是满二叉树：(1 << (h - level)) - 1
      // 当前节点数：1
      // 递归右孩子数：dfs(cur.right, level + 1, h)
      return (1 << (h - level)) - 1 + 1 + dfs(cur.right, level + 1, h);
    }
    // 2、当前节点的右子树，没扎到了整棵树的高度，当前节点的右子树是满二叉树
    // 右孩子是满二叉树：(1 << (h - level - 1)) - 1
    // 当前节点数：1
    // 递归右孩子数：dfs(cur.left, level + 1, h)
    return (1 << (h - level - 1)) - 1 + 1 + dfs(cur.left, level + 1, h);
  }

  // 题目要求时间复杂度：<O(n)，所以递归求左孩子能有多深
  // 也可以求树高度
  private int mostLeftCount(TreeNode node, int level) {
    while (node != null) {
      node = node.left;
      level++;
    }
    return level - 1;
  }

  // 错误写法：时间复杂度O(n),本题会超时
  public int countNodes_time_old(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = countNodes(root.left);
    int right = countNodes(root.right);
    return left + right + 1;
  }
}
