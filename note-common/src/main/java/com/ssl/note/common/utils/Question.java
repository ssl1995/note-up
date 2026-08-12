package com.ssl.note.common.utils;

import java.util.ArrayList;
import java.util.List;

public class Question {

  /**
   * 来源，必填
   * LC:力扣
   * NC：牛客
   */
  private String web;

  /**
   * 题目名称，必填
   * 1 两数之和
   */
  private String name;

  /**
   * 标签，非必填
   * 01-哈希
   */
  private String tag;

  public Question(String web, String name) {
    this.web = web;
    this.name = name;
  }

  public String getWeb() {
    return web;
  }

  public void setWeb(String web) {
    this.web = web;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    if (tag.isBlank()) {
      return web + name;
    }
    return tag + web + name;
  }

  // 初始化题目
  public static List<Question> buildLCTop100() {
    List<Question> questions = new ArrayList<>();

    return questions;
  }

  public static void main(String[] args) {
    Question question1 = new Question("LC", "1 两数之和");
    System.out.println(question1);
  }
}
