package com.ssl.note.leetcode.编号刷题.LC287_寻找重复数;

import com.ssl.note.common.utils.ListNode;

public class Solution1 {

  /**
   * 寻找重复数
   * 给定一个包含n + 1 个整数的数组nums ，其数字都在[1, n]范围内（包括 1 和 n），可知至少存在一个重复的整数。
   * 假设nums只有一个重复的整数 ，返回这个重复的数 。
   * 输入：nums = [1,3,4,2,2]
   * 输出：2
   */
  public int findDuplicate(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    // 初始化慢指针和快指针
    // 数组和链表LC142题不一样，同点出发，先走再判=也可以换成do-while
    int slow = nums[0];
    int fast = nums[nums[0]];

    // 第一阶段：找相遇点
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[nums[fast]];
    }

    // 第二次相遇后
    // 快指针从第一个节点开始
    fast = 0;
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }
    // 返回重复的数，这里slow都是数啦
    return slow;
  }

  /**
   * LC142_判断环形链表II，可能无环，所以需要判断
   */
  public ListNode detectCycle(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
      return null;
    }
    ListNode slow = head;
    ListNode fast = head;

    boolean isCycle = false;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        isCycle = true;
        // 必须退出，有环时就会一直死循环
        break;
      }
    }

    if (!isCycle) {
      return null;
    }

    fast = head;

    while (fast != slow) {
      fast = fast.next;
      slow = slow.next;
    }

    return fast;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {1, 3, 4, 2, 2};
    System.out.println(solution.findDuplicate(nums));
  }
}
