package com.ssl.note.leetcode.编号刷题.LC4_寻找两个正序数组的中位数;

public class Test {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }
    int m = nums1.length;
    int n = nums2.length;
    boolean isEven = (m + n) % 2 == 0;

    int nums1Left = 0, nums1Right = m;

    while (nums1Left <= nums1Right) {
      int i = (nums1Left + nums1Right) / 2;
      int j = (m + n + 1) / 2 - i;

      int num1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
      int num1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];

      int num2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
      int num2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];
      if (num1LeftMax <= num2RightMin && num1RightMin >= num2LeftMax) {
        if (isEven) {
          int num1 = Math.max(num1LeftMax, num2LeftMax);
          int num2 = Math.min(num1RightMin, num2RightMin);
          return (num1 + num2) / 2d;
        } else {
          return Math.max(num1LeftMax, num2LeftMax);
        }
      } else {
        if (num1LeftMax > num2RightMin) {
          nums1Right = i - 1;
        } else {
          nums1Left = i + 1;
        }
      }
    }

    return -1d;
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 2};
    int[] nums2 = {-10, -9, -8};
    Test test = new Test();
    System.out.println(test.findMedianSortedArrays(nums1, nums2));

  }
}
