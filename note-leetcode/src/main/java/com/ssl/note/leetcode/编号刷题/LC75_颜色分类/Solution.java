package com.ssl.note.leetcode.编号刷题.LC75_颜色分类;

import java.util.Arrays;

public class Solution {

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
    // 荷兰国旗解法:
    // 如果初始化left=0,right=n-1：
    // [0,left),[left,i,right],(right,n-1]
    int left = 0;
    int right = nums.length - 1;
    int i = 0;

    while (i <= right) {
      if (nums[i] == 0) {
        // 见0换左边
        swap(nums, left++, i++);
      } else if (nums[i] == 1) {
        // 见1直接走
        i++;
      } else if (nums[i] == 2) {
        // 见2换右边
        // cur不动，从right位置换过来的数还没有检查
        swap(nums, right--, i);
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
    Solution solution = new Solution();
    int[] nums = {2, 0, 2, 1, 1, 0};
    solution.sortColors(nums);
    System.out.println(Arrays.toString(nums));
  }
}
