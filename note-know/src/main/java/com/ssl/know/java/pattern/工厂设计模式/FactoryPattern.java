package com.ssl.know.java.pattern.工厂设计模式;

/**
 * 工厂设计模式 - 完整实现
 * <p>
 * 面试考点：
 * 1. 简单工厂模式（静态工厂，违反开闭原则）
 * 2. 工厂方法模式（每个产品一个工厂，符合开闭原则）
 * 3. 抽象工厂模式（产品族，一次创建一族产品）
 * 4. Spring 中的工厂模式应用（BeanFactory / ApplicationContext）
 *
 * @author ssl
 */
public class FactoryPattern {

  public static void main(String[] args) {
    System.out.println("========== 1. 简单工厂模式 ==========");
    Product productA = SimpleFactory.createProduct("A");
    productA.use();
    Product productB = SimpleFactory.createProduct("B");
    productB.use();

    System.out.println("\n========== 2. 工厂方法模式 ==========");
    ProductFactory factoryA = new ProductAFactory();
    factoryA.create().use();
    ProductFactory factoryB = new ProductBFactory();
    factoryB.create().use();

    System.out.println("\n========== 3. 抽象工厂模式 ==========");
    AbstractFactory huaweiFactory = new HuaweiFactory();
    huaweiFactory.createPhone().call();
    huaweiFactory.createComputer().work();
    AbstractFactory appleFactory = new AppleFactory();
    appleFactory.createPhone().call();
    appleFactory.createComputer().work();
  }

  // ==================== 产品定义 ====================

  /**
   * 产品接口
   */
  interface Product {
    void use();
  }

  static class ProductA implements Product {
    @Override
    public void use() {
      System.out.println("使用产品A");
    }
  }

  static class ProductB implements Product {
    @Override
    public void use() {
      System.out.println("使用产品B");
    }
  }

  // ==================== 1. 简单工厂模式 ====================

  /**
   * 简单工厂模式（静态工厂）
   * <p>
   * 优点：客户端无需关心创建细节，通过参数获取对象
   * 缺点：新增产品需要修改工厂代码，违反开闭原则
   * <p>
   * 考点1：简单工厂为什么违反开闭原则？
   * 答案：每新增一种产品，都要修改 createProduct 的 if-else 分支，
   * 对扩展开放、对修改关闭的原则被破坏。
   * <p>
   * Spring 应用：BeanFactory 根据 beanName 创建 Bean，本质就是简单工厂。
   */
  static class SimpleFactory {
    public static Product createProduct(String type) {
      return switch (type) {
        case "A" -> new ProductA();
        case "B" -> new ProductB();
        default -> throw new IllegalArgumentException("未知产品类型: " + type);
      };
    }
  }

  // ==================== 2. 工厂方法模式 ====================

  /**
   * 工厂方法模式
   * <p>
   * 优点：新增产品只需新增工厂类，符合开闭原则
   * 缺点：每增加一个产品就要增加一个工厂类，类数量膨胀
   * <p>
   * 考点2：工厂方法 vs 简单工厂？
   * 答案：简单工厂把创建逻辑集中在一个工厂中，通过参数判断；
   * 工厂方法把创建逻辑下沉到子类，每个产品对应一个工厂，扩展时不修改原有代码。
   * <p>
   * Spring 应用：FactoryBean 接口，实现 getObject() 返回定制 Bean。
   */
  interface ProductFactory {
    Product create();
  }

  static class ProductAFactory implements ProductFactory {
    @Override
    public Product create() {
      return new ProductA();
    }
  }

  static class ProductBFactory implements ProductFactory {
    @Override
    public Product create() {
      return new ProductB();
    }
  }

  // ==================== 3. 抽象工厂模式 ====================

  /**
   * 抽象工厂模式（产品族）
   * <p>
   * 适用场景：需要创建一族相互关联的产品（如华为全家桶：手机+电脑）
   * 优点：保证一族产品的一致性，切换产品族只需切换工厂
   * 缺点：产品族中新增产品需要修改所有工厂，违反开闭原则
   * <p>
   * 考点3：抽象工厂 vs 工厂方法？
   * 答案：工厂方法针对单一产品等级（只造手机）；
   * 抽象工厂针对产品族（手机+电脑一整套），一个工厂能创建多种产品。
   */
  interface Phone {
    void call();
  }

  interface Computer {
    void work();
  }

  static class HuaweiPhone implements Phone {
    @Override
    public void call() {
      System.out.println("使用华为手机打电话");
    }
  }

  static class HuaweiComputer implements Computer {
    @Override
    public void work() {
      System.out.println("使用华为电脑办公");
    }
  }

  static class ApplePhone implements Phone {
    @Override
    public void call() {
      System.out.println("使用iPhone打电话");
    }
  }

  static class AppleComputer implements Computer {
    @Override
    public void work() {
      System.out.println("使用Mac办公");
    }
  }

  /**
   * 抽象工厂：定义创建一族产品的接口
   */
  interface AbstractFactory {
    Phone createPhone();

    Computer createComputer();
  }

  static class HuaweiFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
      return new HuaweiPhone();
    }

    @Override
    public Computer createComputer() {
      return new HuaweiComputer();
    }
  }

  static class AppleFactory implements AbstractFactory {
    @Override
    public Phone createPhone() {
      return new ApplePhone();
    }

    @Override
    public Computer createComputer() {
      return new AppleComputer();
    }
  }
}

/**
 * ==================== 面试考点总结 ====================
 * <p>
 * 【考点1】三种工厂模式的对比
 * - 简单工厂：一个工厂 + 参数判断，违反开闭原则，适合产品少的场景
 * - 工厂方法：一个产品一个工厂，符合开闭原则，类数量多
 * - 抽象工厂：一个工厂创建一族产品，适合产品族场景，新增产品等级困难
 * <p>
 * 【考点2】Spring 中的工厂模式
 * - BeanFactory：Spring 最底层的工厂接口，延迟加载 Bean
 * - ApplicationContext：继承 BeanFactory，功能更强，启动时预实例化单例 Bean
 * - FactoryBean：特殊的 Bean，实现该接口可以定制 Bean 的创建过程
 * <p>
 * 【考点3】BeanFactory vs FactoryBean
 * - BeanFactory：是 Spring 容器本身，负责创建和管理所有 Bean
 * - FactoryBean：是一个特殊的 Bean，getObject() 返回的对象才是最终 Bean
 * <p>
 * 【考点4】工厂模式的优点
 * - 解耦：客户端与具体产品类解耦，只依赖抽象接口
 * - 复用：创建逻辑集中管理，避免重复代码
 * - 扩展：新增产品无需修改客户端代码
 * <p>
 * 【考点5】工厂模式的应用场景
 * - 对象创建逻辑复杂（如需要读取配置、依赖注入）
 * - 需要根据不同条件创建不同实现类
 * - Spring 容器创建 Bean、日志框架 LoggerFactory
 */
