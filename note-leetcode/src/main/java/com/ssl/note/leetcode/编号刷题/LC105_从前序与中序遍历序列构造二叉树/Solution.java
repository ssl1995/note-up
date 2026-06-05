package com.ssl.note.leetcode.编号刷题.LC105_从前序与中序遍历序列构造二叉树;

import com.ssl.note.common.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  /**
   * 给定两个整数数组 preorder 和 inorder，请构造二叉树并返回其根节点。
   * preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历
   * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
   * 输出: [3,9,20,null,null,15,7]
   */
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }
    return help(preorder, 0, map, 0, inorder.length-1);
  }

  private TreeNode help(int[] pre, int preStart, Map<Integer, Integer> map, int inStart, int inEnd) {
    if (inStart > inEnd) {
      return null;
    }
    int index = map.get(pre[preStart]);
    int leftCount = index - inStart;

    // 前序：preStart,[preStart+1,preStart+leftCount],[preStart+leftCount+1,preEnd]
    // 中序：[inStart,index-1],index,[index+1,inEnd]
    TreeNode node = new TreeNode(pre[preStart]);
    node.left = help(pre, preStart + 1, map, inStart, index - 1);
    node.right = help(pre, preStart + leftCount + 1, map, index + 1, inEnd);
    return node;
  }

  public static void main(String[] args) {
    int[] preOrder = new int[]{3,9,20,15,7};
    int[] inorder = new int[]{9,3,15,20,7};
    Solution solution = new Solution();
    TreeNode root = solution.buildTree(preOrder, inorder);
    System.out.println(root.val);
  }
}
