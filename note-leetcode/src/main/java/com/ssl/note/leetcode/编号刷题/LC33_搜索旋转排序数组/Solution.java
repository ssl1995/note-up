package com.ssl.note.leetcode.编号刷题.LC33_搜索旋转排序数组;

/**
 * @author SongShengLin
 * @date 2022/1/23 9:52 PM
 * @description
 */
public class Solution {

  /**
   * 搜索旋转排序数组
   * 输入：nums = [4,5,6,7,0,1,2], target = 0
   * 输出：4
   */
  public int search(int[] nums, int target) {
    int n = nums.length;
    int l = 0;
    int r = n - 1;

    while (l <= r) {
      int m = l + (r - l) / 2;
      if (nums[m] == target) {
        return m;
      } else {
        // 左侧有序，就二分
        if (nums[0] <= nums[m]) {
          if (nums[0] <= target && target < nums[m]) {
            r = m - 1;
          } else {
            l = m + 1;
          }
        } else {
          // 右侧有序，就二分
          if (nums[m] < target && target <= nums[n - 1]) {
            l = m + 1;
          } else {
            r = m - 1;
          }
        }

      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {4, 5, 6, 7, 0, 1, 2};
    int target = 0;
    System.out.println(solution.search(nums, target));
  }
}
