package com.ssl.note.leetcode.编号刷题.LC300_最长递增子序列;

public class Solution1 {

  /**
   * 最长递增子序列
   * 输入：nums = [10,9,2,5,3,7,101,18]
   * 输出：4
   * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4
   * 方法：贪心+二分查找优化，
   * 时间复杂度：O(nlogn)
   */
  public int lengthOfLIS(int[] nums) {
    int m = nums.length;
    int[] tail = new int[m];
    int len = 0;

    for (int num : nums) {
      int index = find(tail, 0, len, num);
      tail[index] = num;

      if (index == len) {
        len++;
      }
    }

    return len;
  }

  // 查找第一个>=v的坐标，[l,r)范围，找不到的时候，返回right，不需要特判
  private int find(int[] tail, int left, int right, int v) {
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (tail[mid] < v) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }

  // 查找第一个>=v的坐标，[l,r]范围，返回-1需要特判
  private int find1(int[] nums, int l, int r, int v) {
    int left = l;
    int right = r;

    int res = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= v) {
        res = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] arr1 = {10, 9, 2, 5, 3, 7, 101, 18};
    //   tails = {2, 3, 7, 18, 0, 0, 0, 0};
    System.out.println(solution.lengthOfLIS(arr1));
  }
}