package com.ssl.note.leetcode.编号刷题.LC232_用栈实现队列;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue {

  private Deque<Integer> stack1;
  private Deque<Integer> stack2;

  public MyQueue() {
    stack1 = new LinkedList<>();
    stack2 = new LinkedList<>();
  }

  // 往stack2栈倒数据:
  // 1、stack2空时才倒，
  // 2、如果要倒，当时的stack1全倒完
  private void pushToStack2() {
    if (!stack2.isEmpty()) {
      return;
    }
    while (!stack1.isEmpty()) {
      stack2.push(stack1.pop());
    }
  }

  public void push(int x) {
    stack1.push(x);
    pushToStack2();
  }

  public int pop() {
    pushToStack2();
    return stack2.pop();
  }

  public int peek() {
    pushToStack2();
    return stack2.peek();
  }

  public boolean empty() {
    pushToStack2();
    return stack2.isEmpty();
  }
}
