package com.ssl.note.leetcode.编号刷题.LC110_平衡二叉树;

import com.ssl.note.common.utils.TreeNode;

public class Solution {

  /**
   * LC110_平衡二叉树
   * 判断：左右子树高度差<=1
   */
  private boolean isBalanced;

  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    isBalanced = true;
    height(root);

    return isBalanced;
  }

  // 即计算高度，也判断是否高度差>1
  private int height(TreeNode node) {
    // 如果已经判断不平衡，返回什么都不重要了，外层只需要isBalanced
    if (!isBalanced || node == null) {
      return 0;
    }

    int leftH = height(node.left);
    int rightH = height(node.right);

    if (Math.abs(leftH - rightH) > 1) {
      isBalanced = false;
    }

    return Math.max(leftH, rightH) + 1;
  }
}
