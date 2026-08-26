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
    List<List<Integer>> buckets = new ArrayList<>();
    // 频率是下标[1,n]
    for (int i = 0; i <= n; i++) {
      buckets.add(new ArrayList<>());
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      buckets.get(entry.getValue()).add(entry.getKey());
    }

    int[] res = new int[k];
    int index = 0;
    for (int i = n; i >= 0; i--) {
      if (index >= k) {
        break;
      }
      if (buckets.get(i).isEmpty()) {
        continue;
      }
      List<Integer> sameCounts = buckets.get(i);
      for (int num : sameCounts) {
        if (index >= k) {
          break;
        }
        res[index++] = num;
      }
    }

    return res;
  }


  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "10[abc]";

  }

}
