package com.ssl.note.leetcode.编号刷题.LC112_路径之和;

import com.ssl.note.common.utils.TreeNode;

/**
 * @author SongShengLin
 * @date 2022/1/10 11:25 PM
 * @description
 */
public class Solution {

  /**
   * LC112_路径之和
   * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。
   * 判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
   * 如果存在，返回 true ；否则，返回 false 。
   * 叶子节点 是指没有子节点的节点。
   */
  public boolean hasPathSum(TreeNode root, int targetSum) {
    // 越过叶子节点还没有找到，失败
    if (root == null) {
      return false;
    }
    targetSum -= root.val;
    // 到达叶子节点 且 sum-root.val==0，成功
    if (root.left == null && root.right == null && targetSum == 0) {
      return true;
    }
    // 递归左右子树
    return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
  }


}
