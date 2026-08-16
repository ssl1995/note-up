package com.ssl.note.practice.lc_top_100;

import com.ssl.note.common.utils.Question;
import com.ssl.note.common.utils.RandomUtil;

import java.util.List;

public class PracticeQues {

  public static void main(String[] args) {
    // 力扣热题Top100
    List<Question> pool = Question.buildLCTop100();
    int k = 2;
    List<Question> picks = RandomUtil.pickKByReservoir(pool, k);

    // 错题和难题
//    List<Question> pool = Question.buildLCTop100ByError();
//    int k = 3;
//    List<Question> picks = RandomUtil.pickKByReservoir(pool, k);

    System.out.println("poolSize=" + pool.size());
    System.out.println("k=" + k);
    for (int i = 0; i < picks.size(); i++) {
      System.out.println((i + 1) + ". " + picks.get(i));
    }
  }

}
