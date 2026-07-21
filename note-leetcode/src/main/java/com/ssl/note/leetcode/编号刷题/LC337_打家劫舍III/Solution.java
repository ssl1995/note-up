package com.ssl.note.leetcode.编号刷题.LC337_打家劫舍III;

import com.ssl.note.common.utils.TreeNode;

public class Solution {

  /**
   * 打家劫舍III
   * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
   * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
   * 聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
   * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
   * 补充：连续相连的两个节点被盗，房屋报警
   * 输入: root = [3,2,3,null,3,null,1]
   * 输出: 7
   * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
   */
  public int rob(TreeNode root) {
    if (root == null) {
      return 0;
    }
    // 返回一个只有2个元素的数组
    int[] nums = dfs(root);
    return Math.max(nums[0], nums[1]);
  }

  /**
   * 树形DP（最优解）：时间 O(N)，空间 O(H)
   * 关键思路：父节点用子节点的答案时，受"父节点是否被偷"影响，
   * 单个返回值信息不够，所以子节点把两种状态打包上传：
   * [0] = 不偷当前节点时，子树最大金额
   * [1] = 偷当前节点时，子树最大金额
   */
  private int[] dfs(TreeNode node) {
    if (node == null) {
      return new int[2];
    }

    int[] left = dfs(node.left);
    int[] right = dfs(node.right);

    int[] nums = new int[2];
    // 不偷node：左右孩子偷不偷都行，各取最优
    nums[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    // 偷node：左右孩子都不能偷
    nums[1] = left[0] + right[0] + node.val;

    return nums;
  }
}
