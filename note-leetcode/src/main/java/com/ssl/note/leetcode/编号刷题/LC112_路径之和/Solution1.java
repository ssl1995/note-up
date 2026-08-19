package com.ssl.note.leetcode.编号刷题.LC112_路径之和;

import com.ssl.note.common.utils.TreeNode;

/**
 * @author SongShengLin
 * @date 2022/1/10 11:25 PM
 * @description
 */
public class Solution1 {

  private boolean find;

  public boolean hasPathSum(TreeNode root, int targetSum) {
    find = false;
    dfs(root, targetSum);
    return find;
  }

  private void dfs(TreeNode node, int t) {
    if (node == null) {
      return;
    }
    t -= node.val;
    if (node.left == null && node.right == null && t == 0) {
      find = true;
      return;
    }
    dfs(node.left, t);
    dfs(node.right, t);
  }

}
