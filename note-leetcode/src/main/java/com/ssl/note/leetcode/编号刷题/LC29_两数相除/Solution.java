package com.ssl.note.leetcode.编号刷题.LC29_两数相除;

import java.util.Random;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/12 16:47
 * @Describe:
 */
public class Solution {

  /**
   * 两数相除
   * 给你两个整数，被除数 dividend 和除数 divisor。将两数相除，要求 不使用 乘法、除法和取余运算。
   * 输入: dividend = 10, divisor = 3
   * 输出: 3
   * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
   */
  private final int MIN = Integer.MIN_VALUE;
  private final int MAX = Integer.MAX_VALUE;

  public int divide(int a, int b) {
    // a、b都是最小
    if (a == MIN && b == MIN) {
      return 1;
    }
    // a、b都不是最小
    if (a != MIN && b != MIN) {
      return div(a, b);
    }
    // a不是最小，b是最小
    if (b == MIN) {
      return 0;
    }
    // a是最小，b是-1，题目case返回最大
    if (b == neg(1)) {
      return MAX;
    }
    // a是最小，b不是最小，b也不是-1
    // 1、b>0,(a+b)/b,结果-1
    // 2、b<0,(a-b)/b,结果+1
    a = add(a, b > 0 ? b : neg(b));
    int res = div(a, b);
    int offset = b > 0 ? neg(1) : 1;
    return add(res, offset);
  }


  // 加法
  private int add(int a, int b) {
    int res = a;
    while (b != 0) {
      res = a ^ b;

      b = (a & b) << 1;
      a = res;
    }
    return a;
  }

  // 减法
  private int minus(int a, int b) {
    return add(a, neg(b));
  }

  // 乘法
  private int multiply(int a, int b) {
    int res = 0;
    while (b != 0) {
      if ((b & 1) == 1) {
        // 复用加法
        res = add(res, a);
      }

      a <<= 1;
      b >>>= 1;
    }
    return res;
  }

  // 除法Base：neg没法处理整形最小值，a和b都不能是整形最小值
  private int div(int a, int b) {
    // neg没法处理整形最小值
    // 所以a和b的要求默认是不能是整形最小值
    int x = a < 0 ? neg(a) : a;
    int y = b < 0 ? neg(b) : b;

    int res = 0;
    for (int i = 30; i >= 0; i = minus(i, 1)) {
      // y << i有问题：<<会丢弃高位，左移后，可能丢弃了符号位变成负数
//      if (x >= (y << i)) {
      if ((x >> i) >= y) {
        res |= (1 << i);
        x = minus(x, (y << i));
      }
    }

    return a < 0 ^ b < 0 ? neg(res) : res;
  }

  // 相反数
  private int neg(int a) {
    return add(~a, 1);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int times = 100;
    for (int i = 0; i < times; i++) {
      int a = new Random().nextInt(100);
      int b = new Random().nextInt(100, 200);
      if (solution.add(a, b) != (a + b)) {
        System.out.println("error-add");
      }
      if (solution.minus(a, b) != (a - b)) {
        System.out.println("error-minus");
      }
      if (solution.multiply(a, b) != (a * b)) {
        System.out.println("error-multiply");
      }
      if (solution.div(a, b) != (a / b)) {
        System.out.println("error-div");
      }
      if (solution.divide(a, b) != (a / b)) {
        System.out.println("error-divide");
      }
    }
    System.out.println("end");
  }


}
