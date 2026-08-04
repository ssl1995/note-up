package com.ssl.note.leetcode.编号刷题.LC895_最大频率栈;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreqStack {

  /**
   * 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
   * 实现 FreqStack 类:
   * FreqStack() 构造一个空的堆栈。
   * void push(int val) 将一个整数 val 压入栈顶。
   * int pop() 删除并返回堆栈中出现频率最高的元素。
   * 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
   */
  // 最大频次
  private int topTime;
  // 词频表
  private Map<Integer, Integer> timesMap;
  // 相同频率表
  private Map<Integer, List<Integer>> cntMap;

  public FreqStack() {
    this.topTime = 0;
    this.cntMap = new HashMap<>();
    this.timesMap = new HashMap<>();
  }

  public void push(int val) {
    timesMap.put(val, timesMap.getOrDefault(val, 0) + 1);
    Integer times = timesMap.get(val);

    cntMap.computeIfAbsent(times, k -> new ArrayList<>()).add(val);
    // 更新最大词频
    topTime = Math.max(times, topTime);
  }

  public int pop() {
    List<Integer> topTimsList = cntMap.get(topTime);
    // 最高层从右往左倒出
    Integer pop = topTimsList.remove(topTimsList.size() - 1);
    // 当前最高层空了，最大频率-1
    if (topTimsList.isEmpty()) {
      cntMap.remove(topTime--);
    }

    Integer popTime = timesMap.get(pop);
    if (popTime == 1) {
      timesMap.remove(pop);
    } else {
      timesMap.put(pop, popTime - 1);
    }

    return pop;
  }
}
