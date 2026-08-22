package com.ssl.note.leetcode.编号刷题.LC53_最大子数组和;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:21 AM
 * @description
 */
public class Solution {

  /**
   * 最大子数组和
   * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
   * 输出：6
   * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
   */
  public int maxSubArray(int[] nums) {
    // 前缀和方法：sum(i,j) = prefix[j] - prefix[i-1]
    // 要想最大，就得使前者尽量大，后者尽量小
    // 当前前缀和
    int sum = 0;
    // 历史最小前缀和
    int preMinSum = 0;

    // 最大连续子数组
    int res = Integer.MIN_VALUE;

    for (int num : nums) {
      // 1、先更新前缀和
      sum += num;
      // 2、重点，因为是pre[i-1],所以先更新结果
      res = Math.max(res, sum - preMinSum);
      // 3、最后更新历史最小前缀和
      preMinSum = Math.min(sum, preMinSum);
    }

    return res;
  }

  // 第二种写法
  public int maxSubArray1(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return nums[0];
    }
    int sum = 0;
    int preMinSum = 0;
    int res = Integer.MIN_VALUE;

    for (int num : nums) {
      preMinSum = Math.min(preMinSum, sum);
      sum += num;
      res = Math.max(res, sum - preMinSum);
    }

    return res;
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(solution.maxSubArray(nums));
  }
}
