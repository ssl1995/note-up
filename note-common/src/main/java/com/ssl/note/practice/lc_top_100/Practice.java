package com.ssl.note.practice.lc_top_100;

import com.ssl.note.common.utils.Question;
import com.ssl.note.common.utils.RandomUtil;

import java.util.List;

public class Practice {

  public static void main(String[] args) {
    // 题库：力扣热题Top100
    List<Question> pool = Question.buildLCTop100();

    int k = 3; // 想随机抽取k个（不重复）
    List<Question> picks = RandomUtil.pickKByReservoir(pool, k);

    System.out.println("poolSize=" + pool.size());
    System.out.println("k=" + k);
    for (int i = 0; i < picks.size(); i++) {
      System.out.println((i + 1) + ". " + picks.get(i));
    }
  }
}
