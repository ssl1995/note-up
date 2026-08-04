package com.ssl.note.leetcode.编号刷题.LC381_插入删除随机O1_重复;

import java.util.*;

public class RandomizedCollection {

  private final Map<Integer, Set<Integer>> map;
  private final List<Integer> arr;

  /**
   * RandomizedCollection 是一种包含数字集合(可能是重复的)的数据结构。它应该支持插入和删除特定元素，以及删除随机元素。
   * 实现 RandomizedCollection 类:
   * RandomizedCollection()初始化空的 RandomizedCollection 对象。
   * bool insert(int val) 将一个 val 项插入到集合中，即使该项已经存在。如果该项不存在，则返回 true ，否则返回 false 。
   * bool remove(int val) 如果存在，从集合中移除一个 val 项。如果该项存在，则返回 true ，否则返回 false 。注意，如果 val 在集合中出现多次，我们只删除其中一个。
   * int getRandom() 从当前的多个元素集合中返回一个随机元素。每个元素被返回的概率与集合中包含的相同值的数量 线性相关 。
   * 您必须实现类的函数，使每个函数的 平均 时间复杂度为 O(1) 。
   * 注意：生成测试用例时，只有在 RandomizedCollection 中 至少有一项 时，才会调用 getRandom 。
   */
  public RandomizedCollection() {
    this.map = new HashMap<>();
    this.arr = new ArrayList<>();
  }

  /**
   * 新增一个元素
   */
  public boolean insert(int val) {
    arr.add(val);
    int valueIndex = arr.size() - 1;
    // 允许重复，map的value存list，往list中存加入的下标
    map.computeIfAbsent(val, k -> new HashSet<>()).add(valueIndex);
    // 重复加入的返回false
    return map.get(val).size() == 1;
  }

  /**
   * 删除一个 val，要求平均 O(1)。
   * 核心技巧：数组中间删除是 O(n)，所以用【末尾元素覆盖被删位置，再删末尾】实现 O(1)。
   * 删完后要同步修改"下标登记簿"（map 里的 Set）。
   */
  public boolean remove(int val) {
    if (!map.containsKey(val)) {
      return false;
    }
    // val 的所有下标，任取一个作为要删的位置（Set 无序，取哪个都行）
    Set<Integer> deleteSet = map.get(val);
    Integer deleteSetAnyIndex = deleteSet.iterator().next();
    // 末尾元素（将要被"物理删除"位置的元素，用它去补洞）
    Integer endValue = arr.get(arr.size() - 1);

    if (endValue == val) {
      // 情况1：要删的 val 就在末尾，不用搬，直接把它末尾的下标记录删掉
      deleteSet.remove(arr.size() - 1);
    } else {
      // 情况2：val 不在末尾，把末尾元素 endValue 搬到 valAnyIndex 位置
      // 2.1 更新末尾元素的下标登记簿：旧下标(末尾) -> 新下标(valAnyIndex)
      Set<Integer> endValSet = map.get(endValue);
      endValSet.remove(arr.size() - 1);
      endValSet.add(deleteSetAnyIndex);

      // 2.2 arr里真正搬值：末尾值覆盖到被删位置
      arr.set(deleteSetAnyIndex, endValue);
      // 2.3 val 的下标登记簿删掉这个下标（少了一个 val）
      deleteSet.remove(deleteSetAnyIndex);
    }

    // 物理删除arr末尾元素
    arr.remove(arr.size() - 1);

    // val 已经全部删完，从 map 中移除这个 key
    if (deleteSet.isEmpty()) {
      map.remove(val);
    }

    return true;
  }

  public int getRandom() {
    int random = new Random().nextInt(arr.size());
    return arr.get(random);
  }
}
