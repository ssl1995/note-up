package com.ssl.note.leetcode.编号刷题.LC236_二叉树最近公共祖先;


import com.ssl.note.common.utils.TreeNode;

/**
 * @author SongShengLin
 * @date 2022/6/19 10:43
 * @description
 */
public class Solution {

  /**
   * 二叉树最近公共祖先
   */
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // Base case：就是root：root空或者就是p、q
    if (root == null || root == p || root == q) {
      return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    // 找异测：左右子树都不为空，说明p、q都在异侧，root就是最近公共祖先
    if (left != null && right != null) {
      return root;
    }
    // 找同侧，走不为空的那个，如果都为空，也适用
    return left != null ? left : right;
  }
}
