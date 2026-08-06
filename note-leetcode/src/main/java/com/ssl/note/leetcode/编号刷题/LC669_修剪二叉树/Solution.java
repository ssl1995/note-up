package com.ssl.note.leetcode.编号刷题.LC669_修剪二叉树;

import com.ssl.note.common.utils.TreeNode;

public class Solution {

  /**
   * LC669_修剪二叉树
   * 给你二叉搜索树的根节点 root ，
   * 同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中
   */
  public TreeNode trimBST(TreeNode root, int low, int high) {
    if (root == null) {
      return null;
    }
    if (root.val < low) {
      return trimBST(root.right, low, high);
    } else if (root.val > high) {
      return trimBST(root.left, low, high);
    }
    root.left = trimBST(root.left, low, high);
    root.right = trimBST(root.right, low, high);
    return root;
  }
}
