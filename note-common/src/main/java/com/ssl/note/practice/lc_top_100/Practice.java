package com.ssl.note.practice.lc_top_100;

public class Practice {

  // 1. 02-双指针 LC 42 接雨水
  // 2. 09-图论 LC 207 课程表
  // 3. 08-二叉树 LC 114 二叉树展开为链表
  public int trap(int[] height) {
    int n = height.length;
    int[] left = new int[n];
    for (int i = 1; i < n; i++) {
      left[i] = Math.max(left[i - 1], height[i - 1]);
    }

    int[] right = new int[n];
    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], height[i + 1]);
    }

    int res = 0;
    for (int i = 0, min = 0; i < n; i++) {
      min = Math.min(left[i], right[i]);
      if (height[i] < min) {
        res += min - height[i];
      }
    }

    return res;
  }
}
