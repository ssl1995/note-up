package com.ssl.note.leetcode.编号刷题.LC4_寻找两个正序数组的中位数;


public class Solution1 {

  /**
   * 寻找两个正序数组的中位数
   * 解法二：二划分法（最优解）
   * 时间复杂度：O(log(min(m,n)))，在短数组上二分
   * 空间复杂度：O(1)
   * 1.回到定义：中位数的本质是什么？它把全体数据分成两半——左半所有元素 ≤ 右半所有元素，且左半人数固定 = (m+n+1)/2。
   * 2.重新表述问题：所以不是去找某个数，而是在 nums1 上切一刀 i、nums2 上切一刀 j，使两个左半拼起来恰好是"全局左半"。
   * 3.发现只有一个自由度：人数守恒 i + j = (m+n+1)/2，所以 i 一旦定了，j 直接被算出来（j = half - i）——需要搜索的变量从两个降到一个。
   * 4.合法性只需要"交叉检查"：因为每个数组自身有序（题目白送的），同侧必然合法，只需验证交叉条件： nums1[i-1] ≤ nums2[j] 且 nums2[j-1] ≤ nums1[i]
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // 1、短数组上切一刀
    // 保证nums1是较短的数组，在它上面二分划分点i,j就不会越界
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }
    // m是短数组长度，n是长数组长度
    int m = nums1.length;
    int n = nums2.length;
    boolean isEven = (m + n) % 2 == 0;
    // i ∈ [0, m]，存在型二分，闭区间 [left, right]
    int nums1Left = 0, nums1Right = m;

    while (nums1Left <= nums1Right) {
      // nums1:  [4]
      // nums2:  [1,2,3,5]
      // 2、i划分短数组，j自动确定位置
      // i 表示 nums1 左边拿了i个元素，由于总数恒定，j就能推出来
      int i = (nums1Left + nums1Right) / 2;
      // i + j = (m+n+1)/2，+1 让左半在总长为奇数时多拿一个，奇偶统一处理
      int j = (m + n + 1) / 2 - i;

      // 3、防止越界
      int nums1LeftMax = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
      int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];
      int nums2LeftMax = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
      int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

      // 4、交叉合法就收工
      // nums1: [ | 4 ]        贡献左半 {}，右半 {4}
      // nums2: [ 1, 2, 3 | 5 ] 贡献左半 {1,2,3}，右半 {5}
      // 全局左半 = {1,2,3}（3 个 = (5+1)/2 ✓ 人数对，公式预付）
      // 全局右半 = {4,5}
      // nums1LeftMax=-∞,nums1RightMin=4;nums2LeftMax=3,nums2RightMin=5
      // -∞<= 5,3<=4
      if (nums1LeftMax <= nums2RightMin && nums1RightMin >= nums2LeftMax) {
        // 划分正确：左半永远满载，奇数答案在左，偶数跨线取均
        if (isEven) {// 偶数
          int num1 = Math.max(nums1LeftMax, nums2LeftMax);
          int num2 = Math.min(nums1RightMin, nums2RightMin);
          return (num1 + num2) / 2d;
        } else {
          // 奇数
          return Math.max(nums1LeftMax, nums2LeftMax);
        }
      }// 5、谁大谁向左靠，以下2个分支调整切口 i 的位置
      else if (nums1LeftMax > nums2RightMin) {
        // nums1 左边拿多了（有元素比 nums2 右边还大），把它从左边挪回右边，i 左移
        nums1Right = i - 1;
      } else {
        // nums2LeftMax > nums1RightMin：nums1 左边拿少了，i 右移
        nums1Left = i + 1;
      }
    }
    return -1d;
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 2};
    int[] nums2 = {-10, -9, -8};
    Solution1 solution = new Solution1();
    System.out.println(solution.findMedianSortedArrays(nums1, nums2));
  }
}
