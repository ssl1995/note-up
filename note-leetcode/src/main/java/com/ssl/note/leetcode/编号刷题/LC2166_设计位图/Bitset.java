package com.ssl.note.leetcode.编号刷题.LC2166_设计位图;

public class Bitset {

  private int[] set;
  private int size;
  private int zeros;// 0的总数
  private int ones;// 1的总数
  private boolean isReverse;// 是否设置过翻转，0变1,1变0

  public Bitset(int n) {
    this.set = new int[(n + 32 - 1) / 32];
    this.size = n;
    this.zeros = n;
    this.ones = 0;
    this.isReverse = false;
  }

  // 将下标为 idx 的位上的值更新为1。如果值已经是1，则不会发生任何改变。
  public void fix(int idx) {
    int index = idx / 32;
    int bit = idx % 32;
    // 没有翻转过
    // 0:不存在
    // 1:存在
    if (!isReverse) {
      if ((set[index] & (1 << bit)) == 0) {
        zeros--;
        ones++;
        set[index] |= (1 << bit);
      }
    } else {
      // 有翻转过,1更新0
      // 0:存在
      // 1:不存在
      if ((set[index] & (1 << bit)) != 0) {
        zeros--;
        ones++;
        set[index] ^= (1 << bit);
      }
    }
  }

  // 将下标为 idx 的位上的值更新为0。如果值已经是0，则不会发生任何改变。
  public void unfix(int idx) {
    int index = idx / 32;
    int bit = idx % 32;
    // 没有翻转过,1更新0
    if (!isReverse) {
      if ((set[index] & (1 << bit)) != 0) {
        ones--;
        zeros++;
        set[index] ^= (1 << bit);
      }
    } else {
      // 有翻转过,0更新1
      if ((set[index] & (1 << bit)) == 0) {
        ones--;
        zeros++;
        set[index] |= (1 << bit);
      }
    }
  }

  // 翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然。
  public void flip() {
    isReverse = !isReverse;
    // 勿忘
    int temp = zeros;
    zeros = ones;
    ones = temp;
  }

  // 检查 Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false 。
  public boolean all() {
    return ones == size;
  }

  // 检查 Bitset 中 是否 至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false 。
  public boolean one() {
    return ones > 0;
  }

  // 返回 Bitset 中值为 1 的位的 总数 。
  public int count() {
    return ones;
  }

  // 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致。
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0, k = 0, number, status; i < size; k++) {
      number = set[k];
      for (int j = 0; j < 32 && i < size; i++, j++) {
        status = (number >> j) & 1;
        status ^= isReverse ? 1 : 0;
        sb.append(status);
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    Bitset bitset = new Bitset(2);
    bitset.fix(1);
//    bitset.fix(2);
    // 正确：01
    // 初始化2个位置，只能放0和1，放fix(2)的话，会超过size导致输出没有
    System.out.println(bitset);
  }
}
