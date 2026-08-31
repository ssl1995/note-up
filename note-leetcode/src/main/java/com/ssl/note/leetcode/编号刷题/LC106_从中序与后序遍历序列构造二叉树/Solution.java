package com.ssl.note.leetcode.编号刷题.LC106_从中序与后序遍历序列构造二叉树;

import com.ssl.note.common.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  /**
   * 给定两个整数数组 inorder 和 postorder，其中 inorder 是二叉树的中序遍历，postorder 是同一棵树的后序遍历，请构造二叉树并返回它的根节点。
   * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
   * 输出：[3,9,20,null,null,15,7]
   */
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    // 把中序遍历的值和下标存入map，方便O(1)查找根节点位置
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      map.put(inorder[i], i);
    }
    return dfs(postorder, 0, postorder.length - 1, map, 0, inorder.length - 1);
  }

  private TreeNode dfs(int[] post, int postStart, int postEnd,
                       Map<Integer, Integer> map, int inStart, int inEnd) {
    // 中序区间无效，说明没有节点需要构造
    if (inStart > inEnd || postStart > postEnd) {
      return null;
    }
    // 后序遍历的最后一个元素就是当前子树的根节点
    // 比如例子中的[9,15,7,20,3]的3就是整颗树的根
    int value = post[postEnd];
    int index = map.get(value);
    int leftCount = index - inStart;

    TreeNode node = new TreeNode(value);
    // 左子树后序段  = [postStart, postStart + leftCount - 1]
    // 右子树后序段  = [postStart + leftCount, postEnd - 1]
    // 根节点       = post[postEnd]
    node.left = dfs(post, postStart, postStart + leftCount - 1, map, inStart, index - 1);
    node.right = dfs(post, postStart + leftCount, postEnd - 1, map, index + 1, inEnd);
    return node;
  }

  public static void main(String[] args) {
    int[] inorder = new int[]{9, 3, 15, 20, 7};
    int[] postorder = new int[]{9, 15, 7, 20, 3};
    Solution solution = new Solution();
    TreeNode root = solution.buildTree(inorder, postorder);
    System.out.println(root.val);
  }
}
