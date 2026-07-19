package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Random;

public class Solution1 {

  /**
   * 数组中第k大的数
   * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   * 输出: 4
   */
  public int findKthLargest(int[] nums, int k) {
    // 计算目标位置：第k大元素在排序数组中的索引
    int target = nums.length - k;
    // 调用三路快速选择函数
    return quickSelect(nums, 0, nums.length - 1, target);
  }

  private int quickSelect(int[] nums, int left, int right, int target) {
    int[] bound = partition(nums, left, right);
    int lt = bound[0];
    int gt = bound[1];

    // [left,lt],[lt+1,gt],[gt+1,right]
    if (target <= lt) {
      // target在小于区，只搜左边
      return quickSelect(nums, left, lt, target);
    } else if (target > gt) {
      // target在大于区，只搜右边（gt已归位必须排除，用gt+1）
      return quickSelect(nums, gt + 1, right, target);
    } else {
      // lt < target <= gt，target落在等于区，pivot就是答案
      return nums[target];
    }
  }

  private int[] partition(int[] nums, int left, int right) {
    // 随机选择pivot:防止有序数组退化O(n²)
    int random = new Random().nextInt(right - left + 1) + left;
    swap(nums, random, right);

    // 三路快排while循环结束后：
    // [left    lt] [lt+1   i-1] [i   gt-1] [gt  right-1] [right]
    //  < pivot      = pivot       未处理区     > pivot     pivot(人质)
    int pivot = nums[right];  // 选择最右元素作为pivot
    int lt = left - 1;        // 小于pivot的右边界（初始为left-1）
    int gt = right;           // 大于pivot的左边界（初始为right）=未处理区域的右边界
    int i = left;             // 当前遍历位置（从left开始）
    // i只能扫描未处理区域，一旦越过 gt，就会去动已经归位的元素，分区立刻乱掉
    while (i < gt) {
      if (nums[i] < pivot) {
        // 当前元素小于pivot，放到左侧
        swap(nums, ++lt, i++);
      } else if (nums[i] > pivot) {
        // 当前元素大于pivot，放到右侧
        swap(nums, --gt, i);
        // 注意：这里i不增加，因为交换过来的元素还没检查
      } else {
        // 当前元素等于pivot，保持在中间
        i++;
      }
    }
    // 将gt归位到最终位置
    swap(nums, gt, right);

    return new int[]{lt, gt};
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
    int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
    int k = 4;
    int res = 4;
    Solution1 solution = new Solution1();
    System.out.println(solution.findKthLargest(nums, k) == res);
  }
}
