package com.ssl.note.leetcode.编号刷题.LC75_颜色分类;

import java.util.Arrays;

public class Solution1 {

  /**
   * 颜色分类：0-红，1-白，2-蓝色
   * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列
   * 输入：nums = [2,0,2,1,1,0]
   * 输出：[0,0,1,1,2,2]
   */
  public void sortColors(int[] nums) {
    if (nums == null) {
      return;
    }
    // 荷兰国旗解法
    // [0, left)      全是 0
    // [left, cur)    全是 1
    // [cur, right]   未处理区
    // (right, end]   全是 2
    // 左闭右开：left 左边全是 0，cur 左边全是 1，right 右边全是 2。
    int left = 0;
    int cur = 0;
    int right = nums.length - 1;

    while (cur <= right) {
      if (nums[cur] == 0) {
        // 见0换左边
        swap(nums, cur, left);
        left++;
        cur++;
      } else if (nums[cur] == 1) {
        // 见1直接走
        cur++;
      } else if (nums[cur] == 2) {
        // 见2换右边
        swap(nums, cur, right);
        right--;
        // cur不动，从right位置换过来的数还没有检查
      }
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
    Solution1 solution = new Solution1();
    int[] nums = {2, 0, 2, 1, 1, 0};
    solution.sortColors(nums);
    System.out.println(Arrays.toString(nums));
  }
}
