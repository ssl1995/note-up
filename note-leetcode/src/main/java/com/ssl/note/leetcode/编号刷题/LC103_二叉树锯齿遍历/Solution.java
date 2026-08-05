package com.ssl.note.leetcode.编号刷题.LC103_二叉树锯齿遍历;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * LC103_二叉树锯齿遍历
   * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
   * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
   */
  private int MAX = 2001;
  private TreeNode[] deque = new TreeNode[MAX];
  private int l, r;
  private boolean isRightToleft;

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    l = r = 0;

    List<List<Integer>> res = new ArrayList<>();
    deque[r++] = root;
    isRightToleft = false;

    while (l < r) {
      int size = r - l;
      List<Integer> temp = new ArrayList<>();

      // 先收集信息，isRightToleft初始化false
      // isRightToleft=false,从左往右,l->r-1
      // isRightToleft=true,从右往左,r-1->l
      for (int i = !isRightToleft ? l : r - 1, j = !isRightToleft ? 1 : -1, k = 0; k < size; i += j, k++) {
        temp.add(deque[i].val);
      }

      // 再入队列
      while (size-- > 0) {
        TreeNode pop = deque[l++];

        if (pop.left != null) {
          deque[r++] = pop.left;
        }
        if (pop.right != null) {
          deque[r++] = pop.right;
        }
      }

      isRightToleft = !isRightToleft;
      res.add(temp);
    }

    return res;
  }
}
