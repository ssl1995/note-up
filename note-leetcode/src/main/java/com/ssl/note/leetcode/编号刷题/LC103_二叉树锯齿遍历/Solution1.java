package com.ssl.note.leetcode.编号刷题.LC103_二叉树锯齿遍历;

import com.ssl.note.common.utils.TreeNode;

import java.util.*;

public class Solution1 {

  /**
   * LC103_二叉树锯齿遍历
   * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
   * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
   */
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }

    List<List<Integer>> res = new ArrayList<>();
    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offerLast(root);

    boolean isLToR = true;
    while (!queue.isEmpty()) {
      int size = queue.size();

      Integer[] temp = new Integer[size];
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.pollFirst();

        if (node.left != null) {
          queue.offerLast(node.left);
        }
        if (node.right != null) {
          queue.offerLast(node.right);
        }

        // 预分配位置，省掉reverse
        temp[isLToR ? i : size - 1 - i] = node.val;
      }
      // 更新方向
      isLToR = !isLToR;
      res.add(Arrays.asList(temp));
    }

    return res;
  }
}
