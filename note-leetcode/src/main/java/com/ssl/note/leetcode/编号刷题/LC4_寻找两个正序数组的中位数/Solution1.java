package com.ssl.note.leetcode.编号刷题.LC4_寻找两个正序数组的中位数;

public class Solution1 {

  /**
   * 解法二：时间复杂度最优解
   * 时间复杂度：O(log(min(m+n)))
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // 在短数组上二分，找划分点
    // 保证 nums1 是较短的数组
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }
    // m是短数组长度，n是长数组长度
    int m = nums1.length;
    int n = nums2.length;
    int left = 0, right = m;

    while (left <= right) {
      int i = (left + right) / 2;        // nums1 的划分点
      int j = (m + n + 1) / 2 - i;       // nums2 的划分点

      int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
      int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
      int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
      int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

      if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
        // 划分正确
        if ((m + n) % 2 == 0) {
          return (Math.max(nums1LeftMax, nums2LeftMax)
              + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
        } else {
          return Math.max(nums1LeftMax, nums2LeftMax);
        }
      } else if (nums1LeftMax > nums2RightMin) {
        // nums1 左边太大，往左缩
        right = i - 1;
      } else {
        // nums1 左边太小，往右扩
        left = i + 1;
      }
    }
    return 0.0;
  }
}
