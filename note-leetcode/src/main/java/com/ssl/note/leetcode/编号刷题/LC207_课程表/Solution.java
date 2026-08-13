package com.ssl.note.leetcode.编号刷题.LC207_课程表;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author SongShengLin
 * @date 2022/2/22 8:04 AM
 * @description
 */
public class Solution {
  /**
   * 课程表
   * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
   * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites给出，
   * 其中prerequisites[i] = [ai, bi],表示如果要学习课程ai，则必须先学习课程bi 。
   * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
   * 输出：true,一个可行的修课序列0->1->2->3
   */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    // 1、建领接表、入度
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    int[] inDegrees = new int[numCourses];

    for (int[] p : prerequisites) {
      // 邻接表：bi -> ai
      int cur = p[0];
      int pre = p[1];
      graph.get(pre).add(cur);
      // 入度
      inDegrees[cur]++;
    }

    // 2、入度为0的入队列
    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < inDegrees.length; i++) {
      if (inDegrees[i] == 0) {
        queue.offer(i);
      }
    }

    // 3、模拟课程过程:BFS
    int count = 0;
    while (!queue.isEmpty()) {
      Integer index = queue.poll();
      // 出队=上过的课
      count++;

      // 上完课的入度-1
      List<Integer> nextIndex = graph.get(index);
      for (Integer next : nextIndex) {
        // 发生变化的节点才可能再次为0
        if (--inDegrees[next] == 0) {
          queue.offer(next);
        }
      }
    }

    return count == numCourses;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int nums = 4;
    int[][] pres = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
    System.out.println(solution.canFinish(nums, pres));
  }
}
