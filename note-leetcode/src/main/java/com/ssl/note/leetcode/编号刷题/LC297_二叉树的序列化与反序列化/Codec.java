package com.ssl.note.leetcode.编号刷题.LC297_二叉树的序列化与反序列化;

import com.ssl.note.common.utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Codec {

  // null符：前序遍历，把nul记录下来，一个前序序列就能唯一确定一棵树
  private final String NULL_VALUE = "#";
  // 分隔符：反序列化确定队列
  private final String SPLIT = "!";

  /**
   * 序列化-二叉树
   * 前序遍历
   */
  public String serialize(TreeNode root) {
    if (root == null) {
      // 难点：null节点也需要一个分隔符
      // #!
      return NULL_VALUE + SPLIT;
    }
    // 前序遍历:1!左！右
    return root.val + SPLIT + serialize(root.left) + serialize(root.right);
  }

  /**
   * 反序列化-二叉树
   */
  public TreeNode deserialize(String data) {
    // data:1!2!#!#!2!#!#!，然后按照分隔符拆分
    String[] split = data.split(SPLIT);
    // 12##3##：前序遍历
    Deque<String> queue = new ArrayDeque<>();
    for (String str : split) {
      queue.offer(str);
    }
    return dfs(queue);
  }

  private TreeNode dfs(Queue<String> queue) {
    if (queue.isEmpty()) {
      return null;
    }
    // 12##3##
    String poll = queue.poll();
    if (poll.equals(NULL_VALUE)) {
      return null;
    }
    TreeNode node = new TreeNode(Integer.parseInt(poll));
    node.left = dfs(queue);
    node.right = dfs(queue);
    return node;
  }

  public static void main(String[] args) {
    Codec codec = new Codec();
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(2);
    node1.left = node2;
    node1.right = node3;

    String serialize = codec.serialize(node1);
    System.out.println("序列化：" + serialize);

    TreeNode root = codec.deserialize(serialize);
    System.out.println("反序列化：" + root.val);
  }
}
