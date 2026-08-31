package com.ssl.note.practice.lc_top_100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Practice {

  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (prerequisites == null) {
      return false;
    }
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    int[] indegree = new int[numCourses];
    for (int[] p : prerequisites) {
      int cur = p[0];
      int pre = p[1];
      graph.get(pre).add(cur);
      indegree[cur]++;
    }

    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegree[i] == 0) {
        queue.offer(i);
      }
    }

    int count = 0;
    while (!queue.isEmpty()) {
      int poll = queue.poll();
      count++;

      List<Integer> nexts = graph.get(poll);
      for (int next : nexts) {
        // indegree[next]--;
        if (--indegree[next] == 0) {
          queue.offer(next);
        }
      }
    }

    return count == numCourses;
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[][] nums = {{0, 1}};
    int num = 2;
    System.out.println(practice.canFinish(num, nums));
  }

}
