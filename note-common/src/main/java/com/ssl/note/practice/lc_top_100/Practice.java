package com.ssl.note.practice.lc_top_100;

import com.ssl.note.common.utils.TreeNode;


public class Practice {

  // 1. 02-双指针 LC 42 接雨水
  // 2. 09-图论 LC 207 课程表
  // 3. 08-二叉树 LC 114 二叉树展开为链表
  public void flatten(TreeNode root) {
    if (root == null) {
      return;
    }
    TreeNode cur = root;
    while (cur != null) {
      if (cur.left != null) {
        TreeNode mostRight = cur.left;
        while (mostRight.right != null) {
          mostRight = mostRight.right;
        }

        mostRight.right = cur.right;
        cur.right = cur.left;
        cur.left = null;
      }
      cur = cur.right;
    }
  }

  public static void main(String[] args) {
    Practice practice = new Practice();

  }
}
