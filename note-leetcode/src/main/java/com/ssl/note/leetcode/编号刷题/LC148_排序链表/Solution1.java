package com.ssl.note.leetcode.编号刷题.LC148_排序链表;

import com.ssl.note.common.utils.ListNode;

public class Solution1 {

  /**
   * 排序链表
   * 输入：head = [4,2,1,3]
   * 输出：[1,2,3,4]
   */
  public ListNode sortList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    int n = 0;
    ListNode cur = head;
    while (cur != null) {
      cur = cur.next;
      n++;
    }

    // dummy：哨兵，"已排序部分"的最前端
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    // step:1、2、4、8相邻排序（自底向上归并，step = 当前每段有序链表的长度）
    for (int step = 1; step < n; step *= 2) {
      // prev：会移动的"已排序部分"的末尾，每轮 step 从 dummy 重新出发
      ListNode prev = dummy;
      cur = dummy.next;

      // 每轮 while 处理一对相邻的、长度各为 step 的有序段
      while (cur != null) {
        // 左段：从 cur 开始
        ListNode left = cur;
        // 第一刀：左段切满step个并断开，返回右段的头
        ListNode right = split(cur, step);
        // 第二刀：右段也切满step个并断开，返回下一对的头给cur遍历用
        cur = split(right, step);

        // [left,right]排序，返回排序后的末尾节点
        prev = merge(prev, left, right);
      }
    }
    return dummy.next;
  }

  // head往后找到第step节点，并断开，返回该节点
  private ListNode split(ListNode head, int step) {
    if (head == null) {
      return null;
    }
    ListNode cur = head;
    // step是1步，就走0步
    for (int i = 1; i < step && cur.next != null; i++) {
      cur = cur.next;
    }
    // 再走下一步，就是head走step的末尾
    ListNode end = cur.next;
    // 断开
    cur.next = null;
    return end;
  }

  // 合并两个有序链表，拼接到prev后面，返回拼接后的末尾节点
  private ListNode merge(ListNode prev, ListNode left, ListNode right) {
    // 从prev开始
    ListNode cur = prev;

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

    // cur要走到末尾节点
    while (cur.next != null) {
      cur = cur.next;
    }

    return cur;
  }

}
