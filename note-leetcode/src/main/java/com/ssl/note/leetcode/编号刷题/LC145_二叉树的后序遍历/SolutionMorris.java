package com.ssl.note.leetcode.编号刷题.LC145_二叉树的后序遍历;

import com.ssl.note.common.utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class SolutionMorris {

  /**
   * 后续遍历
   * morris遍历写法
   */
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    TreeNode cur = root;

    while (cur != null) {
      // 没有左孩子
      if (cur.left == null) {
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
          pre.right = null;
          // 后续遍历：每次第二次到达一个有左子树的节点cur时，把cur.left树的最右链逆序输出。
          addRes(cur.left, res);
          // 第二次来到时，cur已经遍历完了左孩子
          cur = cur.right;
        }
      }
    }
    // 后续遍历：由于根节点及其右侧链没有父亲，需要单独记录
    addRes(root, res);
    return res;
  }

  // 把从node开始沿right指针走到底的这条链，逆序加入结果。
  private void addRes(TreeNode node, List<Integer> res) {
    int size = res.size();
    while (node != null) {
      res.add(size, node.val);
      node = node.right;
    }
  }

}
