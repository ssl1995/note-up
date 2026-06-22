package com.ssl.note.leetcode.编号刷题.LC169_多数元素;

public class Solution {

  /**
   * 多数元素
   * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
   * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
   * 示例：
   * 输入：nums = [3,2,3]
   * 输出：3
   */
  public int majorityElement(int[] nums) {
    int x = nums[0];
    int count = 1;

    for (int i = 1; i < nums.length; i++) {
      // 投票
      if (nums[i] == x) {
        count++;
      } else {
        count--;
      }
      // 计数器如果=0,重置计数器和候选数
      if (count == 0) {
        x = nums[i];
        count = 1;
      }
    }
    return x;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {10, 9, 9, 9, 10};
    // 9
    System.out.println(solution.majorityElement(nums));
  }
}
