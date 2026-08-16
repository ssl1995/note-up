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
    if (nums == null || nums.length == 0) {
      return 0;
    }
    // 贪心思路：相同长度的递增子序列，末尾元素越小越好。因为越小的元素越容易追加递增的数
    // 维护一个递增序列 tail，其中 tail[i] 表示长度为 i+1 的最长递增子序列的最小末尾元素
    int[] tail = new int[nums.length];
    // len是最长递增子序列长度，[0,len-1]表示已经放好了,len位置表示待放
    int len = 0;

    for (int num : nums) {
      // 查找第一个>=v的坐标，[l,r]范围，返回-1需要特判
      int index = binarySearch(nums, 0, len, num);
      if (index == -1) {
        index = len;
      }
      // 查找第一个>=v的坐标，[l,r)范围，不需要特判
//      int index = binarySearch1(nums, 0, len, num);

      tail[index] = num;

      // 比所有尾数都大，len++
      if (index == len) {
        len++;
      }
    }

    return len;
  }

  // 查找第一个>=v的坐标，[l,r]范围，返回-1需要特判
  private int binarySearch(int[] nums, int l, int r, int v) {
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

  // 查找第一个>=v的坐标，[l,r)范围，不需要特判
  private int binarySearch1(int[] tail, int l, int r, int v) {
    int left = l;
    int right = r;

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


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] arr1 = {10, 9, 2, 5, 3, 7, 101, 18};
    //   tails = {2, 3, 7, 18, 0, 0, 0, 0};
    System.out.println(solution.lengthOfLIS(arr1));
  }
}