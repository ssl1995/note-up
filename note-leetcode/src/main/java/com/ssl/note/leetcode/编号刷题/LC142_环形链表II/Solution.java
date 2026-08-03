package com.ssl.note.leetcode.编号刷题.LC142_环形链表II;

import com.ssl.note.common.utils.ListNode;

public class Solution {

  /**
   * LC142_判断环形链表II：
   * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回null
   */
  public ListNode detectCycle(ListNode head) {
    if (head == null) {
      return null;
    }
    // 环形题，快慢指针同起点
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        break;
      }
    }

    // 无环while条件被破坏，必须写全
    if (fast == null || fast.next == null) {
      return null;
    }

    fast = head;

    while (fast != slow) {
      fast = fast.next;
      slow = slow.next;
    }

    return fast;
  }
}
