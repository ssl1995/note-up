package com.ssl.note.leetcode.编号刷题.LC1590_使数组和能被P整除;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  /**
   * LC1590_使数组和能被P整除
   * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
   * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
   * 子数组 定义为原数组中连续的一组元素。
   * 示例：
   * 输入：nums = [3,1,4,2], p = 6
   * 输出：1
   * 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
   * 【思路】前缀和 + 哈希表 + 同余原理
   * 1. 题意转化：
   *    设总和为 S，移除子数组 nums[j+1..i] 的和为 sub，要求 (S - sub) % p == 0
   *    即 sub % p == S % p，记 mod = S % p，则 sub % p = mod，移除一个"某个和模p余mod"的最短子数组
   * 2. 同余原理（核心）：
   *    a % p == b % p  ⟺  (a - b) % p == 0，若所有和%p= mod，若存在某段和%p=mod，则该段就是被删除的那一段
   * 3. 哈希表记录：key = 前缀和余数，value = 该余数最晚出现的下标（越晚 j 越大，i - j 越小，越优）
   * 【示例推演】nums = [3,1,4,2], p = 6
   *    总和 10，mod = 10 % 6 = 4，需要移除"余数为 4"的子数组
   *    初始 map = {余数0: 下标-1}
   *    i=0: cur=3, find=(3-4+6)%6=5, 无 → map={0:-1, 3:0}
   *    i=1: cur=4, find=0,        有 j=-1 → minLen=1-(-1)=2, map+={4:1}
   *    i=2: cur=2, find=4,        有 j=1  → minLen=min(2, 1)=1,  map+={2:2}  ← 移除[4]
   *    i=3: cur=4, find=0,        有 j=-1 → minLen=min(1, 4)=1
   *    返回 1
   * 【复杂度】时间 O(n)，空间 O(min(n, p))（余数最多 p 种）
   */
  public int minSubarray(int[] nums, int p) {
    // 1. 计算总和模 p 的余数 mod（即需要移除的子数组的余数目标）
    int mod = 0;
    for (int num : nums) {
      mod = (mod + num) % p;
    }
    // 整体已经能整除，移除空子数组即可
    if (mod == 0) {
      return 0;
    }

    int n = nums.length;
    // map: 前缀和余数 -> 该余数最晚出现的下标
    Map<Integer, Integer> map = new HashMap<>();
    // 空前缀余数为 0，下标记为 -1，用于匹配"从下标 0 开始移除"的子数组
    map.put(0, -1);

    int minLen = Integer.MAX_VALUE;
    for (int i = 0, cur = 0, find = 0; i < n; i++) {
      // cur = pre[i] % p，当前前缀和的余数
      cur = (cur + nums[i]) % p;
      // 目标余数 find = (cur - mod) % p，+p 防止 Java 取模出现负数
      // 若存在 pre[j] % p == find，则子数组 nums[j+1..i] 的和模 p 余 mod，正是要移除的
      find = (cur - mod + p) % p;
      // 等价写法：find = cur >= mod ? cur - mod : (cur + p - mod);
      if (map.containsKey(find)) {
        // 找到同余前缀j，子数组长度为 i - j，更新最短长度
        minLen = Math.min(minLen, i - map.get(find));
      }
      // 记录余数 cur 最晚出现的下标，每次都覆盖更新（j 越大，i - j 越小，越优）
      map.put(cur, i);
    }

    // 不允许移除整个数组；无解或只能全删时返回 -1
    return minLen == n ? -1 : minLen;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    // 总和 6 % 3 = 0，无需移除，输出 0
    int[] nums = new int[]{1, 2, 3};
    int p = 3;
    System.out.println(s.minSubarray(nums, p));
  }
}
