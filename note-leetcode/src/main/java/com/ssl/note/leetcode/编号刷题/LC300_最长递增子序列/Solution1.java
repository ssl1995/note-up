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
    int n = nums.length;
    // 贪心思维:让短序列的末尾尽可能小，才能给长序列腾出生长空间
    // tail[i]表示长度i+1的递增序列最小末尾元素
    int[] tail = new int[n];
    int len = 0;

    for (int num : nums) {
      // 要让相同长度下末尾元素尽可能小，就在tail中找>=num的位置，不存在返回右边界size
      int index = getFirstGE(tail, 0, len, num);
      // 如果是边界，就更新边界长度
      if (index == len) {
        len++;
      }
      // 无论是否是边界，都更新tail的值
      tail[index] = num;
    }

    return len;
  }

  // 查找第一个>=v的坐标，[l,r)范围
  // 也可以理解为插入t的位置下标,不存在插入右边界r
  private int getFirstGE(int[] nums, int l, int r, int t) {
    // 左闭右开
    while (l < r) {
      int mid = l + (r - l) / 2;
      if (nums[mid] >= t) {
        r = mid;
      } else {
        l = mid + 1;
      }
    }
    return l;
  }

  // 优化写法
  public int lengthOfLIS1(int[] nums) {
    int m = nums.length;
    int[] tail = new int[m];
    int size = 0;

    for (int num : nums) {
      int index = getFirstGE(tail, 0, size, num);
      tail[index] = num;

      if (index == size) {
        size++;
      }
    }

    return size;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] arr1 = {10, 9, 2, 5, 3, 7, 101, 18};
    //   tails = {2, 3, 7, 18, 0, 0, 0, 0};
    System.out.println(solution.lengthOfLIS(arr1));
  }
}