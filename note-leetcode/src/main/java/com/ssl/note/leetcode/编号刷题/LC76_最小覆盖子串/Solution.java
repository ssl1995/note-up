package com.ssl.note.leetcode.编号刷题.LC76_最小覆盖子串;

/**
 * @author SongShengLin
 * @date 2022/1/28 8:33 AM
 * @description
 */
public class Solution {
  /**
   * 最小覆盖子串
   * 返回 s 中涵盖 t 所有字符的最小子串
   * 输入：s = "ADOBECODEBANC", t = "ABBC"
   * 输出："BANC"
   * <p>
   * 债务模型（与 LC438 Solution3 同构）：
   * 1.三要素：建债(cnts[t[i]]--记为负) → 还债(进窗++出窗--) → 查账(标量计数器 debt)
   * 2.核心思想：用标量计数器替代每轮数组比较(O(k)→O(1))，计数器只在词频跨过临界点0的瞬间更新
   * 3.一个数组三种状态：cnts[c]<0 还欠、==0 恰好、>0 盈余
   * 4.欠债粒度：本题"包含即可"（多还无所谓）→ 按个数欠 debt=t.length，进窗 cnts++<0 才 debt--；
   * LC438"精确匹配"（多了不行）→ 按种类欠，看 ==0
   * 5.内联坑：进窗还债每轮必做，可内联 if(cnts[cs[r]]++<0)；出账有条件，不能写成 cnts[cs[l]]-->0，
   * 否则判断为假也会多减一次；不熟就用"改前查→修改→改后查"的显式三段式
   * 6.新题先问两问："多了行不行？"（决定 <0 还是 ==0）、"窗口固定吗？"（决定要不要 l+while）
   */
  public String minWindow(String s, String t) {
    if (s.length() < t.length()) {
      return "";
    }
    char[] cs = s.toCharArray();
    char[] ct = t.toCharArray();
    // 需要的
    int[] cnts = new int[256];
    for (char c : ct) {
      cnts[c]--;
    }
    // 总的债务:t的个数
    int debt = ct.length;
    // 求子串，考虑初始坐标和长度
    int start = 0;
    int len = Integer.MAX_VALUE;
    for (int r = 0, l = 0; r < cs.length; r++) {
      // ++/-- 写进条件里，条件成功or失败都会执行
      // 这里是必须发生的，所以加1后还<0,说明还需要
      // 也可以写成：++cnts[cs[right]] <= 0
      if (cnts[cs[r]]++ < 0) {
        debt--;
      }

      if (debt == 0) {
        // ++/-- 写进条件里，条件成功or失败都会执行
        // 这里不是必须发生的，所以不能写成cnts[cs[left]]-->0
        while (cnts[cs[l]] > 0) {
          cnts[cs[l++]]--;
        }

        if (r - l + 1 < len) {
          start = l;
          len = r - l + 1;
        }
      }
    }
    return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "aaAABC";
    String t = "aaA";
    System.out.println(solution.minWindow(s, t));
  }
}
