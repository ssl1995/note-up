package com.ssl.note.leetcode.编号刷题.LC17_电话号码组合;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  /**
   * 电话号码组合
   * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合
   * 输入：digits = "23"
   * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
   */
  public List<String> letterCombinations(String digits) {
    if (digits == null || digits.isEmpty()) {
      return new ArrayList<>();
    }
    Map<Character, String> map = new HashMap<>();
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");
    List<String> res = new ArrayList<>();
    dfs(digits, 0, map, new StringBuilder(), res);
    return res;
  }

  private void dfs(String digits, int i, Map<Character, String> map,
                   StringBuilder path,
                   List<String> res) {
    if (i == digits.length()) {
      // 加入结果集
      res.add(path.toString());
      return;
    }
    // 2 -> abc
    String lett = map.get(digits.charAt(i));
    for (Character c : lett.toCharArray()) {
      path.append(c);
      // 从i+1开始，而不是j+1，因为遍历的中心是digits
      dfs(digits, i + 1, map, path, res);
      path.deleteCharAt(path.length() - 1);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String digits = "9";
    System.out.println(solution.letterCombinations(digits));
    System.out.println("---");
    System.out.println("".length());
  }

}
