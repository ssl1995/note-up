package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  /**
   * LC98_验证二叉搜索树
   * 中序非递归
   */
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    // 中序遍历是用栈
    Deque<TreeNode> stack = new ArrayDeque<>();
    // root初始化可能直接就是整形最小值,所以pre初始化<整形最小，选Long的最小值
    long pre = Long.MIN_VALUE;

    while (!stack.isEmpty() || root != null) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        TreeNode pop = stack.pop();

        // 中序遍历是严格递增，一旦>=就违规
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
