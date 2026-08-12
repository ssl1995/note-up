package com.ssl.note.common.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

  public Question(String web, String name, String tag) {
    this.web = web;
    this.name = name;
    this.tag = tag;
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

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    if (tag == null || tag.isBlank()) {
      return web + " " + name;
    }
    return tag + " " + web + " " + name;
  }

  // 力扣热题Top100（共104道）
  public static List<Question> buildLCTop100() {
    List<Question> questions = new ArrayList<>();
    String web = "LC";

    // 01-哈希
    String tag = "01-哈希";
    questions.add(new Question(web, "1 两数之和", tag));
    questions.add(new Question(web, "49 字母异位词分组", tag));
    questions.add(new Question(web, "128 最长连续序列", tag));

    // 02-双指针
    tag = "02-双指针";
    questions.add(new Question(web, "283 移动零", tag));
    questions.add(new Question(web, "11 盛水最多的容器", tag));
    questions.add(new Question(web, "15 三数之和", tag));
    questions.add(new Question(web, "42 接雨水", tag));
    questions.add(new Question(web, "407 接雨水II（补充）", tag));

    // 03-滑动窗口
    tag = "03-滑动窗口";
    questions.add(new Question(web, "3 无重复字符的最长子串", tag));
    questions.add(new Question(web, "438 找到字符串中所有的字母异位词", tag));

    // 04-子串
    tag = "04-子串";
    questions.add(new Question(web, "560 和为K的子数组", tag));
    questions.add(new Question(web, "239 滑动窗口最大值", tag));
    questions.add(new Question(web, "76 最小覆盖子串", tag));

    // 05-普通数组
    tag = "05-普通数组";
    questions.add(new Question(web, "53 最大子数组和", tag));
    questions.add(new Question(web, "56 合并区间", tag));
    questions.add(new Question(web, "189 轮转数组", tag));
    questions.add(new Question(web, "238 除自身以外数组的乘积", tag));
    questions.add(new Question(web, "41 缺失的第一个正数", tag));

    // 06-矩阵
    tag = "06-矩阵";
    questions.add(new Question(web, "73 矩阵置零", tag));
    questions.add(new Question(web, "54 螺旋矩阵", tag));
    questions.add(new Question(web, "48 旋转图像", tag));
    questions.add(new Question(web, "240 搜索二维矩阵II", tag));

    // 07-链表
    tag = "07-链表";
    questions.add(new Question(web, "160 相交链表", tag));
    questions.add(new Question(web, "206 反转链表", tag));
    questions.add(new Question(web, "234 回文链表", tag));
    questions.add(new Question(web, "141 环形链表", tag));
    questions.add(new Question(web, "142 环形链表II", tag));
    questions.add(new Question(web, "21 合并两个有序链表", tag));
    questions.add(new Question(web, "2 两数相加", tag));
    questions.add(new Question(web, "19 删除链表的倒数第N个结点", tag));
    questions.add(new Question(web, "24 两两交换链表中的节点", tag));
    questions.add(new Question(web, "25 K个一组翻转链表", tag));
    questions.add(new Question(web, "138 随机链表的复制", tag));
    questions.add(new Question(web, "148 排序链表", tag));
    questions.add(new Question(web, "23 合并K个升序链表", tag));
    questions.add(new Question(web, "146 LRU缓存", tag));

    // 08-二叉树
    tag = "08-二叉树";
    questions.add(new Question(web, "144 二叉树前序遍历", tag));
    questions.add(new Question(web, "94 二叉树中序遍历", tag));
    questions.add(new Question(web, "145 二叉树后序遍历", tag));
    questions.add(new Question(web, "104 二叉树的最大深度", tag));
    questions.add(new Question(web, "226 翻转二叉树", tag));
    questions.add(new Question(web, "101 对称二叉树", tag));
    questions.add(new Question(web, "543 二叉树的直径", tag));
    questions.add(new Question(web, "102 二叉树的层序遍历", tag));
    questions.add(new Question(web, "108 将有序数组转换为二叉搜索树", tag));
    questions.add(new Question(web, "98 验证二叉搜索树", tag));
    questions.add(new Question(web, "230 二叉搜索树中第K小的元素", tag));
    questions.add(new Question(web, "199 二叉树的右视图", tag));
    questions.add(new Question(web, "114 二叉树展开为链表", tag));
    questions.add(new Question(web, "105 从前序与中序遍历序列构造二叉树", tag));
    questions.add(new Question(web, "437 路径总和III", tag));
    questions.add(new Question(web, "236 二叉树的最近公共祖先", tag));
    questions.add(new Question(web, "124 二叉树中的最大路径和", tag));

    // 09-图论
    tag = "09-图论";
    questions.add(new Question(web, "200 岛屿数量", tag));
    questions.add(new Question(web, "994 腐烂的橘子", tag));
    questions.add(new Question(web, "207 课程表", tag));
    questions.add(new Question(web, "208 实现Trie（前缀树）", tag));

    // 10-回溯
    tag = "10-回溯";
    questions.add(new Question(web, "46 全排列", tag));
    questions.add(new Question(web, "78 子集", tag));
    questions.add(new Question(web, "17 电话号码的字母组合", tag));
    questions.add(new Question(web, "39 组合总和", tag));
    questions.add(new Question(web, "22 括号生成", tag));
    questions.add(new Question(web, "79 单词搜索", tag));
    questions.add(new Question(web, "131 分割回文串", tag));
    questions.add(new Question(web, "51 N皇后", tag));

    // 11-二分查找
    tag = "11-二分查找";
    questions.add(new Question(web, "35 搜索插入位置", tag));
    questions.add(new Question(web, "74 搜索二维矩阵", tag));
    questions.add(new Question(web, "34 在排序数组中查找元素的第一个和最后一个位置", tag));
    questions.add(new Question(web, "33 搜索旋转排序数组", tag));
    questions.add(new Question(web, "153 寻找旋转排序数组中的最小值", tag));
    questions.add(new Question(web, "4 寻找两个正序数组的中位数", tag));

    // 12-栈
    tag = "12-栈";
    questions.add(new Question(web, "20 有效的括号", tag));
    questions.add(new Question(web, "155 最小栈", tag));
    questions.add(new Question(web, "394 字符串解码", tag));
    questions.add(new Question(web, "739 每日温度", tag));
    questions.add(new Question(web, "84 柱状图中最大的矩形", tag));

    // 13-堆
    tag = "13-堆";
    questions.add(new Question(web, "215 数组中的第K个最大元素", tag));
    questions.add(new Question(web, "347 前K个高频元素", tag));
    questions.add(new Question(web, "295 数据流的中位数", tag));

    // 14-贪心算法
    tag = "14-贪心算法";
    questions.add(new Question(web, "121 买卖股票的最佳时机", tag));
    questions.add(new Question(web, "55 跳跃游戏", tag));
    questions.add(new Question(web, "45 跳跃游戏II", tag));
    questions.add(new Question(web, "763 划分字母区间", tag));

    // 15-动态规划
    tag = "15-动态规划";
    questions.add(new Question(web, "70 爬楼梯", tag));
    questions.add(new Question(web, "118 杨辉三角", tag));
    questions.add(new Question(web, "198 打家劫舍", tag));
    questions.add(new Question(web, "279 完全平方数", tag));
    questions.add(new Question(web, "322 零钱兑换", tag));
    questions.add(new Question(web, "139 单词拆分", tag));
    questions.add(new Question(web, "300 最长递增子序列", tag));
    questions.add(new Question(web, "152 乘积最大子数组", tag));
    questions.add(new Question(web, "416 分割等和子集", tag));
    questions.add(new Question(web, "32 最长有效括号", tag));

    // 16-多维动态规划
    tag = "16-多维动态规划";
    questions.add(new Question(web, "62 不同路径", tag));
    questions.add(new Question(web, "64 最小路径和", tag));
    questions.add(new Question(web, "5 最长回文子串", tag));
    questions.add(new Question(web, "1143 最长公共子序列", tag));
    questions.add(new Question(web, "72 编辑距离", tag));
    questions.add(new Question(web, "10 正则表达式匹配", tag));

    // 17-技巧
    tag = "17-技巧";
    questions.add(new Question(web, "136 只出现一次的数字", tag));
    questions.add(new Question(web, "169 多数元素", tag));
    questions.add(new Question(web, "75 颜色分类", tag));
    questions.add(new Question(web, "31 下一个排列", tag));
    questions.add(new Question(web, "287 寻找重复数", tag));

    return questions;
  }

  // 力扣热题Top100-指定某种标签下的题
  public static List<Question> buildLCTop100ByTags(Set<String> tags) {
    List<Question> questions = buildLCTop100();
    return questions.stream().filter(v -> tags.contains(v.getTag())).collect(Collectors.toList());
  }

  // 力扣热题Top100-难题和错题
  public static List<Question> buildLCTop100ByError() {
    Set<String> errorQuesString = new HashSet<>();
    errorQuesString.add("01-哈希 LC 1 两数之和");

    return buildLCTop100().stream().filter(v -> errorQuesString.contains(v.toString())).collect(Collectors.toList());
  }

  public static void main(String[] args) {
    System.out.println(buildLCTop100ByError());
  }
}
