package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;


public class Solution1 {

  /**
   * 验证是否是BST
   * 中序非递归
   */
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    // 中序遍历是用栈
    Deque<TreeNode> stack = new ArrayDeque<>();
    long pre = Long.MIN_VALUE;

    while (!stack.isEmpty() || root != null) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode pop = stack.pop();

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


}
