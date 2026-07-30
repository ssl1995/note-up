package com.ssl.note.leetcode.编号刷题.LC493_翻转对;

public class Solution {

  /**
   * LC493_翻转对
   * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
   * 你需要返回给定数组中的重要翻转对的数量。
   * 示例 1:
   * 输入: [1,3,2,3,1]
   * 输出: 2
   */
  public int reversePairs(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    return mergeSort(nums, 0, nums.length - 1);
  }

  private int mergeSort(int[] nums, int left, int right) {
    if (left == right) {
      return 0;
    }
    int mid = left + (right - left) / 2;
    int sum1 = mergeSort(nums, left, mid);
    int sum2 = mergeSort(nums, mid + 1, right);
    int sum3 = merge(nums, left, mid, right);

    return sum1 + sum2 + sum3;
  }

  private int merge(int[] nums, int l, int m, int r) {
    // 统计
    int res = 0;

    int i = l;
    int j = m + 1;

    while (i <= m) {
      // 错误：(long)nums[i] >  (long)(2 * nums[j]),强转只作用于括号内已经溢出的结果，无法挽回精度
      // 正确：(long)nums[i] > 2 * (long) nums[j] --> nums[i] > 2L * nums[j]
      while (j <= r && nums[i] > 2L * nums[j]) {
        j++;
      }
      i++;
      res += j - (m + 1);
    }

    int[] help = new int[r - l + 1];
    int index = 0;
    int a = l;
    int b = m + 1;
    while (a <= m && b <= r) {
      help[index++] = (nums[a] < nums[b]) ? nums[a++] : nums[b++];
    }
    while (a <= m) {
      help[index++] = nums[a++];
    }
    while (b <= r) {
      help[index++] = nums[b++];
    }
    System.arraycopy(help, 0, nums, l, r - l + 1);

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
    System.out.println(solution.reversePairs(nums));
  }
}
