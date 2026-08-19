package com.ssl.note.leetcode.编号刷题.LC875_爱吃香蕉的珂珂;

public class Solution {

  /**
   * LC875_爱吃香蕉的珂珂
   * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
   * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，
   * 她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
   * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
   * 示例：
   * 输入：piles = [30,11,23,4,20], h = 5
   * 输出：30
   */
  public int minEatingSpeed(int[] piles, int h) {
    if (piles == null) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    for (int num : piles) {
      max = Math.max(max, num);
    }

    int l = 1;
    int r = max;

    int res = 0;
    while (l <= r) {
      // 假设的速度
      int mid = l + (r - l) / 2;
      // 向上取整，这里返回值是long不会越界
      long needH = f(piles, mid);
      // 当前速度mid可行,能在h小时内吃完
      // 求最小速度，就右移
      if (needH <= h) {
        res = mid;
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }

    return res;
  }

  // 计算speed速度下总共需要的小时数
  // 注意，返回值是long，否则会需要的h会越界出现负数
  private long f(int[] nums, int speed) {
    long res = 0;
    for (int num : nums) {
      // 非负数向上取整
      res += (num + speed - 1) / speed;
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {805306368, 805306368, 805306368};
    int h = 1000000000;
    System.out.println(solution.minEatingSpeed(nums, h));
  }
}
