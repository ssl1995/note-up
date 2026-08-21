package com.ssl.note.leetcode.编号刷题.LC160_相交链表;

import com.ssl.note.common.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2022/6/18 16:29
 * @description
 */
public class Solution {

  /**
   * 相交链表
   */
  public ListNode getIntersectionNode(ListNode l1, ListNode l2) {
    int n1 = getLen(l1);
    int n2 = getLen(l2);
    ListNode cur1 = l1;// 指向长的
    ListNode cur2 = l2;
    if (n1 < n2) {
      cur1 = l2;
      cur2 = l1;
    }

    int diff = Math.abs(n1 - n2);
    while (diff-- > 0) {
      cur1 = cur1.next;
    }

    while (cur1 != cur2) {
      cur1 = cur1.next;
      cur2 = cur2.next;
    }

    return cur1;
  }

  private int getLen(ListNode node) {
    int n1 = 0;
    ListNode cur1 = node;
    while (cur1 != null) {
      cur1 = cur1.next;
      n1++;
    }
    return n1;
  }
}
