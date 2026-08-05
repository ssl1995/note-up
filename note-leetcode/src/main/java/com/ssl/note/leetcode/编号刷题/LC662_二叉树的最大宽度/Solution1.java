package com.ssl.note.leetcode.编号刷题.LC662_二叉树的最大宽度;

import com.ssl.note.common.utils.TreeNode;

public class Solution1 {

  private int MAX = 3001;
  private TreeNode[] nodeQueue = new TreeNode[MAX];
  private int[] indexQueue = new int[MAX];
  private int l, r;

  /**
   * 二叉树的最大宽度
   * 某一层最左存在的节点到最优存在的节点的长度
   * 注意：不一定是叶子节点
   */
  public int widthOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }
    l = r = 0;
    nodeQueue[r] = root;
    // 初始根节点虚拟下标为1,左孩子=2i，右孩子=2i+1
    // 初始根节点虚拟下标为-,左孩子=2i+1，右孩子=2i+2
    indexQueue[r++] = 1;

    int res = 0;

    while (l < r) {
      int size = r - l;

      // [l,r-1]两边端点就是当前最左和最右位置，计算长度
      int curWidth = indexQueue[r - 1] - indexQueue[l] + 1;
      res = Math.max(res, curWidth);

      while (size-- > 0) {
        // 指针要同步移动，最后++
        TreeNode node = nodeQueue[l];
        int nodeIndex = indexQueue[l++];
        if (node.left != null) {
          nodeQueue[r] = node.left;
          indexQueue[r++] = 2 * nodeIndex;
        }
        if (node.right != null) {
          nodeQueue[r] = node.right;
          indexQueue[r++] = 2 * nodeIndex + 1;
        }
      }
    }

    return res;
  }


}
