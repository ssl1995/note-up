package com.ssl.know.java.collection;

import java.util.*;

public class IterTest {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("A");
    list.add("B");
    list.add("C");

    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      String element = iterator.next();
      System.out.println(element);
    }

    Map<String, String> map = new HashMap<>();
    map.put("1", "1");
    map.put("2", "2");
    map.put("3", "3");
    Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
    while (iter.hasNext()) {
      if (Objects.equals(iter.next().getKey(), "1")) {
        iter.remove();
      }
    }
    System.out.println(map);
  }
}
