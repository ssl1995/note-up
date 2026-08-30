package com.ssl.note.leetcode.编号刷题.LC234_回文链表;

import com.ssl.note.common.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2022/6/19 10:30
 * @description
 */
public class Solution {
  /**
   * 回文链表
   * 示例：
   * 输入：head = [1,2,2,1]
   * 输出：true
   * 最优解：时间复杂度 = O（n），空间复杂度=O（1）
   */
  public boolean isPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }
    // 1、找中点，偶数在前一个节点
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    // 记录后半头结点和前一个节点
    ListNode halfPre = slow;
    ListNode halfHead = reverse(slow.next);
    slow.next = null;
    // 2、回到两个头，重新遍历判断
    fast = halfHead;
    slow = head;
    while (slow != null && fast != null) {
      if (slow.val != fast.val) {
        return false;
      }
      slow = slow.next;
      fast = fast.next;
    }
    // 3、(备选)还原后半链表
    halfPre.next = reverse(halfHead);
    return true;
  }

  private ListNode reverse(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode cur = head;
    ListNode pre = null;
    while (cur != null) {
      ListNode next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    return pre;
  }
}
