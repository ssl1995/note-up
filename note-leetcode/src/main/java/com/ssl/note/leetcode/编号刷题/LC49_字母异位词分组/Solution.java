package com.ssl.note.leetcode.编号刷题.LC49_字母异位词分组;

import java.sql.SQLOutput;
import java.util.*;

public class Solution {

  /**
   * 字母异位词分组
   * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
   * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
   * 时间和空间复杂度：O（n*k）
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) {
      return new ArrayList<>();
    }
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      // 使用次数 + 特殊字符，组成一个唯一键
      String key = getCountKey(str);

      map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
    }

    return new ArrayList<>(map.values());
  }

  /*
   * 使用次数 + 特殊字符，组成一个唯一键
   */
  private String getCountKey(String str) {
    if (str == null) {
      return "";
    }
    int[] count = new int[26];
    char[] cs = str.toCharArray();
    for (char c : cs) {
      count[c - 'a']++;
    }

    StringBuilder sb = new StringBuilder();
    for (int num : count) {
      // 加#防止：1和11无法区分真实的次数
      sb.append(num).append("#");
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    System.out.println(solution.groupAnagrams(strs));
    System.out.println("----------");
    Map<String, Integer> map = new HashMap<>();

    System.out.println("-----compute-----");
    // 键不存在时，计算新值并插入
    map.compute("a", (k, v) -> (v == null) ? 1 : v + 1); // 返回 1（插入）
    System.out.println(map.get("a"));
    // 键存在时，基于旧值计算新值
    map.compute("a", (k, v) -> (v == null) ? 1 : v + 1); // 返回 2（更新）
    System.out.println(map.get("a"));
    // 函数返回null时，删除键
    map.compute("a", (k, v) -> null); // 返回 null（删除键）
    System.out.println(map.get("a"));

    System.out.println("-----computeIfAbsent-----");
    Map<String, List<String>> map1 = new HashMap<>();
    // 键不存在时，创建新列表并添加元素
    map1.computeIfAbsent("group1", k -> new ArrayList<>()).add("item1");
    // 键存在时，直接获取列表并添加元素
    map1.computeIfAbsent("group1", k -> new ArrayList<>()).add("item2");

    System.out.println("-----computeIfPresent-----");
    Map<String, Integer> map2 = new HashMap<>();
    map2.put("count", 1);
    // 键存在时，基于旧值累加
    map2.computeIfPresent("count", (k, v) -> v + 1); // 返回 2（更新）
    // 函数返回null时，删除键
    map2.computeIfPresent("count", (k, v) -> null); // 返回 null（删除键）
    // 键不存在时，无操作
    map2.computeIfPresent("count", (k, v) -> v + 1); // 返回 null（无操作）
  }
}
