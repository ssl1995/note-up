package com.ssl.note.leetcode.编号刷题.LC148_排序链表;

import com.ssl.note.common.utils.ListNode;

public class Solution {

  /**
   * 排序链表
   * 输入：head = [4,2,1,3]
   * 输出：[1,2,3,4]
   * 方法：归并排序法
   */
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    // 链表排序选归并排序：只需要从头遍历，天然契合链表
    // 拆：找中点拆开
    ListNode mid = getMiddleNode(head);
    ListNode rightHead = mid.next;
    mid.next = null;// 链表从中间断开

    // 排：递归，假设处理好了左右
    ListNode left = sortList(head);
    ListNode right = sortList(rightHead);

    // 合：合并左右
    return merge(left, right);
  }


  // 偶数取左边的标准写法，与234 回文链表找中点一样
  private ListNode getMiddleNode(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    // fast 自己能走两步时，才继续走
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  // LC21 合并两个有序链表
  private ListNode merge(ListNode left, ListNode right) {
    if (left == null || right == null) {
      return left == null ? right : left;
    }

    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;

    while (left != null && right != null) {
      if (left.val < right.val) {
        cur.next = left;
        left = left.next;
      } else {
        cur.next = right;
        right = right.next;
      }
      cur = cur.next;
    }

    cur.next = left == null ? right : left;

    return dummy.next;
  }

}
