package com.ssl.note.leetcode.编号刷题.LC380_插入删除随机O1_不重复;

import java.util.*;

public class RandomizedSet {

  private final Map<Integer, Integer> map;
  private final List<Integer> arr;

  public RandomizedSet() {
    this.map = new HashMap<>();
    this.arr = new ArrayList<>();
  }

  public boolean insert(int val) {
    // 本题不允许含有重复元素
    if (map.containsKey(val)) {
      return false;
    }
    // 老size就是新数的下标
    map.put(val, arr.size());
    arr.add(val);
    return true;
  }

  public boolean remove(int val) {
    if (!map.containsKey(val)) {
      return false;
    }
    // 数：val，数下标：valueIndex
    Integer valIndex = map.get(val);
    // 数：endNum，数下标：size-1
    Integer endValue = arr.get(arr.size() - 1);

    map.put(endValue, valIndex);
    arr.set(valIndex, endValue);

    // map删除用key
    map.remove(val);
    // list删除用index，而不是value
    arr.remove(arr.size() - 1);

    return true;
  }

  public int getRandom() {
    int random = new Random().nextInt(arr.size());
    return arr.get(random);
  }
}
