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
    int nums1Left = 0, nums1Right = m;

    // num1=[4],nums2=[1,2,3,5],m=1,n=4
    while (nums1Left <= nums1Right) {
      // nums1:  [ 1,  3,  5 | 7,  9 ]
      // nums2:  [ 2,  4,  6 | 8, 10 ]
      //            L 区域      R 区域
      // i=0
      int i = (nums1Left + nums1Right) / 2;
      // j=3
      // 总长为奇数时，多一个
      int j = (m + n + 1) / 2 - i;

      // nums1LeftMax=MIN_VALUE、nums1RightMin=4
      int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
      int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
      // nums2LeftMax=3、nums2RightMin=5
      int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
      int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

      // max(nums1[i-1], nums2[j-1]) <= min(nums1[i], nums2[j])
      // 由于nums1[i-1]<nums1[i]和 nums2[j-1]<nums2[j]天然成立，所以只需要交叉比较就行
      if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
        // 划分正确
        if ((m + n) % 2 == 0) {
          return (Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin)) / 2d;
        } else {
          return Math.max(nums1LeftMax, nums2LeftMax);
        }
      }// 以下2个分支确定交叉点i和j的位置
      else if (nums1LeftMax > nums2RightMin) {
        // nums1左边元素没有过大，把它从左边挪到右边，i减少
        nums1Right = i - 1;
      } else {
        // num2左边元素没有过大=nums1左边元素太小，i增大
        nums1Left = i + 1;
      }
    }
    return -1d;
  }

  public static void main(String[] args) {
    Solution1 solution2 = new Solution1();
    int[] nums1 = {1, 2};
    int[] nums2 = {3, 4, 5};
    System.out.println(solution2.findMedianSortedArrays(nums1, nums2));
  }
}
