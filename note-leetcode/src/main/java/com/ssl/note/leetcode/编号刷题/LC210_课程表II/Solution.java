package com.ssl.note.leetcode.编号刷题.LC210_课程表II;

import java.util.*;

public class Solution {

  /**
   * LC210_课程表II
   * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
   * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
   * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
   * 示例：
   * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
   * 输出：[0,2,1,3]
   * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
   * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
   */
  public int[] findOrder(int numCourses, int[][] prerequisites) {
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
    int[] res = new int[numCourses];
    // index既是遍历指针，也表示有效的课程数
    int index = 0;
    while (!queue.isEmpty()) {
      Integer poll = queue.poll();
      // 出队=上过的课
      res[index++] = poll;

      // 上完课的入度-1
      List<Integer> nextIndex = graph.get(poll);
      for (Integer next : nextIndex) {
        // 发生变化的节点才可能再次为0
        if (--inDegrees[next] == 0) {
          queue.offer(next);
        }
      }
    }
    // 如果都是有效的课程，才返回res数组
    return index == numCourses ? res : new int[]{};
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
//    int numCourses = 4;
//    int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
    int numCourses = 2;
    int[][] prerequisites = {{1, 0}, {0, 1}};
    System.out.println(Arrays.toString(solution.findOrder(numCourses, prerequisites)));
  }
}
