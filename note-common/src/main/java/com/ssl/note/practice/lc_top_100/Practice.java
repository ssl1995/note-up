package com.ssl.note.practice.lc_top_100;

public class Practice {

  public int findMin(int[] nums) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {

      int mid = left + (right - left) / 2;
      int target = nums[0];

      if (nums[mid] < target) {
        right = mid;
      } else if (nums[mid] > target) {
        left = mid + 1;
      } else {
        right--;
      }
    }
    return nums[left];
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[] nums = {5, 7, 7, 8, 8, 10};
    int t = 8;
  }

}
