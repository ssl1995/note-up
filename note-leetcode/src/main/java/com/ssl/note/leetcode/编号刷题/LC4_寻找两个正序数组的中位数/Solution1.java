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
    // m是短数组长度，n是长数组长度
    int m = nums1.length;
    int n = nums2.length;
    // 保证nums1是较短的数组，在它上面二分划分点i,j就不会越界
    if (m > n) {
      return findMedianSortedArrays(nums2, nums1);
    }

    /**
     * 将两个数组合并后的全局左半和全局右半分开，满足2个条件
     * 1、左半所有元素<=右半所有元素 ，判断a <= d && c >= d
     * 2、左半的元素个数=(m+n+1)/2，二分划分i,j
     * 这样的天然就能快速算出中位数
     */
    int left = 0, right = m;
    while (left <= right) {
      // nums1:  [4]
      // nums2:  [1,2,3,5]
      // 2、i划分短数组，j自动确定位置
      // i 表示 nums1 左边拿了i个元素，由于总数恒定，j就能推出来
      int i = (left + right) / 2;
      // i + j = (m+n+1)/2，+1 让左半在总长为奇数时多拿一个，奇偶统一处理
      int j = (m + n + 1) / 2 - i;

      // 3、防止越界.期望公式：nums1[i-1] ≤ nums2[j] 且 nums2[j-1] ≤ nums1[i]
      int a = getNum(nums1, i - 1);
      int b = getNum(nums1, i);
      int c = getNum(nums2, j - 1);
      int d = getNum(nums2, j);

      /**
       * 4、交叉合法就收工
       * nums1: [... a | b ...]    a = nums1左半最大值，b = nums1右半最小值
       * nums2: [... c | d ...]    c = nums2左半最大值，d = nums2右半最小值
       * 全局左半 = nums1左半 ∪ nums2左半
       * 全局右半 = nums1右半 ∪ nums2右半
       */
      if (a <= d && c <= b) {
        boolean isEven = (m + n) % 2 == 0;
        if (isEven) {// 偶数
          // 偶数：左边取最小，右边取最大，除2返回
          int num1 = Math.max(a, c);
          int num2 = Math.min(b, d);
          return (num1 + num2) / 2d;
        } else {
          // 奇数:左边是满载的，左边最小就是中位数
          return Math.max(a, c);
        }
      }// 5、谁大谁向左靠，以下2个分支调整切口i的位置
      else if (a > d) {
        // nums1 左边拿多了（有元素比 nums2 右边还大），把它从左边挪回右边，i 左移
        right = i - 1;
      } else {
        // nums2LeftMax > nums1RightMin：nums1 左边拿少了，i 右移
        left = i + 1;
      }
    }
    return -1d;
  }

  private int getNum(int[] nums, int index) {
    if (index < 0) {
      return Integer.MIN_VALUE;
    }
    if (index > nums.length - 1) {
      return Integer.MAX_VALUE;
    }
    return nums[index];
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 2};
    int[] nums2 = {-10, -9, -8};
    Solution1 solution = new Solution1();
    System.out.println(solution.findMedianSortedArrays(nums1, nums2));
  }
}
