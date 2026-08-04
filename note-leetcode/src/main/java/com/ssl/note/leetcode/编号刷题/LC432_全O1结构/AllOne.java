package com.ssl.note.leetcode.编号刷题.LC432_全O1结构;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOne {

  /**
   * 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串
   * 实现 AllOne 类：
   * AllOne() 初始化数据结构的对象。
   * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
   * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
   * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
   * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
   * 注意：每个函数都应当满足 O(1) 平均时间复杂度。
   * 示例：
   * 输入
   * ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
   * [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
   * 输出
   * [null, null, null, "hello", "hello", null, "hello", "leet"]
   * 解释
   * AllOne allOne = new AllOne();
   * allOne.inc("hello");
   * allOne.inc("hello");
   * allOne.getMaxKey(); // 返回 "hello"
   * allOne.getMinKey(); // 返回 "hello"
   * allOne.inc("leet");
   * allOne.getMaxKey(); // 返回 "hello"
   * allOne.getMinKey(); // 返回 "leet"
   * 提示：
   * 1 <= key.length <= 10
   * key 由小写英文字母组成
   * 测试用例保证：在每次调用 dec 时，数据结构中总存在 key
   * 最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次
   */
  static class Bucket {
    private final int cnt;
    private final Set<String> set;
    private Bucket pre;
    private Bucket next;

    public Bucket(String str, int cnt) {
      this.set = new HashSet<>();
      this.set.add(str);
      this.cnt = cnt;
    }
  }

  private final Bucket head;
  private final Bucket tail;
  private final Map<String, Bucket> map;

  public AllOne() {
    this.head = new Bucket("", 0);
    this.tail = new Bucket("", Integer.MAX_VALUE);
    this.head.next = this.tail;
    this.tail.pre = this.head;
    this.map = new HashMap<>();
  }

  public void inc(String key) {
    // 不存在该桶
    if (!map.containsKey(key)) {
      // 已经有了1频次的桶
      if (head.next.cnt == 1) {
        head.next.set.add(key);
        map.put(key, head.next);
      } else {
        Bucket bucket = new Bucket(key, 1);
        map.put(key, bucket);
        insert(head, bucket);
      }
      return;
    }

    // 存在该桶,key移除当前桶，往后存下一个桶里
    Bucket bucket = map.get(key);
    // 往后存下一个桶里
    if (bucket.next.cnt != bucket.cnt + 1) {
      Bucket newBucket = new Bucket(key, bucket.cnt + 1);
      map.put(key, newBucket);
      insert(bucket, newBucket);
    } else {
      Bucket nextBucket = bucket.next;
      nextBucket.set.add(key);
      map.put(key, nextBucket);
    }

    // key移除当前桶
    bucket.set.remove(key);
    if (bucket.set.isEmpty()) {
      remove(bucket);
    }
  }

  public void dec(String key) {
    // 不存在该桶:在减少计数前，key存在，不会触发
    if (!map.containsKey(key)) {
      return;
    }

    // 存在该桶,key移除当前桶，往后存下一个桶里
    Bucket bucket = map.get(key);
    // 往前存一个桶
    if (bucket.cnt == 1) {
      map.remove(key);
    } else {
      if (bucket.pre.cnt != bucket.cnt - 1) {
        Bucket newBucket = new Bucket(key, bucket.cnt - 1);
        map.put(key, newBucket);
        insert(bucket.pre, newBucket);
      } else {
        Bucket preBucket = bucket.pre;
        preBucket.set.add(key);
        map.put(key, preBucket);
      }
    }

    // key移除当前桶
    bucket.set.remove(key);
    if (bucket.set.isEmpty()) {
      remove(bucket);
    }
  }

  public String getMaxKey() {
    return tail.pre.set.iterator().next();
  }

  public String getMinKey() {
    return head.next.set.iterator().next();
  }

  // 辅助方法：cur后面插入一个新桶
  private void insert(Bucket cur, Bucket pos) {
    pos.next = cur.next;
    pos.pre = cur;

    cur.next.pre = pos;
    cur.next = pos;
  }

  // 辅助方法：删除一个桶
  private void remove(Bucket delete) {
    delete.pre.next = delete.next;
    delete.next.pre = delete.pre;
  }

  public static void main(String[] args) {
    AllOne allOne = new AllOne();
    allOne.inc("hello");
    allOne.inc("hello");
    System.out.println(allOne.getMaxKey());
    System.out.println(allOne.getMinKey());
    allOne.inc("leet");
    System.out.println(allOne.getMaxKey());
    System.out.println(allOne.getMinKey());
  }
}
