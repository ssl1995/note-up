package com.ssl.note.leetcode.编号刷题.LC160_相交链表;

import com.ssl.note.common.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2022/6/18 16:29
 * @description
 */
public class Solution1 {

  /**
   * 相交链表
   * 最优解：
   * A走完，走B的；B走完，走A的，
   * 因为总路程相同，相交点相同，没有相交点，都返回null
   */
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return headA == null ? headB : headA;
    }

    ListNode l1 = headA;
    ListNode l2 = headB;
    while (l1 != l2) {
      l1 = l1 == null ? headB : l1.next;
      l2 = l2 == null ? headA : l2.next;
    }
    return l1;
  }

  // 错误：加上next就是死循环
  public ListNode getIntersectionNodeError(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return headA == null ? headB : headA;
    }

    ListNode l1 = headA;
    ListNode l2 = headB;
    while (l1 != l2) {
      l1 = l1.next == null ? headB : l1.next;
      l2 = l2.next == null ? headA : l2.next;
    }
    return l1;
  }
}
