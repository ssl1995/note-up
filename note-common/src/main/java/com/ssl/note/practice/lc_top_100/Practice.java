package com.ssl.note.practice.lc_top_100;


public class Practice {

  /**
   * 1. 07-链表 LC 24 两两交换链表中的节点
   * 2. 17-技巧 LC 136 只出现一次的数字
   * 加137 只出现一次数字II
   * 加260 只出现一次数字III
   * 3. 11-二分查找 LC 4 寻找两个正序数组的中位数
   * 加LC215_数组中的第K大元素
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    if (m > n) {
      return findMedianSortedArrays(nums2, nums1);
    }

    int left = 0, right = m;
    boolean isEven = (m + n) % 2 == 0;
    while (left <= right) {
      int i = left + (right - left) / 2;
      int j = (m + n + 1) / 2 - i;

      int a = getMin(nums1, i - 1);
      int b = getMax(nums1, i);
      int c = getMin(nums2, j - 1);
      int d = getMax(nums2, j);

      if (a <= d && b >= c) {
        if (isEven) {
          int num1 = Math.max(a, c);
          int num2 = Math.min(b, d);
          return (num1 + num2) / 2d;
        } else {
          return Math.max(a, c);
        }
      } else if (a > d) {
        right = i - 1;
      } else {
        left = i + 1;
      }
    }

    return -1d;
  }

  private int getMax(int[] nums, int index) {
    if (index > nums.length - 1) {
      return Integer.MAX_VALUE;
    }
    return nums[index];
  }

  private int getMin(int[] nums, int index) {
    if (index < 0) {
      return Integer.MIN_VALUE;
    }
    return nums[index];
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[] nums1 = {1, 2};
    int[] nums2 = {3, 4};
    System.out.println(practice.findMedianSortedArrays(nums1, nums2));
  }
}
