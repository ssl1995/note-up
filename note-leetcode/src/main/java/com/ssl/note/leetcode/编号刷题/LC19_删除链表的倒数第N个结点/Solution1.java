package com.ssl.note.leetcode.编号刷题.LC19_删除链表的倒数第N个结点;

import com.ssl.note.common.utils.ListNode;

public class Solution1 {

  /**
   * 删除链表的倒数第N个结点
   * 注意:建立dummy节点
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null || n < 1) {
      return null;
    }
    int len = dfs(head, n);
    // 如果链表长度与n相同，就返回head.next
    if (len == n) {
      return head.next;
    }
    // 否则递归过程中就找到了要删除的倒数第n个节点
    return head;
  }

  private int dfs(ListNode node, int n) {
    if (node == null) {
      return 0;
    }
    int pos = dfs(node.next, n);
    // 递归求长度过程中找待删除的节点
    if (pos + 1 == n + 1) {
      node.next = node.next.next;
    }
    return pos + 1;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    // 虚拟 1 2 3 4 5
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    int n = 2;

    ListNode newHead = solution.removeNthFromEnd(node1, n);

    while (newHead != null) {
      System.out.print(newHead.val);
      System.out.print(" ");
      newHead = newHead.next;
    }
  }

}
