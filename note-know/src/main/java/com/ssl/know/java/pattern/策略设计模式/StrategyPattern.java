package com.ssl.know.java.pattern.策略设计模式;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略设计模式 - 完整实现
 * <p>
 * 面试考点：
 * 1. 策略模式的基本结构（策略接口 + 具体策略 + 上下文）
 * 2. 策略模式消除 if-else
 * 3. 策略模式 + 工厂模式组合
 * 4. Spring 中的策略模式（Resource 接口）
 *
 * @author ssl
 */
public class StrategyPattern {

  public static void main(String[] args) {
    System.out.println("========== 1. 策略模式基础：支付方式 ==========");
    PayContext context = new PayContext(new AliPayStrategy());
    context.pay(100);

    // 运行时切换策略
    context.setStrategy(new WeChatPayStrategy());
    context.pay(200);

    System.out.println("\n========== 2. 策略 + 工厂：消除 if-else ==========");
    // 通过工厂自动选择策略，客户端无需 if-else
    PayStrategyFactory.pay("ali", 100);
    PayStrategyFactory.pay("wechat", 200);
    PayStrategyFactory.pay("union", 300);

    System.out.println("\n========== 3. 模拟 Spring Resource 接口 ==========");
    MockResource classPathResource = new ClassPathResource("config/application.yml");
    MockResource fileResource = new FileSystemResource("/data/config/app.yml");
    MockResource urlResource = new UrlResource("https://example.com/config.yml");
    System.out.println(classPathResource.load());
    System.out.println(fileResource.load());
    System.out.println(urlResource.load());
  }

  // ==================== 1. 策略模式基础 ====================

  /**
   * 策略接口：支付策略
   * <p>
   * 考点1：策略模式的核心思想？
   * 答案：定义一系列算法（策略），将每个算法封装起来，
   * 使它们可以互相替换。策略模式让算法的变化独立于使用算法的客户端。
   */
  interface PayStrategy {
    void pay(double amount);
  }

  static class AliPayStrategy implements PayStrategy {
    @Override
    public void pay(double amount) {
      System.out.println("使用支付宝支付: " + amount + " 元");
    }
  }

  static class WeChatPayStrategy implements PayStrategy {
    @Override
    public void pay(double amount) {
      System.out.println("使用微信支付: " + amount + " 元");
    }
  }

  static class UnionPayStrategy implements PayStrategy {
    @Override
    public void pay(double amount) {
      System.out.println("使用银联支付: " + amount + " 元");
    }
  }

  /**
   * 上下文：持有策略引用，运行时切换策略
   */
  static class PayContext {
    private PayStrategy strategy;

    public PayContext(PayStrategy strategy) {
      this.strategy = strategy;
    }

    public void setStrategy(PayStrategy strategy) {
      this.strategy = strategy;
    }

    public void pay(double amount) {
      strategy.pay(amount);
    }
  }

  // ==================== 2. 策略 + 工厂：消除 if-else ====================

  /**
   * 策略工厂：根据类型自动选择策略
   * <p>
   * 考点2：策略模式如何消除 if-else？
   * 答案：将策略注册到 Map 中，通过 key 获取对应策略，
   * 替代传统的 if-else / switch 分支判断。
   * <p>
   * 考点3：Spring 中如何实现策略的自动注册？
   * 答案：
   * 1. 所有策略实现类标注 @Component
   * 2. 通过 Map<String, PayStrategy> 注入（key 是 beanName）
   * 3. 或使用 ApplicationContext.getBeansOfType() 获取所有实现
   */
  static class PayStrategyFactory {
    private static final Map<String, PayStrategy> STRATEGY_MAP = new HashMap<>();

    static {
      STRATEGY_MAP.put("ali", new AliPayStrategy());
      STRATEGY_MAP.put("wechat", new WeChatPayStrategy());
      STRATEGY_MAP.put("union", new UnionPayStrategy());
    }

    public static void pay(String type, double amount) {
      PayStrategy strategy = STRATEGY_MAP.get(type);
      if (strategy == null) {
        throw new IllegalArgumentException("不支持的支付方式: " + type);
      }
      strategy.pay(amount);
    }
  }

  // ==================== 3. 模拟 Spring Resource 接口 ====================

  /**
   * 模拟 Spring 的 Resource 接口
   * <p>
   * 考点4：Spring 中 Resource 接口的策略模式体现？
   * 答案：Resource 是资源访问的抽象接口，不同实现类对应不同的资源访问策略：
   * - ClassPathResource：从类路径加载
   * - FileSystemResource：从文件系统加载
   * - UrlResource：从 URL 加载
   * - ServletContextResource：从 Servlet 上下文加载
   * 客户端面向 Resource 接口编程，具体策略由实现类决定。
   */
  interface MockResource {
    String load();
  }

  static class ClassPathResource implements MockResource {
    private final String path;

    ClassPathResource(String path) {
      this.path = path;
    }

    @Override
    public String load() {
      return "[ClassPathResource] 从类路径加载: " + path;
    }
  }

  static class FileSystemResource implements MockResource {
    private final String path;

    FileSystemResource(String path) {
      this.path = path;
    }

    @Override
    public String load() {
      return "[FileSystemResource] 从文件系统加载: " + path;
    }
  }

  static class UrlResource implements MockResource {
    private final String url;

    UrlResource(String url) {
      this.url = url;
    }

    @Override
    public String load() {
      return "[UrlResource] 从URL加载: " + url;
    }
  }
}

/**
 * ==================== 面试考点总结 ====================
 * <p>
 * 【考点1】策略模式的结构
 * - Strategy（策略接口）：定义算法族
 * - ConcreteStrategy（具体策略）：实现具体算法
 * - Context（上下文）：持有策略引用，运行时切换
 * <p>
 * 【考点2】策略模式 vs 模板方法模式
 * - 策略模式：基于组合，整个算法可替换，运行时切换（对象级别）
 * - 模板方法：基于继承，算法骨架固定，部分步骤可替换（类级别）
 * - 策略模式更灵活，模板方法更简洁
 * <p>
 * 【考点3】策略模式的优缺点
 * - 优点：消除 if-else、符合开闭原则、算法可独立扩展和测试
 * - 缺点：客户端需要知道所有策略、策略类数量可能膨胀
 * <p>
 * 【考点4】Spring 中的策略模式
 * - Resource 接口：ClassPathResource / FileSystemResource / UrlResource
 * - InstantiationStrategy：Bean 实例化策略（反射 / CGLIB）
 * - HandlerMapping：根据请求选择不同的 Handler
 * <p>
 * 【考点5】策略模式 + Spring 的最佳实践
 * - 策略接口标注 @Component 让 Spring 管理
 * - 通过 Map<String, Strategy> 注入所有实现（key = beanName）
 * - 业务代码根据类型从 Map 中获取策略，完全消除 if-else
 * <p>
 * 【考点6】策略模式的应用场景
 * - 支付方式选择（支付宝、微信、银联）
 * - 文件解析（CSV、Excel、JSON）
 * - 排序算法切换、压缩算法切换
 * - 表单校验规则
 */
