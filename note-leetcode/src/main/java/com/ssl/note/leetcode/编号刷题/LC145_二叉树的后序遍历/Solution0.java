package com.ssl.note.leetcode.编号刷题.LC145_二叉树的后序遍历;

import com.ssl.note.common.utils.TreeNode;

import java.util.*;

public class Solution0 {
  /**
   * 二叉树后续遍历
   * 双栈法
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    if (root == null) {
      return new LinkedList<>();
    }
    List<Integer> res = new LinkedList<>();
    // 前序遍历的栈
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      // 入栈：根左右，出栈：根右左
      if (node.left != null) {
        stack.push(node.left);
      }
      if (node.right != null) {
        stack.push(node.right);
      }
    }
    // 根右左 翻转= 左右根=后序遍历
    Collections.reverse(res);

    return res;
  }
}
