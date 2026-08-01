package com.ssl.note.leetcode.编号刷题.LC23_合并K个升序链表;

import com.ssl.note.common.utils.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author SongShengLin
 * @date 2022/1/21 8:31 AM
 * @description
 */
public class Solution1 {

  /**
   * 合并K个升序链表
   * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
   * 输出：[1,1,2,3,4,4,5,6]
   */
  public ListNode mergeKLists(ListNode[] lists) {
    int n = lists.length;
    if (n == 0) {
      return null;
    }
    if (n == 1) {
      return lists[0];
    }
    // 1、小根堆，排序比较器用val字段
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

    // 2、将每个链表的头节点放进堆中
    for (ListNode node : lists) {
      if (node != null) {
        minHeap.offer(node);
      }
    }

    ListNode dummy = new ListNode(-1);
    ListNode cur = dummy;

    // 3、小根堆头部作为新链表的节点，向后遍历
    while (!minHeap.isEmpty()) {
      ListNode node = minHeap.poll();
      cur.next = node;
      cur = cur.next;

      // 新的头入小根堆
      node = node.next;
      if (node != null) {
        minHeap.offer(node);
      }
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(4);
    ListNode node3 = new ListNode(5);
    node1.next = node2;
    node2.next = node3;
    ListNode node4 = new ListNode(1);
    ListNode node5 = new ListNode(3);
    ListNode node6 = new ListNode(4);
    node4.next = node5;
    node5.next = node6;
    ListNode node7 = new ListNode(2);
    ListNode node8 = new ListNode(6);
    node7.next = node8;
    Solution1 solution = new Solution1();
    ListNode[] lists = new ListNode[3];
    lists[0] = node1;
    lists[1] = node4;
    lists[2] = node7;
    printListNode(solution.mergeKLists(lists));
  }

  private static void printListNode(ListNode head) {
    ListNode cur = head;
    StringBuilder buffer = new StringBuilder();
    buffer.append("[");
    while (cur.next != null) {
      buffer.append(cur.val).append(" ");
      cur = cur.next;
    }
    buffer.append(cur.val).append("]");
    System.out.println(buffer);
  }
}
