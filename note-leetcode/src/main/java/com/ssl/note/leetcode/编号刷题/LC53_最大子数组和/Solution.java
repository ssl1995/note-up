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
    if (nums == null || nums.length == 0) {
      return 0;
    }
    // 前缀和方法：sum(i,j) = prefix[j] - prefix[i-1]
    // 要想最大，就得使前者尽量大，后者尽量小
    int n = nums.length;
    // 当前前缀和
    int preSum = 0;
    // 历史最小前缀和
    int minPreSum = 0;

    // 最大连续子数组
    int res = Integer.MIN_VALUE;

    for (int num : nums) {
      // 1、先更新前缀和
      preSum += num;
      // 2、重点，因为是pre[i-1],所以先更新结果
      res = Math.max(res, preSum - minPreSum);
      // 3、最后更新历史最小前缀和
      minPreSum = Math.min(preSum, minPreSum);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(solution.maxSubArray(nums));
  }
}
