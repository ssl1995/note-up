package com.ssl.note.leetcode.编号刷题.LC98_验证二叉搜索树;

import com.ssl.note.common.utils.TreeNode;

public class Solution3 {

  // 全局最小值和最大值
  public static long min, max;

  /**
   * LC98_验证二叉搜索树
   * 递归，每次拿到左右孩子的最小值和最大值
   */
  public static boolean isValidBST(TreeNode head) {
    if (head == null) {
      min = Long.MAX_VALUE;
      max = Long.MIN_VALUE;
      return true;
    }
    boolean lok = isValidBST(head.left);
    long lmin = min;
    long lmax = max;
    boolean rok = isValidBST(head.right);
    long rmin = min;
    long rmax = max;

    min = Math.min(Math.min(lmin, rmin), head.val);
    max = Math.max(Math.max(lmax, rmax), head.val);
    return lok && rok && lmax < head.val && head.val < rmin;
  }
}
