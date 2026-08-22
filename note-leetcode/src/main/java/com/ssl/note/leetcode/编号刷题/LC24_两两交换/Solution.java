package com.ssl.note.leetcode.编号刷题.LC24_两两交换;

import com.ssl.note.common.utils.ListNode;
import com.ssl.note.common.utils.ListNodeUtil;

public class Solution {

  /**
   * 两两交换链表中的节点（迭代）
   * 核心：dummy + prev 指针，每轮改三条边
   */
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode pre = dummy;
    // dummy → 1 → 2 → 3 → 4 → null
    // dummy → 2 → 1 → 4 → 3 → null
    while (pre.next != null && pre.next.next != null) {
      ListNode first = pre.next;
      ListNode second = pre.next.next;

      // 从左到右移动指针
      pre.next = second;
      first.next = second.next;
      second.next = first;

      // pre[first,second]next 变 pre[second,first]next
      pre = first;
    }

    return dummy.next;
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
  }
}
