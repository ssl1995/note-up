package com.ssl.note.leetcode.编号刷题.LC300_最长递增子序列;

public class Test {

  public int lengthOfLIS(int[] nums) {
    int n = nums.length;
    // tail[i]表示长度为 i+1 的最长递增子序列的最小末尾元素。
    int[] tail = new int[n];
    int len = 0;

    for (int i = 0; i < n; i++) {
      // 新来一个数,在tail中找第一个>=它的下标,替换为它
      int index = getMin(tail, 0, len, nums[i]);
      tail[index] = nums[i];

      if (index == len) {
        len++;
      }
    }

    return len;
  }

  private int getMin(int[] nums, int l, int r, int t) {
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

  public static void main(String[] args) {
    Test test = new Test();
    int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
    int res = 4;
    System.out.println(test.lengthOfLIS(nums) == res);
  }
}
