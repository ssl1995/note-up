package com.ssl.note.leetcode.编号刷题.LC437_路径之和III;


import com.ssl.note.common.utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  private int res = 0;

  /**
   * 路径之和III
   * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
   * 输出：3
   * 解释：和等于 8 的路径有 3 条，如图所示。
   * LC560 和为k的子数组的二叉树版本
   */
  public int pathSum(TreeNode root, int targetSum) {
    // 初始化Map，当preSum=target时候，说明出现了1次
    Map<Long, Integer> map = new HashMap<>();
    map.put(0L, 1);
    // 初始化前缀和
    Long preSum = 0L;

    dfs(root, preSum, targetSum, map);

    return res;
  }

  /**
   * long 防止溢出,为了兼容TreeNode的val定义，使用基本数据类型
   */
  private void dfs(TreeNode node, Long preSum,
                   Integer target, Map<Long, Integer> map) {
    if (node == null) {
      return;
    }
    preSum += node.val;

    if (map.containsKey(preSum - target)) {
      res += map.get(preSum - target);
    }

    map.put(preSum, map.getOrDefault(preSum, 0) + 1);

    dfs(node.left, preSum, target, map);
    dfs(node.right, preSum, target, map);

    // DFS从左子树回到父节点后要去右子树，左子树路径上的前缀和不能留在 map 中影响右子树的计算
    // 回溯，消除该节点路径和的影响
    map.put(preSum, map.getOrDefault(preSum, 0) - 1);
  }


}
