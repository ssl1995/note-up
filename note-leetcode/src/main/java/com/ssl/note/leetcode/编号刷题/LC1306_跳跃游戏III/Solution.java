package com.ssl.note.leetcode.编号刷题.LC1306_跳跃游戏III;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

  /**
   * LC1306_跳跃游戏III
   * 给定一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
   * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
   * 注意，不管是什么情况下，你都无法跳到数组之外。
   * 示例 1：
   * 输入：arr = [4,2,3,0,3,1,2], start = 5
   * 输出：true
   * 解释：
   * 到达值为 0 的下标 3 有以下可能方案：
   * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
   * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
   */
  public boolean canReach(int[] arr, int start) {
    int n = arr.length;
    // 记录访问过的下标，防止在两个位置之间来回跳动造成死循环
    boolean[] visited = new boolean[n];
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int i = queue.poll();
      // 只要到达任意值为0的下标，就返回true
      if (arr[i] == 0) {
        return true;
      }
      int step = arr[i];
      // 向右跳
      int right = i + step;
      if (right < n && !visited[right]) {
        visited[right] = true;
        queue.offer(right);
      }
      // 向左跳
      int left = i - step;
      if (left >= 0 && !visited[left]) {
        visited[left] = true;
        queue.offer(left);
      }
    }
    // 队列已空，说明所有可达位置都访问过，仍未找到值为0的下标
    return false;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] arr = {4, 2, 3, 0, 3, 1, 2};
    System.out.println(solution.canReach(arr, 5)); // true
    System.out.println(solution.canReach(arr, 0)); // false
    System.out.println(solution.canReach(arr, 3)); // true
  }
}
