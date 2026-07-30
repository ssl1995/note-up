package com.ssl.note.leetcode.编号刷题.LC912_排序数组;

import com.ssl.note.common.utils.ArrayUtil;

public class Solution_heapSort_small {

  /**
   * LC912_排序数组
   * 堆排序：小根堆写法
   * 思路：
   * 1. 把整个数组调整成小根堆（堆顶是最小值）
   * 2. 每次把堆顶最小值交换到堆的末尾，堆大小减1，再从堆顶向下 heapify
   * 这样从后往前依次排好的是 最小、次小... 即整个数组变成【降序】
   * 3. 最后把数组反转，得到【升序】
   */
  public int[] sortArray(int[] nums) {
    int n = nums.length;
    // 1、建堆：从最后一个节点开始，依次向下调整
    for (int i = n - 1; i >= 0; i--) {
      heapify(nums, i, n);
    }

    // 2、0位置是最小值，只能放到堆顶，调整堆
    int size = n;
    while (--size >= 0) {
      // 堆顶与堆的最后一个元素交换，堆大小减1
      swap(nums, 0, size);
      // 从堆顶开始向下调整，恢复小根堆
      heapify(nums, 0, size);
    }

    // 3、堆排序的核心操作是：每次把堆顶元素交换到堆的末尾，然后堆大小减1
    // 小根堆最后需要reverse数组
    reverse(nums);

    return nums;
  }

  /**
   * 小根堆结构
   * i
   * 2i+1   2i+2
   */
  private void heapify(int[] nums, int i, int size) {
    int left = 2 * i + 1;
    while (left < size) {
      // 2个孩子中最小的那个，和父亲比哪个小
      int min = left + 1 < size && nums[left + 1] < nums[left] ? left + 1 : left;
      min = nums[min] < nums[i] ? min : i;
      // 如果最小的就是自己，就停止
      if (min == i) {
        break;
      }
      swap(nums, i, min);
      // i往最小的孩子走
      i = min;
      // 更新下一个左孩子
      left = 2 * i + 1;
    }
  }

  private void reverse(int[] nums) {
    int l = 0;
    int r = nums.length - 1;
    while (l < r) {
      swap(nums, l++, r--);
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
    Solution_heapSort_small solution = new Solution_heapSort_small();
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
