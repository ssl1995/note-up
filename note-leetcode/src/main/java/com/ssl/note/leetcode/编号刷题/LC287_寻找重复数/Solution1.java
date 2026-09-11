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
  // 如果slow和fast初始化都为0，就是do-while
  public int findDuplicate(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    // 初始化慢指针和快指针：把数组看成虚拟链表 next(x)=nums[x]
    // 以nums=[1,3,4,2,2]为例，链路为：0→1→3→2→4→2...，环2→4→2，环入口=2=重复数
    // 下标0就是虚拟链表的head（值域[1,n]保证无人指向0，它没有前驱）

    // 从头节点0开始
    int slow = 0;
    int fast = 0;

    // 第一阶段：找相遇点
    // 如果用while(slow!=fast)就直接false跳出，所以用do-while
    do {
      slow = nums[slow];
      fast = nums[nums[fast]];
    } while (slow != fast);

    // 第二次相遇：fast从头节点0开始
    fast = 0;
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }

    return slow;
  }

  /**
   * LC142_判断环形链表II，可能无环，所以需要判断
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

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {1, 3, 4, 2, 2};
    System.out.println(solution.findDuplicate(nums));
  }
}
