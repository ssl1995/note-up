package com.ssl.note.leetcode.编号刷题.LC906_超级回文数;

public class Solution {

  /**
   * LC906_超级回文数
   * 如果一个正整数自身是回文数，而且它也是一个回文数的平方，那么我们称这个数为 超级回文数 。
   * 示例：
   * 输入：left = "4", right = "1000"
   * 输出：4
   * 解释：4、9、121 和 484 都是超级回文数。
   * 注意 676 不是超级回文数：26 * 26 = 676 ，但是 26 不是回文数。
   */
  public int superpalindromesInRange(String leftStr, String rightStr) {
    long l = Long.parseLong(leftStr);
    long r = Long.parseLong(rightStr);
    // leftStr根据题目范围是2^18次方，要想降低时间复杂度，考虑根号
    long limit = (long) Math.sqrt(r);

    // 根号后数量范围是2^9次方也不行，思考再只考虑一半规模=seed从1开始,
    // 比如seed=121，求num=121构成的奇数或者偶数回文，再用num*num判断是否再范围内
    long num;
    long seed = 1;

    int res = 0;
    do {
      // 因为奇数回文比偶数回文形成的num较小，
      // 先偶数后奇数,num<limit不会丢失结果
      // 121的偶数回文121121
      num = getEvenOrOddNum(seed, false);
      if (check(num * num, l, r)) {
        res++;
      }
      // 121的奇数回文12121
      num = getEvenOrOddNum(seed, true);
      if (check(num * num, l, r)) {
        res++;
      }
      // 种子变大
      seed++;
    }
    while (num < limit);

    return res;
  }

  // 生成偶数或者偶数回文,123-> 123321(偶数),123-> 12321(奇数)
  private long getEvenOrOddNum(long seed, boolean isOdd) {
    long res = seed;
    // 是奇数回文，提前除10
    if (isOdd) {
      seed /= 10;
    }
    while (seed != 0) {
      res = res * 10 + seed % 10;

      seed /= 10;
    }
    return res;
  }

  // LC9:是否是回文数字，入参是long类型
  private boolean check(long num, long l, long r) {
    // 负数或者不在范围内，不是有效回文数字
    if (num < 0 || num < l || num > r) {
      return false;
    }
    long offset = 1;
    while (num / offset >= 10) {
      offset *= 10;
    }
    while (num != 0) {
      long num1 = num / offset;
      long num2 = num % 10;
      if (num1 != num2) {
        return false;
      }

      num = (num % offset) / 10;
      offset /= 100;
    }
    return true;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String leftStr = "4";
    String rightStr = "1000";
    System.out.println(solution.superpalindromesInRange(leftStr, rightStr));
  }
}
