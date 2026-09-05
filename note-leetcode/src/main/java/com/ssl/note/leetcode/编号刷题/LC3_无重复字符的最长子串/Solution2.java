package com.ssl.note.leetcode.编号刷题.LC3_无重复字符的最长子串;

import java.util.Arrays;

public class Solution2 {
  /**
   * 无重复字符的最长子串
   * 输入: s = "abcabcbb"
   * 输出: 3
   * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
   * 概念：1.子串 = 连续的 2.子序列 = 不连续的
   * <p>
   * 经验总结：
   * 1.题型触发："最长/最短 + 连续子串 + 无重复/满足约束" → 同向双指针滑动窗口，l 只增不减
   * 2.核心洞察：窗口不变量 (l, r] 内始终无重复，r 右移一步时唯一可能制造重复的只有新进来的 cs[r]，
   * 故只需查 cs[r] 的上次出现位置 last，新左边界 = last+1，O(n²) → O(n)
   * 3.l 不回退：last 可能已过期（在当前窗口左边），不能写 l = last+1，必须 l = max(l, last+1)，
   * 反例 "abba"：扫到最后的 a 时 map['a']=0，直接赋值会让 l 回退
   * 4.记忆口诀：map 填 -1 表示"没见过"，l = max(l, last+1)，没见过自动不生效；
   * 此时 if (map[c] != -1) 是冗余的（max + (-1) 已兜底），写它只为表达"只在重复时更新 l"的语义；
   * 若数组默认 0 初始化，则 if 不能省（否则第一个字符就把 l 推到 1）
   * 5.自测用例："abba"→2(杀 l 回退)、"aabbaa"→2(连续重复)、" "/""(杀 -1 初始化边界)、"abcabcbb"→3(标准)
   */
  public int lengthOfLongestSubstring(String s) {
    int[] map = new int[256];
    Arrays.fill(map, -1);

    char[] cs = s.toCharArray();
    int res = 0;
    // l初始化-1，表示(l,r]的左边界之外
    for (int r = 0, l = -1; r < cs.length; r++) {
      // l表示上次出现的位置,只有重复时才更新
//      if (map[cs[r]] != -1) {
//        l = Math.max(l, map[cs[r]]);
//      }
      // 因为l最小=-1，map默认是也是-1，不用上面的if
      l = Math.max(l, map[cs[r]]);
      map[cs[r]] = r;
      // 无重复的长度：r-(l+1)+1=r-l
      res = Math.max(res, r - l);
    }

    return res;
  }

  public int lengthOfLongestSubstring2(String s) {
    int[] map = new int[256];
    Arrays.fill(map, -1);

    char[] cs = s.toCharArray();

    int res = 0;
    for (int r = 0, l = 0; r < cs.length; r++) {
      // 滑动窗口r和l不能回退,l来到过的位置不能再回去了
      l = Math.max(l, map[cs[r]] + 1);
      map[cs[r]] = r;

      res = Math.max(res, r - l + 1);
    }

    return res;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    String s = "aabbaa";// 2
    System.out.println(solution.lengthOfLongestSubstring(s));
  }
}
