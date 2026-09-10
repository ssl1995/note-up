package com.ssl.note.practice.lc_top_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice {
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    int n = nums.length;
    List<List<Integer>> buckets = new ArrayList<>(n + 1);
    for (int i = 0; i <= n; i++) {
      buckets.add(new ArrayList<>());
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      int key = entry.getKey();
      int count = entry.getValue();

      buckets.get(count).add(key);
    }

    List<Integer> list = new ArrayList<>();
    for (int i = buckets.size() - 1; i >= 0; i--) {
      if (list.size() > k) {
        break;
      }
      if (buckets.get(i) != null) {
        list.addAll(buckets.get(i));
      }
    }

    int[] res = new int[k];
    int index = 0;
    for (int i = k - 1; i >= 0; i--) {
      res[index++] = list.get(i);
    }

    return res;
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[][] grod = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    // aaabcbc
  }

}
