package com.ssl.note.leetcode.编号刷题.LC138_复制带随机指针的链表;

/**
 * @author SongShengLin
 * @date 2022/1/11 9:12 AM
 * @description
 */
public class Solution2 {

  /**
   * 复制一个带有随机指针的链表
   */
  public Node copyRandomList(Node head) {
    // 不能||head.next==null，因为只要有1个节点都要重新复制
//    if (head == null) {
//      return null;
//    }
    // 1、新建节点，不动random
    Node cur = head;
    while (cur != null) {
      Node copy = new Node(cur.val);

      Node next = cur.next;
      cur.next = copy;
      copy.next = next;

      cur = next;
    }

    // 2、修改复制节点的random
    cur = head;
    while (cur != null) {
      Node copy = cur.next;
      Node next = copy.next;
      // 判空1
      copy.random = cur.random == null ? null : cur.random.next;

      cur = next;
    }

    // 3、分离=修改复制节点的next
    cur = head;
    // 判空2
    Node newHead = cur == null ? null : cur.next;
    while (cur != null) {
      Node copy = cur.next;
      Node next = copy.next;

      cur.next = next;
      // 判空3
      copy.next = next == null ? null : next.next;

      cur = next;
    }

    return newHead;
  }
}
