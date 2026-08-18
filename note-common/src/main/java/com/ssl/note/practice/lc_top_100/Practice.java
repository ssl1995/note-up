package com.ssl.note.practice.lc_top_100;

public class Practice {

  /**
   * 1. 16-多维动态规划 LC 62 不同路径
   * 2. 15-动态规划 LC 152 乘积最大子数组
   */
  public int maxArea(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    int l = 0;
    int r = height.length - 1;
    int res = 0;
    while (l < r) {
      int temp = (r - l) * Math.min(height[l], height[r]);
      res = Math.max(res, temp);

      if (height[l] < height[r]) {
        l++;
      } else {
        r--;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Practice solution = new Practice();
    int[] nums = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    System.out.println(solution.maxArea(nums));
  }
}
