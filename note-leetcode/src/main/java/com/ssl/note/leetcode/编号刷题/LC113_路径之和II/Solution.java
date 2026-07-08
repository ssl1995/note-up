package com.ssl.note.leetcode.编号刷题.LC113_路径之和II;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/10 11:45 PM
 * @description
 */
public class Solution {

  private List<List<Integer>> res;
  private List<Integer> path;

  /**
   * LC113_路径之和II
   * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
   */
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return new ArrayList<>();
    }
    res = new ArrayList<>();
    path = new ArrayList<>();
    dfs(root, targetSum);
    return res;
  }

  private void dfs(TreeNode root, int targetSum) {
    if (root == null) {
      return;
    }
    // 收集临时路径
    path.add(root.val);

    targetSum -= root.val;
    if (root.left == null && root.right == null && targetSum == 0) {
      // 回收满足条件的路径
      // 必须是复制，因为path后序还要回溯
      res.add(new ArrayList<>(path));
    }
    // 递归左右子树
    dfs(root.left, targetSum);
    dfs(root.right, targetSum);

    // 回溯：临时加入的路径需要取消
    path.remove(path.size() - 1);
  }


}
