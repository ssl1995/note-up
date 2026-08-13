package com.ssl.note.leetcode.编号刷题.LC4_寻找两个正序数组的中位数;

public class Solution {

  /**
   * 寻找两个正序数组中的中位数
   * 使用合并数组法，时间复杂度 O(m+n)
   * 解题步骤：
   * 1. 合并两个有序数组为一个新的有序数组
   * 2. 根据合并后数组的长度计算中位数
   * 示例1：
   * 输入：nums1 = [1,2], nums2 = [3,4]
   * 合并后：[1,2,3,4]
   * 总长度4为偶数，中位数 = (2+3)/2 = 2.5
   * 示例2：
   * 输入：nums1 = [1,3], nums2 = [2]
   * 合并后：[1,2,3]
   * 总长度3为奇数，中位数 = 2
   * 解法一：时间复杂度不是最优解，但是是最清晰的写法
   * 时间复杂度：O(m+n)，需要遍历两个数组
   * 空间复杂度：O(m+n)，需要额外空间存储合并后的数组
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // 时间复杂度是O(m+n)，不是最优解，但是是最容易理解的
    int m = nums1.length;
    int n = nums2.length;
    // 1、合并成一个nums数组
    int[] nums = new int[m + n];
    int i = 0, j = 0, k = 0;
    while (i < m && j < n) {
      nums[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
    }
    while (i < m) {
      nums[k++] = nums1[i++];
    }

    while (j < n) {
      nums[k++] = nums2[j++];
    }

    // 2、找中位数
    int mid = (m + n) / 2;
    int even = (m + n) % 2;
    if (even == 0) {
      return (nums[mid] + nums[mid - 1]) / 2d;
    }
    return nums[mid];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums1 = {4};
    int[] nums2 = {1, 2, 3, 5};
    System.out.println(solution.findMedianSortedArrays(nums1, nums2));
  }


}
