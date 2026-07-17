package com.ssl.note.leetcode.编号刷题.LC33_搜索旋转排序数组;

/**
 * @author SongShengLin
 * @date 2022/1/23 9:52 PM
 * @description
 */
public class Test {

  /**
   * 搜索旋转排序数组
   * 输入：nums = [4,5,6,7,0,1,2], target = 0
   * 输出：4
   */
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      } else {
        if (nums[0] <= nums[mid]) {
          if (nums[0] <= target && target < nums[mid]) {
            right = mid - 1;
          } else {
            left = mid + 1;
          }
        } else {
          if (nums[mid] < target && target <= nums[nums.length - 1]) {
            left = mid + 1;
          } else {
            right = mid - 1;
          }
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Test solution = new Test();
    int[] nums = {4, 5, 6, 7, 0, 1, 2};
    int target = 0;
    System.out.println(solution.search(nums, target));
  }
}
