package com.ssl.note.leetcode.编号刷题.LC287_寻找重复数;

public class Solution {

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
    // 初始化慢指针和快指针，都从第一个节点开始
    // 注意：Floyd 判圈算法要求两个指针从同一点出发
    int slow = nums[0];
    int fast = nums[0];

    // 第一阶段：找相遇点
    do {
      slow = nums[slow];
      fast = nums[nums[fast]];
    } while (slow != fast);

    // 第二次相遇后
    // 快指针从第一个节点开始
    fast = nums[0];
    while (slow != fast) {
      slow = nums[slow];
      fast = nums[fast];
    }

    return slow;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 3, 4, 2, 2};
    System.out.println(solution.findDuplicate(nums));
  }
}
