package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.common.utils.TreeNode;

public class Solution2 {

  /**
   * LC98_验证二叉搜索树
   * 思路：Morris中序遍历 = O(1)空间的普通中序遍历；BST的中序遍历必严格递增。
   * 在Morris遍历中“真正访问节点”的两个位置做判断即可。
   */
  public boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }
    TreeNode cur = root;

    // root初始化可能直接就是整形最小值,所以pre初始化<整形最小，选Long的最小值
    long preValue = Long.MIN_VALUE;

    while (cur != null) {
      if (cur.left == null) {
        // 中序遍历：没有左子树，当前cur就是中序遍历的下一个访问节点
        if (preValue >= cur.val) {
          return false;
        }
        preValue = cur.val;

        cur = cur.right;
      } else {
        TreeNode pre = cur.left;
        while (pre.right != null && pre.right != cur) {
          pre = pre.right;
        }
        if (pre.right == null) {
          pre.right = cur;
          cur = cur.left;
        } else {
          // 中序遍历：第二次到达cur，左子树已遍历完，cur是中序遍历的下一个访问节点
          if (preValue >= cur.val) {
            return false;
          }
          preValue = cur.val;

          pre.right = null;
          cur = cur.right;
        }
      }
    }
    return true;
  }

}
