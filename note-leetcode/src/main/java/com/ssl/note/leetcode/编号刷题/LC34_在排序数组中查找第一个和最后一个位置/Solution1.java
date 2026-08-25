package com.ssl.note.leetcode.编号刷题.LC34_在排序数组中查找第一个和最后一个位置;

import java.util.Arrays;

public class Solution1 {

    /**
     * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
     */
    public int[] searchRange(int[] nums, int target) {
        return new int[]{getOne(nums, target), getLast(nums, target)};
    }

    /**
     * >=的第一个数
     * 特判：l是否越界 或者 这个位置不是它
     */
    private int getOne(int[] nums, int t) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= t) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (l == nums.length || nums[l] != t) {
            return -1;
        }

        return l;
    }

    /**
     * <=t的最后一个数
     * 特判：l是否越界 或者 这个位置不是它
     */
    private int getLast(int[] nums, int t) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] > t) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (l - 1 < 0 || nums[l - 1] != t) {
            return -1;
        }
        return l - 1;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        int[] nums = {1};
        int t = 1;
        System.out.println(Arrays.toString(solution.searchRange(nums, t)));
    }
}
