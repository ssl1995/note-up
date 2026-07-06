package com.ssl.note.leetcode.编号刷题.LC230_二叉搜索树中第K小的元素;

import com.ssl.note.common.utils.TreeNode;

public class SolutionMorris {

  /**
   * 二叉搜索树中第K小的元素
   * 中序Morris遍历
   */
  public int kthSmallest(TreeNode root, int k) {
    if (root == null || k < 0) {
      return -1;
    }

    TreeNode cur = root;
    while (cur != null) {
      if (cur.left == null) {
        // 中序遍历
        k--;
        if (k == 0) {
          return cur.val;
        }

        cur = cur.right;
      } else {
        TreeNode pre = cur.left;
        while (pre.right != null && pre.right != cur) {
          pre = pre.right;
        }
        if (pre.right == null) {
          // 第一到达，是构造线索
          pre.right = cur;
          cur = cur.left;
        } else {
          // 第二次到达，才是真正的访问
          k--;
          if (k == 0) {
            return cur.val;
          }

          pre.right = null;
          cur = cur.right;
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);

    node5.left = node3;
    node5.right = node6;
    node3.left = node2;
    node3.right = node4;
    node2.left = node1;

    Solution solution = new Solution();
    SolutionMorris solutionMorris = new SolutionMorris();
    int k = 3;
    System.out.println(solution.kthSmallest(node5,k));
    System.out.println(solutionMorris.kthSmallest(node5,k));
  }
}
