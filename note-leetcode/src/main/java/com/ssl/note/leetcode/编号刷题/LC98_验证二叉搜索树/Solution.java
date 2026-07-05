package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.common.utils.TreeNode;


public class Solution {

  /**
   * pre是会超过int类型的边界值,所以使用Long的边界值
   */
  private long pre = Long.MIN_VALUE;

  /**
   * 验证是否是BST
   * 中序递归法
   */
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    // 左子树
    boolean left = isValidBST(root.left);
    if (!left) {
      return false;
    }

    // 陷阱：不能只比较当前节点: 左<根<右，需要递归
    // 正确：中序遍历记录pre值，判断BST
    if (pre >= root.val) {
      return false;
    }
    pre = root.val;

    // 右子树
    return isValidBST(root.right);
  }

}
