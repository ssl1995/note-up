package com.ssl.note.leetcode.编号刷题.LC148_排序链表;

import com.ssl.note.common.utils.ListNode;

public class Test {

  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode mid = getMid(head);
    ListNode rightHead = mid.next;
    mid.next = null;

    ListNode left = sortList(head);
    ListNode right = sortList(rightHead);

    return merge(left, right);
  }

  private ListNode getMid(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  private ListNode merge(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }

    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        cur.next = l1;
        l1 = l1.next;
      } else {
        cur.next = l2;
        l2 = l2.next;
      }
      cur = cur.next;
    }

    // 错误：这里漏了一段代码，导致外层递归栈溢出
//    cur.next = l1 == null ? l2 : l1;

    return dummy.next;
  }
}
