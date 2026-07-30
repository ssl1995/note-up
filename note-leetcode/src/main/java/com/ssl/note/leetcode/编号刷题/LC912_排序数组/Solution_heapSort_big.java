package com.ssl.note.leetcode.编号刷题.LC912_排序数组;

import com.ssl.note.common.utils.ArrayUtil;

public class Solution_heapSort_big {

  /**
   * LC912_排序数组
   * 大根堆排序
   */
  public int[] sortArray(int[] nums) {
    int n = nums.length;
    // 1、建堆：从最后一个节点开始，依次向下调整
    for (int i = n - 1; i >= 0; i--) {
      heapify(nums, i, n);
    }

    // 2、大根堆结构，每次将0维持与末尾交换
    int size = n;
    while (--size > 0) {
      swap(nums, 0, size);
      heapify(nums, 0, size);
    }

    return nums;
  }

  /**
   * 大根堆结构
   * i
   * 2i+1   2i+2
   */
  private void heapify(int[] nums, int i, int size) {
    int left = 2 * i + 1;
    while (left < size) {
      // 2个孩子最大和父亲哪个大
      int best = left + 1 < size && nums[left + 1] > nums[left] ? left + 1 : left;
      best = nums[best] > nums[i] ? best : i;
      // 如果最大的孩子是自己，就停止
      if (best == i) {
        break;
      }
      swap(nums, i, best);
      // i往最大的孩子走
      i = best;
      // 更新下一个左孩子
      left = 2 * i + 1;
    }
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    Solution_heapSort_big solution = new Solution_heapSort_big();
    int times = 1001;
    while (times-- > 0) {
      int[] nums = {5, 1, 1, 2, 0, 0};
      int[] right = {0, 0, 1, 1, 2, 5};
      solution.sortArray(nums);
      boolean equals = ArrayUtil.isEquals(nums, right);
      if (!equals) {
        System.out.println("error");
        break;
      }
    }
  }


}
