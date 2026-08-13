package com.ssl.note.leetcode.编号刷题.LC24_两两交换;

import com.ssl.note.common.utils.ListNode;
import com.ssl.note.common.utils.ListNodeUtil;

public class Solution {

  /**
   * 两两交换链表中的节点（迭代）
   * 核心：dummy + prev 指针，每轮改三条边
   */
  public ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode prev = dummy;
    // dummy → 1 → 2 → 3 → 4 → null
    // dummy → 2 → 1 → 4 → 3 → null
    while (prev.next != null && prev.next.next != null) {
      ListNode cur = prev.next;
      ListNode next = prev.next.next;

      // 交换2个节点有3个指针需要变化
      // 易错的第一个pre的后续
      prev.next = next;
      cur.next = next.next;
      next.next = cur;

      prev = cur;
    }

    return dummy.next;
  }

  /**
   * 两两交换链表中的节点（递归）
   */
  public ListNode swapPairsRecursive(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode first = head;
    ListNode second = head.next;
    first.next = swapPairsRecursive(second.next);
    second.next = first;
    return second;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    ListNodeUtil.printListNode(solution.swapPairs(node1));
    ListNodeUtil.printListNode(solution.swapPairsRecursive(node1));
  }
}
