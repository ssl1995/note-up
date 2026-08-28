package com.ssl.note.practice.lc_top_100;

import java.util.HashMap;
import java.util.Map;

public class Practice {

  public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int sum = 0;
    int res = 0;
    for (int num : nums) {
      sum += num;
      res += map.getOrDefault(map.get(sum - k), 0);
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return res;
  }


  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "10[abc]";

  }

}
