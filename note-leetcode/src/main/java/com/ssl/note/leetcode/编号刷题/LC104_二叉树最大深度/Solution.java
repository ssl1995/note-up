package com.ssl.note.leetcode.编号刷题.LC104_二叉树最大深度;

import com.ssl.note.common.utils.TreeNode;

/**
 * @author SongShengLin
 * @date 2022/7/21 08:38
 * @description
 */
public class Solution {

  /**
   * 求一个二叉树的最大深度
   */
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    // 以下判断可加可不加
//    if (root.left == null && root.right == null) {
//      return 1;
//    }
    int left = maxDepth(root.left);
    int right = maxDepth(root.right);
    return Math.max(left, right) + 1;
  }

}
