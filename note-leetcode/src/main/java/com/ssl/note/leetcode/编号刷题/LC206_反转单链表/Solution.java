package com.ssl.note.leetcode.编号刷题.LC206_反转单链表;

import com.ssl.note.common.utils.ListNode;

public class Solution {

  /**
   * 反转单链表
   * 迭代
   */
  public ListNode reverseList(ListNode head) {
    ListNode pre = null;
    ListNode cur = head;
    while (cur != null) {
      // 先记录后继结点
      ListNode next = cur.next;
      cur.next = pre;

      pre = cur;
      cur = next;
    }
    // 返回pre，不是cur
    return pre;
  }
}
