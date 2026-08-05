package com.ssl.note.leetcode.编号刷题.LC958_完全二叉树验证;

import com.ssl.note.common.utils.TreeNode;

public class Solution {

  private int MAX = 101;
  private TreeNode[] queue = new TreeNode[MAX];
  private int l, r;

  /**
   * LC958_完全二叉树验证
   * 给你一棵二叉树的根节点 root ，请你判断这棵树是否是一棵 完全二叉树 。
   * 定义：在一棵 完全二叉树 中，除了最后一层外，所有层都被完全填满，
   * 并且最后一层中的所有节点都尽可能靠左。最后一层（第 h 层）中可以包含 1 到 2h 个节点。
   */
  public boolean isCompleteTree(TreeNode root) {
    if (root == null) {
      return false;
    }
    l = 0;
    r = 0;
    queue[r++] = root;

    boolean isFindLeaf = false;

    while (l < r) {
      TreeNode node = queue[l++];

      // 必不是完全二叉树判断：
      // 1、有右无左，不是完全二叉树
      // 2、曾今命中过叶子节点，后续节点如果不是叶子，不是完全二叉树
      if ((node.left == null && node.right != null) ||
          (isFindLeaf && (node.left != null || node.right != null))
      ) {
        return false;
      }

      if (node.left != null) {
        queue[r++] = node.left;
      }
      if (node.right != null) {
        queue[r++] = node.right;
      }

      // 更新是否命中过叶子节点
      if (node.left == null || node.right == null) {
        isFindLeaf = true;
      }
    }

    return true;
  }
}
