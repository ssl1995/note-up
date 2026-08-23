package com.ssl.note.practice.lc_top_100;

import java.util.*;

public class Practice {

  private int fresh;

  public int[] findOrder(int numCourses, int[][] pre) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      graph.add(new ArrayList<>());
    }

    int[] in = new int[numCourses];

    for (int[] cur : pre) {
      int a = cur[0];
      int b = cur[1];
      graph.get(b).add(a);
      in[a]++;
    }

    Deque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < numCourses; i++) {
      if (in[i] == 0) {
        queue.offer(i);
      }
    }

    int count = 0;
    int[] res = new int[numCourses];
    while (!queue.isEmpty()) {
      int poll = queue.poll();
      res[count++] = poll;

      List<Integer> next = graph.get(poll);
      for (int num : next) {
        if (--in[num] == 0) {
          queue.offer(num);
        }
      }
    }

    return count == numCourses ? res : new int[]{};
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[][] pre = {};
    int n = 1;
    System.out.println(Arrays.toString(practice.findOrder(n, pre)));
  }

}
