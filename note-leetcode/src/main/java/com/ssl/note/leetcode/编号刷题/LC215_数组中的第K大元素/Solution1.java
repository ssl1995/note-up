package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Random;

public class Solution1 {

  /**
   * 数组中第k大的数
   * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   * 输出: 4
   */
  public int findKthLargest(int[] nums, int k) {
    // 第1大的数：n-1
    // 第k大的数：n-k
    int target = nums.length - k;
    return quickSelect(nums, 0, nums.length - 1, target);
  }

  private int quickSelect(int[] nums, int l, int r, int target) {
    // 循环不变式：target 始终在 [left, right] 区间内
    while (l <= r) {
      // [l,r]闭区间写法，我还是习惯这种写法
      int[] partition = partition(nums, l, r);
      int a = partition[0];
      int b = partition[1];
      // a、b、t都是坐标值，直接比较
      if (a <= target && target <= b) {
        return nums[target];
      } else if (target < a) {
        r = a - 1;
      } else {
        l = b + 1;
      }
    }
    // 理论上不会走到这里，因为题目保证k有效
    return -1;
  }

  // 三路快排
  private int[] partition(int[] nums, int l, int right) {
    // 三路快排，当重复元素过多时，高效处理
    // 1、交换随机值，防止出现基准=最大值/最小值最坏情况
    int random = new Random().nextInt(right - l + 1) + l;
    int pivot = nums[random];

    // 2、扫描右边界是 b 而不是 r，i > b 时终止
    // [0,a-1] [a,b] [b+1,r]
    int a = l, b = right, i = l;
    while (i <= b) {
      if (nums[i] < pivot) {
        swap(nums, i++, a++);
      } else if (nums[i] > pivot) {
        swap(nums, i, b--);
      } else {
        i++;
      }
    }
    // 闭区间：直接返回a和b，而不是a-1和b-1
    return new int[]{a, b};
  }

  private void swap(int[] nums, int i, int j) {
    // 避免自我交换
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 5, 6, 4};
    int k = 2;
    int res = 5;
    Solution1 solution = new Solution1();
    System.out.println(solution.findKthLargest(nums, k) == res);
  }
}
