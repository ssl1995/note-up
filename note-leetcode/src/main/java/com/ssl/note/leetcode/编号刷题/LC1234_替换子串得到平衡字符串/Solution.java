package com.ssl.note.leetcode.编号刷题.LC1234_替换子串得到平衡字符串;

public class Solution {

  /**
   * LC1234_替换子串得到平衡字符串
   * 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
   * 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
   * 输入：s = "QQWE"
   * 输出：1
   * 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
   * <p>
   * 思路：替换一个子串后，子串外的字符必须已经平衡（每种 ≤ n/4）。
   * 问题转化为：找最短子串，使其包含所有“超出 n/4 的富余字符” → 套 LC76 最小覆盖子串模板。
   * <p>
   * 经验总结：
   * 1. “替换/删除一段使整体满足某种计数要求”→ 转化为“窗口外已满足，窗口内覆盖富余”，想到滑动窗口。
   * 2. 欠债模型：超出的字符记负数（欠多少个），窗口纳入时 ++，从负数加回 0 才算还了一份债；
   * 不够的字符记 0（不用还），debt 记录总债务，debt == 0 时窗口有效。
   * 3. 收缩循环用“先判断后操作”（cnts[sArr[l]] > 0 才移除），不要把 -- 写进条件，
   * 否则条件失败时计数会被偷减一次，与窗口不一致。
   */
  public int balancedString(String s) {
    int n = s.length();
    // QWER 映射为 0/1/2/3，方便用数组计数
    int[] sArr = new int[n];
    int[] cnts = new int[n];

    for (int i = 0; i < n; i++) {
      char c = s.charAt(i);
      sArr[i] = c == 'Q' ? 0 : (c == 'W' ? 1 : (c == 'E' ? 2 : 3));
      cnts[sArr[i]]++;
    }
    // 总：40，Q:4,W:16,E:10,R:10,
    // 转换：需要W拿出6个的最小子串数量(LC76_最小覆盖子串)
    int target = n / 4;
    int debt = 0;
    for (int i = 0; i < n; i++) {
      // 不够的=不欠债的，需要别人帮忙
      if (cnts[i] < target) {
        cnts[i] = 0;
      } else {
        // W：16,需要6个，总债务+6
        int count = cnts[i] - target;
        cnts[i] = -count; // 负数 = 该字符欠下的债（必须被窗口覆盖的数量）
        debt += count;
      }
    }
    if (debt == 0) {
      return 0;
    }

    int res = Integer.MAX_VALUE;
    for (int r = 0, l = 0; r < n; r++) {
      // 右端字符进窗口：计数 ++；从负数加回来说明还上了一份债
      if (cnts[sArr[r]]++ < 0) {
        debt--;
      }
      if (debt == 0) {
        // 债已还清，左端有富余就尽量收缩（先判断后操作）
        while (cnts[sArr[l]] > 0) {
          cnts[sArr[l++]]--;
        }
        res = Math.min(res, r - l + 1);
      }
    }

    return res == Integer.MAX_VALUE ? 0 : res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "QQWE";
    System.out.println(solution.balancedString(s));
  }
}
