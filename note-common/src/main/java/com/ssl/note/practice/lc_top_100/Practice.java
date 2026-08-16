package com.ssl.note.practice.lc_top_100;


import com.ssl.note.common.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Practice {

  /**
   * 1. 15-动态规划 LC 300 最长递增子序列
   * 2. 08-二叉树 LC 114 二叉树展开为链表
   * 3. 09-图论 LC 207 课程表
   */
  private Map<Integer, Integer> map;

  public TreeNode buildTree(int[] pre, int[] in) {
    map = new HashMap<>();
    for (int i = 0; i < in.length; i++) {
      map.put(in[i], i);
    }
//    return dfs(pre, 0, pre.length - 1, 0, in.length - 1);
    return dfs1(pre, 0, pre.length - 1, map,0, in.length - 1);
  }

  private TreeNode dfs(int[] pre, int preStart, int preEnd, int inStart, int inEnd) {
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }
    int index = map.get(pre[preStart]);
    int leftCount = index - inStart;

    TreeNode node = new TreeNode(pre[preStart]);
    node.left = dfs(pre, preStart + 1, preStart + leftCount, inStart, index - 1);
    node.right = dfs(pre, preStart + leftCount + 1, preEnd, index + 1, inEnd);

    return node;
  }

  private TreeNode dfs1(int[] pre, int preStart, int preEnd,
                        Map<Integer, Integer> map, int inStart, int inEnd) {
    if (inStart > inEnd || preStart > preEnd) {
      return null;
    }
    int index = map.get(pre[preStart]);
    int leftCount = index - inStart;

    TreeNode node = new TreeNode(pre[preStart]);
    node.left = dfs1(pre, preStart + 1, preStart + leftCount, map, inStart, index - 1);
    node.right = dfs1(pre, preStart + leftCount + 1, preEnd, map, index + 1, inEnd);
    return node;
  }


  public static void main(String[] args) {
    Practice practice = new Practice();
    int[] nums1 = {4, 10, 4, 3, 8, 9};
  }
}
