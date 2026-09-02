package com.ssl.note.practice.lc_top_100;

import java.util.ArrayDeque;
import java.util.Deque;

public class Practice {
  public String decodeString(String s) {
    Deque<String> letterStack = new ArrayDeque<>();
    Deque<Integer> numberStack = new ArrayDeque<>();

    int num = 0;
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c >= '0' && c <= '9') {
        num = num * 10 + (c - '0');
      } else if (c >= 'a' && c <= 'z') {
        sb.append(c);
      } else if (c == '[') {
        numberStack.push(num);
        num = 0;

        letterStack.push(sb.toString());
        sb = new StringBuilder();
      } else {
        if (numberStack.isEmpty()) {
          continue;
        }
        int count = numberStack.isEmpty() ? 0 : numberStack.pop();
        StringBuilder temp = new StringBuilder();
        while (count-- > 0) {
          temp.append(sb);
        }

        String pop = letterStack.isEmpty() ? "" : letterStack.pop();
        sb = new StringBuilder(pop + temp);
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    String s = "3[a]2[bc]";
    // aaabcbc
    System.out.println(practice.decodeString(s));
  }

}
