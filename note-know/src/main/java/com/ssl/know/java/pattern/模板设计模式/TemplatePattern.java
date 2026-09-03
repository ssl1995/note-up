package com.ssl.know.java.pattern.模板设计模式;

/**
 * 模板设计模式 - 完整实现
 * <p>
 * 面试考点：
 * 1. 模板方法模式的基本结构（抽象类定义骨架，子类实现细节）
 * 2. 钩子方法（Hook）的使用
 * 3. Spring 中的模板模式应用（JdbcTemplate、RestTemplate）
 *
 * @author ssl
 */
public class TemplatePattern {

  public static void main(String[] args) {
    System.out.println("========== 1. 模板方法模式：制作饮品 ==========");
    AbstractBeverage tea = new Tea();
    tea.makeBeverage();

    System.out.println("\n========== 2. 钩子方法：不加调料的咖啡 ==========");
    AbstractBeverage coffee = new Coffee(false);
    coffee.makeBeverage();

    System.out.println("\n========== 3. 模拟 JdbcTemplate 工作原理 ==========");
    MockJdbcTemplate jdbcTemplate = new MockJdbcTemplate();
    String result = jdbcTemplate.query("SELECT name FROM user WHERE id = 1",
        rs -> "模拟查询结果: 张三");
    System.out.println(result);
  }

  // ==================== 1. 模板方法模式 ====================

  /**
   * 抽象模板类：定义算法骨架
   * <p>
   * 考点1：模板方法模式的核心思想？
   * 答案：父类定义算法骨架（模板方法），将某些步骤延迟到子类实现。
   * 模板方法用 final 修饰防止子类修改算法骨架。
   */
  static abstract class AbstractBeverage {

    /**
     * 模板方法：定义制作饮品的算法骨架
     * final 修饰，防止子类篡改流程
     */
    public final void makeBeverage() {
      boilWater();        // 1. 烧水（公共步骤）
      brew();             // 2. 冲泡（子类实现）
      pourInCup();        // 3. 倒入杯中（公共步骤）
      if (needCondiments()) {  // 4. 钩子方法：是否加调料
        addCondiments();  //    加调料（子类实现）
      }
    }

    // 公共步骤：父类实现
    private void boilWater() {
      System.out.println("烧水");
    }

    private void pourInCup() {
      System.out.println("倒入杯中");
    }

    // 抽象步骤：交给子类实现
    protected abstract void brew();

    protected abstract void addCondiments();

    /**
     * 钩子方法（Hook）：子类可以覆盖，控制算法流程
     * <p>
     * 考点2：钩子方法的作用？
     * 答案：让子类有机会干预算法的某些步骤，默认返回 true，
     * 子类按需覆盖，实现"可选步骤"的效果。
     */
    protected boolean needCondiments() {
      return true;
    }
  }

  /**
   * 具体实现：茶
   */
  static class Tea extends AbstractBeverage {
    @Override
    protected void brew() {
      System.out.println("冲泡茶叶");
    }

    @Override
    protected void addCondiments() {
      System.out.println("加入柠檬");
    }
  }

  /**
   * 具体实现：咖啡（通过钩子方法控制是否加调料）
   */
  static class Coffee extends AbstractBeverage {
    private final boolean needCondiments;

    public Coffee(boolean needCondiments) {
      this.needCondiments = needCondiments;
    }

    @Override
    protected void brew() {
      System.out.println("冲泡咖啡粉");
    }

    @Override
    protected void addCondiments() {
      System.out.println("加入牛奶和糖");
    }

    @Override
    protected boolean needCondiments() {
      return needCondiments;
    }
  }

  // ==================== 2. 模拟 JdbcTemplate ====================

  /**
   * 模拟查询结果集
   */
  interface MockResultSet {
    // 模拟结果集
  }

  /**
   * 行映射器：将结果集映射为对象（回调接口）
   */
  @FunctionalInterface
  interface RowMapper<T> {
    T mapRow(MockResultSet rs);
  }

  /**
   * 模拟 JdbcTemplate
   * <p>
   * 考点3：JdbcTemplate 解决了什么问题？
   * 答案：传统 JDBC 代码有大量样板代码（获取连接、创建 Statement、
   * 处理异常、关闭资源），JdbcTemplate 用模板方法封装了这些固定流程，
   * 用户只需关注 SQL 和结果映射。
   * <p>
   * 考点4：JdbcTemplate 是模板方法模式还是回调模式？
   * 答案：两者结合。execute() 是模板方法，封装了获取连接、异常处理、
   * 释放资源的固定流程；RowMapper、PreparedStatementSetter 等是回调接口，
   * 让用户定制变化的部分。严格来说 Spring 的 Template 更偏向"模板+回调"组合。
   */
  static class MockJdbcTemplate {

    public <T> T query(String sql, RowMapper<T> rowMapper) {
      try {
        // 1. 获取连接（模板固定步骤）
        System.out.println("[JdbcTemplate] 获取数据库连接");
        // 2. 创建 Statement（模板固定步骤）
        System.out.println("[JdbcTemplate] 创建 Statement，执行SQL: " + sql);
        // 3. 执行查询（模板固定步骤）
        MockResultSet rs = new MockResultSet() {
        };
        // 4. 结果映射（回调用户代码，变化的部分）
        return rowMapper.mapRow(rs);
      } finally {
        // 5. 释放资源（模板固定步骤）
        System.out.println("[JdbcTemplate] 释放数据库连接");
      }
    }
  }
}

/**
 * ==================== 面试考点总结 ====================
 * <p>
 * 【考点1】模板方法模式的结构
 * - 抽象类：定义模板方法（final）+ 抽象方法（子类实现）+ 钩子方法（可选覆盖）
 * - 具体子类：实现抽象方法，按需覆盖钩子方法
 * <p>
 * 【考点2】模板方法 vs 策略模式
 * - 模板方法：基于继承，算法骨架固定，部分步骤由子类实现（编译期确定）
 * - 策略模式：基于组合，整个算法可替换，运行时动态切换
 * <p>
 * 【考点3】Spring 中的模板模式
 * - JdbcTemplate：封装 JDBC 样板代码
 * - RestTemplate：封装 HTTP 请求样板代码
 * - RedisTemplate：封装 Redis 操作样板代码
 * - JmsTemplate、JpaTemplate 等
 * 共同点：都以 Template 结尾，封装资源获取、异常处理、资源释放的固定流程
 * <p>
 * 【考点4】模板方法模式的优缺点
 * - 优点：代码复用（公共逻辑在父类）、反向控制（父类调用子类，好莱坞原则）
 * - 缺点：每个不同实现都需要一个子类，类数量可能膨胀
 * <p>
 * 【考点5】好莱坞原则
 * - "不要调用我们，我们会调用你"（Don't call us, we'll call you）
 * - 父类控制算法流程，在适当的时候调用子类实现的方法
 * - 防止"依赖腐败"，高层组件控制低层组件
 */
