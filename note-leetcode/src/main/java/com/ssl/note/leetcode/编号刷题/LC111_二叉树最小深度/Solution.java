package com.ssl.note.leetcode.编号刷题.LC111_二叉树最小深度;

import com.ssl.note.common.utils.TreeNode;

public class Solution {

  /**
   * LC111_二叉树最小深度
   * 根节点到叶子节点的最小深度
   */
  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if (root.left == null && root.right == null) {
      return 1;
    }
    int leftMinDepth = Integer.MAX_VALUE;
    int rightMinDepth = Integer.MAX_VALUE;
    // 以下不能直接调用，因为Math.min会被null=0干扰
    // 总结：null如果默认值会让"空"产生贡献，就必须把它排除掉
//    leftMinDepth = minDepth(root.left);
//    rightMinDepth = minDepth(root.right);
    if (root.left != null) {
      leftMinDepth = minDepth(root.left);
    }
    if (root.right != null) {
      rightMinDepth = minDepth(root.right);
    }
    return Math.min(leftMinDepth, rightMinDepth) + 1;
  }
}
