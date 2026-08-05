package com.ssl.note.leetcode.编号刷题.LC102_二叉树的层次遍历;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {

  public static int MAXN = 2001;

  public static TreeNode[] queue = new TreeNode[MAXN];

  public static int l, r;

  // 提交时把方法名改为levelOrder，此方法为每次处理一层的优化bfs，此题推荐
  public static List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> ans = new ArrayList<>();
    // 手写一个队列
    l = 0;
    r = 0;
    queue[r++] = root;

    while (l != r) {
      int size = r - l;
      List<Integer> temp = new ArrayList<>();

      // 收集信息也可以放到外边，就可以和103题统一思维
      for (int i = l; i < r; i++) {
        temp.add(queue[i].val);
      }

      while (size-- > 0) {
        TreeNode pop = queue[l++];

        if (pop.left != null) {
          queue[r++] = pop.left;
        }
        if (pop.right != null) {
          queue[r++] = pop.right;
        }
      }
      ans.add(temp);
    }

    return ans;
  }
}
