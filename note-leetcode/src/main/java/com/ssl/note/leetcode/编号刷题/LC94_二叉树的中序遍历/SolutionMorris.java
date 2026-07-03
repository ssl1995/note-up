package com.ssl.note.leetcode.编号刷题.LC94_二叉树的中序遍历;

import com.ssl.note.common.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class SolutionMorris {

  /**
   * 中序遍历
   * morris遍历写法
   */
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    TreeNode cur = root;

    while (cur != null) {
      // 没有左孩子
      if (cur.left == null) {
        res.add(cur.val);
        cur = cur.right;
      } else {
        // 有左孩子，找到左孩子的最右节点
        TreeNode pre = cur.left;
        while (pre.right != null && pre.right != cur) {
          pre = pre.right;
        }
        // 第一次来到
        if (pre.right == null) {
          pre.right = cur;
          // 第一次来到时，cur还没有遍历过左孩子
          cur = cur.left;
        } else {
          // 第二次来到
          res.add(cur.val);
          pre.right = null;
          // 第二次来到时，cur已经遍历完了左孩子
          cur = cur.right;
        }
      }
    }
    return res;
  }
}
