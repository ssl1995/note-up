package com.ssl.note.leetcode.编号刷题.LC17_电话号码组合;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
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
    // 使用数组代替map
    String[] map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    backtrack(digits, 0, map, path, res);
    return res;
  }

  private void backtrack(String digits, int start, String[] map, StringBuilder path, List<String> res) {
    if (start == digits.length()) {
      res.add(path.toString());
      return;
    }
    char num = digits.charAt(start);
    // 2 -> abc
    String letter = map[num - '0'];
    for (int i = 0; i < letter.length(); i++) {
      path.append(letter.charAt(i));

      // 但是递归中的start是digits的指针，所以永远从start+1开始
      backtrack(digits, start + 1, map, path, res);

      path.delete(path.length() - 1, path.length());
//      path.deleteCharAt(path.length() - 1);
    }
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
//    String digits = "9";
//    System.out.println(solution.letterCombinations(digits));

    StringBuilder path =new StringBuilder();
    path.append("a");
    path.append("b");
    System.out.println(path.length());
    System.out.println(path.delete(path.length() - 1, path.length()));
  }

}
