package com.ssl.note.leetcode.编号刷题.LC719_找出第k小的数对距离;

import java.util.Arrays;

public class Solution {

  /**
   * LC719_找出第k小的数对距离
   * 对距离 = 两数绝对差。给定数组 nums 和 k，返回所有数对距离中第 k 小的距离
   * 输入：nums = [1,3,1], k = 1
   * 输出：0
   * 解释：数对和对应的距离如下：
   * (1,3) -> 2
   * (1,1) -> 0
   * (3,1) -> 2
   * 距离第 1 小的数对是 (1,1) ，距离为 0 。
   */
  public int smallestDistancePair(int[] nums, int k) {
    if (nums == null) {
      return 0;
    }
    // 排序后，最小距离=0，最大距离=最大值-最小值
    Arrays.sort(nums);
    // 二分答案：在距离范围 [0, max-min] 上二分
    int l = 0, r = nums[nums.length - 1] - nums[0];
    int res = 0;
    while (l <= r) {
      int m = l + (r - l) / 2;
      // arr中任意两数的差值 <= m 的数字配对，有几对？
      // 距离m越大 -> 满足条件的数对越多（单调性，所以可二分）
      int x = f(nums, m);
      if (x >= k) {
        // 有至少k对距离<=m，说明第k小的距离 <= m，m是候选答案
        // 继续往更小的距离试探，找满足条件的最小m
        res = m;
        r = m - 1;
      } else {
        // 距离<=m的数对不足k对，第k小的距离一定比m大
        l = m + 1;
      }
    }

    return res;
  }

  // arr中任意两数的差值 <= m 的数字配对，有几对？
  // 滑动窗口/双指针：O(n)
  private int f(int[] nums, int limit) {
    int res = 0;
    for (int i = 0, j = 0; i < nums.length; i++) {
      // 固定左端点i，j单调右移（不回退），找到最远满足 nums[j]-nums[i]<=limit 的j
      // (i,j)、(i,j+1)满足
      while (j + 1 < nums.length && nums[j + 1] - nums[i] <= limit) {
        j++;
      }
      // 以nums[i]为较小值的合法数对有 (i,i+1)...(i,j)，共 j-i 对
      res += j - i;
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 3, 1};
    int k = 1;
    System.out.println(solution.smallestDistancePair(nums, k));
  }

}
