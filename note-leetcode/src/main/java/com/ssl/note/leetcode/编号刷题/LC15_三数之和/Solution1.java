package com.ssl.note.leetcode.编号刷题.LC15_三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

  /**
   * 三数之和
   * 是否存在3个数使得a+b+c=0
   * 注意：答案中不可以包含重复的三元组
   * 输入：nums = [-1,0,1,2,-1,-4]
   * 输出：[[-1,-1,2],[-1,0,1]]
   */
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length < 3) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    // 1.排序
    Arrays.sort(nums);
    int n = nums.length;
    for (int i = 0; i < n - 2; i++) {
      // 2.加速1：剪枝：最小值大于0，不可能有解
      if (nums[i] > 0) {
        break;
      }
      // 3.易错/加速2：重复的第一个数保留，下面会计算，后续重复的跳过
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      // 4.双指针
      int l = i + 1;
      int r = n - 1;
      // 易错：本题left和right不能相同
      while (l < r) {
        int sum = nums[i] + nums[l] + nums[r];
        if (sum < 0) {
          l++;
        } else if (sum > 0) {
          r--;
        } else {
          // 5.找到答案
          res.add(Arrays.asList(nums[i], nums[l], nums[r]));
          // 要求：答案中不可以包含重复的三元组。
          // 6.非加速，但需要跳过重复值
          // 例如；[0,0,0,0] 固定第一个0后，双指针返回第一个(0,0,0)后，不要再重复返回了
          while (l + 1 < n && nums[l] == nums[l + 1]) {
            l++;
          }
          while (r - 1 >= 0 && nums[r] == nums[r - 1]) {
            r--;
          }
          // 易错:还需要再次移动双指针
          l++;
          r--;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {-1, 0, 1, 2, -1, -4};
    System.out.println(solution.threeSum(nums));
  }

}
