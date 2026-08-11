package com.ssl.note.leetcode.编号刷题.LC421_两个数最大异或值;

public class Solution {

  /**
   * LC421_两个数最大异或值
   * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
   * 示例：
   * 输入：nums = [3,10,5,25,2,8]
   * 输出：28
   * 解释：最大运算结果是 5 XOR 25 = 28.
   */
  public int findMaximumXOR(int[] nums) {
    // 建立前缀树
    build(nums);
    int res = Integer.MIN_VALUE;
    for (int num : nums) {
      res = Math.max(res, getMaxXor(num));
    }
    clear();
    return res;
  }

  // 写法1:都是位运算
  private static int getMaxXor(int num) {
    int res = 0;

    int cur = 1;
    for (int i = high; i >= 0; i--) {
      // 当前数该位是1还是0
      int status = (num >> i) & 1;
      // 如果要异或最大，原1期望下个数是0
      int want = status ^ 1;
      // 前缀树中找这个期望的位是否存在
      if (tree[cur][want] == 0) {
        // 也可以这么写:want ^=1
        want = status;
      }
      res |= (status ^ want) << i;
      cur = tree[cur][want];
    }

    return res;
  }

  // 写法2:if判断需要理解want最后要让cur走到期望位置
  private static int getMaxXor1(int num) {
    int res = 0;

    int cur = 1;
    for (int i = high; i >= 0; i--) {
      // 当前数该位是1还是0
      int status = (num >> i) & 1;
      // 如果要异或最大，原1期望下个数是0
      int want = status ^ 1;
      // 前缀树中找这个期望的位是否存在
      if (tree[cur][want] == 0) {
        want = status;
//        res|=0;
      } else {
        // 期望存在记录结果
        res |= 1 << i;
      }
      cur = tree[cur][want];
    }

    return res;
  }

  // 必须static：LeetCode每个测试用例都会new Solution()
  // 若作为实例字段，每个用例都要重新分配300万个int[2]小对象(约100MB)，必然超时
  // static则类加载时只分配一次，之后靠clear()复用
  private static final int MAX = 3000001;
  private static final int[][] tree = new int[MAX][2];
  private static int cnt;

  // 根据题目计算的特殊变量：高位到低位第一个1是从左到右第几个(第0个、第1个等)开始的
  // 比如12=1100，左边第一个1是从左到右第3位开始的
  private static int high;

  private static void build(int[] nums) {
    cnt = 1;
    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      max = Math.max(max, num);
    }
    high = 31 - Integer.numberOfLeadingZeros(max);
    for (int num : nums) {
      insert(num);
    }
  }

  private static void insert(int num) {
    int cur = 1;
    for (int i = high; i >= 0; i--) {
      int path = (num >> i) & 1;
      if (tree[cur][path] == 0) {
        tree[cur][path] = ++cnt;
      }
      cur = tree[cur][path];
    }
  }

  public static void clear() {
    for (int i = 1; i <= cnt; i++) {
      tree[i][0] = tree[i][1] = 0;
    }
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    int[] nums = {3, 10, 5, 25, 2, 8};
    // 最大运算结果是 5 XOR 25 = 28.
    System.out.println(s.findMaximumXOR(nums));
  }
}
