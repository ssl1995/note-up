package com.ssl.note.practice.lc_top_100;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice {

  /**
   * 1. 16-多维动态规划 LC 62 不同路径
   * 2. 15-动态规划 LC 152 乘积最大子数组
   */
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    }
    Arrays.sort(nums);
    int n = nums.length;
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (nums[i] > 0) {
        break;
      }
      if (i > 0 && nums[i - 1] == nums[i]) {
        continue;
      }
      int left = i + 1;
      int right = n - 1;
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum < 0) {
          left++;
        } else if (sum > 0) {
          right--;
        } else {
          res.add(Arrays.asList(nums[i], nums[left], nums[right]));

          while (left + 1 < n && nums[left + 1] == nums[left]) {
            left++;
          }

          while (right - 1 >= 0 && nums[right - 1] == nums[right]) {
            right--;
          }

          left++;
          right--;
        }
      }
    }

    return res;
  }


  public static void main(String[] args) {
    Practice practice = new Practice();

  }
}
