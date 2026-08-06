package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.common.utils.TreeNode;


public class Solution1 {

  /**
   * LC98_验证二叉搜索树
   * 中序非递归-手写数组
   */
  private int MAX = 1001;
  private TreeNode[] stack = new TreeNode[MAX];
  private int r;

  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    // 中序遍历是用栈
    r = 0;
    long pre = Long.MIN_VALUE;

    while (r > 0 || root != null) {
      if (root != null) {
        stack[r++] = root;
        root = root.left;
      } else {
        TreeNode pop = stack[--r];

        // 中序遍历记录pre值，判断BST
        if (pre >= pop.val) {
          return false;
        }

        pre = pop.val;
        root = pop.right;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    TreeNode node2 = new TreeNode(2);
    TreeNode node1 = new TreeNode(1);
    TreeNode node3 = new TreeNode(3);
    node2.left = node1;
    node2.right = node3;
    System.out.println(solution1.isValidBST(node2));
  }


}
