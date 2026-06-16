package com.ssl.note.leetcode.编号刷题.LC155_最小栈;

import java.util.ArrayDeque;
import java.util.Deque;


public class MinStack {

  private final Deque<Integer> stack1;
  private final Deque<Integer> stack2;

  public MinStack() {
    stack1 = new ArrayDeque<>();
    stack2 = new ArrayDeque<>();
  }

  public void push(int val) {
    if(isEmpty()){
      stack1.push(val);
      stack2.push(val);
      return;
    }
    if (val < stack2.peek()) {
      stack2.push(val);
    } else {
      stack2.push(stack2.peek());
    }
    stack1.push(val);
  }

  // 题目要求只在栈非空时执行pop、top、getMin
  public void pop() {
    if (isEmpty()) {
      return;
    }
    stack1.pop();
    stack2.pop();
  }

  public int top() {
    return stack1.peek();
  }

  public int getMin() {
    return stack2.peek();
  }

  private boolean isNotEmpty() {
    return !stack1.isEmpty() && !stack2.isEmpty();
  }

  private boolean isEmpty() {
    return !isNotEmpty();
  }
}
