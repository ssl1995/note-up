package com.ssl.note.leetcode.编号刷题.LC124_二叉树中的最大路径和;

import com.ssl.note.common.utils.TreeNode;

public class Test {

  private int res = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    if (root == null) {
      return 0;
    }
    dfs(root);
    return res;
  }

  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int left = Math.max(dfs(node.left), 0);
    int right = Math.max(dfs(node.right), 0);

    res = Math.max(res, left + right + node.val);

    return Math.max(left, right) + node.val;
  }
}

