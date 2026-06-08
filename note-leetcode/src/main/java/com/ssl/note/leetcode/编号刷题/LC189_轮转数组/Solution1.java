package com.ssl.note.leetcode.编号刷题.LC189_轮转数组;

public class Solution1 {

  /**
   * LC189_轮转数组
   * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
   * 输入: nums = [1,2,3,4,5,6,7], k = 3
   * 输出: [5,6,7,1,2,3,4]
   * 解释:
   * 向右轮转 1 步: [7,1,2,3,4,5,6]
   * 向右轮转 2 步: [6,7,1,2,3,4,5]
   * 向右轮转 3 步: [5,6,7,1,2,3,4]
   */
  public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    if (k == 0) {
      return;
    }
    // 每次推动的元素个数
    int moved = 0;
    for (int start = 0; moved < n; start++) {
      int curIndex = start;
      int curValue = nums[start];

      do {
        int targetIndex = (curIndex + k) % n;
        int saved = nums[targetIndex];

        nums[targetIndex] = curValue;

        curValue = saved;
        curIndex = targetIndex;

        moved++;
      } while (curIndex != start);
    }
  }
}
