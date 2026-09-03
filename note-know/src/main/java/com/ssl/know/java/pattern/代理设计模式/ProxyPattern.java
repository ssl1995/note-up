package com.ssl.know.java.pattern.代理设计模式;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理设计模式 - 完整实现
 * <p>
 * 面试考点：
 * 1. 静态代理（编译期确定，代理类手写）
 * 2. JDK 动态代理（基于接口，反射生成）
 * 3. CGLIB 动态代理（基于继承，字节码生成）
 * 4. Spring AOP 如何选择代理方式
 *
 * @author ssl
 */
public class ProxyPattern {

  public static void main(String[] args) {
    System.out.println("========== 1. 静态代理 ==========");
    UserService staticProxy = new UserServiceStaticProxy(new UserServiceImpl());
    staticProxy.addUser("张三");

    System.out.println("\n========== 2. JDK 动态代理 ==========");
    UserService jdkProxy = (UserService) Proxy.newProxyInstance(
        UserService.class.getClassLoader(),
        new Class<?>[]{UserService.class},
        new LogInvocationHandler(new UserServiceImpl())
    );
    jdkProxy.addUser("李四");

    System.out.println("\n========== 3. CGLIB 动态代理（原理演示） ==========");
    // CGLIB 通过继承目标类生成子类代理，无需接口
    // Spring 中需要引入 cglib 依赖，此处用代码演示原理
    OrderService orderService = new OrderService();
    OrderServiceCglibProxy cglibProxy = new OrderServiceCglibProxy(orderService);
    cglibProxy.createOrder("订单001");
  }

  // ==================== 目标接口与实现 ====================

  /**
   * 目标接口
   */
  interface UserService {
    void addUser(String name);
  }

  /**
   * 目标实现类
   */
  static class UserServiceImpl implements UserService {
    @Override
    public void addUser(String name) {
      System.out.println("添加用户: " + name);
    }
  }

  // ==================== 1. 静态代理 ====================

  /**
   * 静态代理
   * <p>
   * 优点：简单直观，不修改目标类就能增强功能
   * 缺点：每个目标类都要手写一个代理类，接口新增方法时代理类也要改
   * <p>
   * 考点1：静态代理的缺点？
   * 答案：代理类与目标类强耦合，一个接口一个代理类；
   * 接口方法变更时代理类必须同步修改，无法复用。
   */
  static class UserServiceStaticProxy implements UserService {
    private final UserService target;

    public UserServiceStaticProxy(UserService target) {
      this.target = target;
    }

    @Override
    public void addUser(String name) {
      System.out.println("[静态代理] 前置增强：开启事务");
      target.addUser(name);
      System.out.println("[静态代理] 后置增强：提交事务");
    }
  }

  // ==================== 2. JDK 动态代理 ====================

  /**
   * JDK 动态代理
   * <p>
   * 原理：运行时通过反射动态生成实现接口的代理类（$Proxy0）
   * 要求：目标类必须实现接口
   * <p>
   * 考点2：JDK 动态代理为什么必须基于接口？
   * 答案：生成的代理类已经继承了 Proxy 类，Java 单继承限制，
   * 只能通过实现接口来代理目标类。
   * <p>
   * 考点3：JDK 动态代理的核心类？
   * 答案：Proxy（创建代理对象）+ InvocationHandler（定义增强逻辑）。
   * 所有方法调用都会被转发到 invoke() 方法统一处理。
   */
  static class LogInvocationHandler implements InvocationHandler {
    private final Object target;

    public LogInvocationHandler(Object target) {
      this.target = target;
    }

    /**
     * @param proxy  代理对象
     * @param method 被调用的方法
     * @param args   方法参数
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("[JDK动态代理] 前置增强：方法=" + method.getName());
      Object result = method.invoke(target, args);
      System.out.println("[JDK动态代理] 后置增强：记录日志");
      return result;
    }
  }

  // ==================== 3. CGLIB 动态代理（原理演示） ====================

  /**
   * 目标类（不实现接口）
   */
  static class OrderService {
    public void createOrder(String orderNo) {
      System.out.println("创建订单: " + orderNo);
    }
  }

  /**
   * CGLIB 动态代理（原理演示）
   * <p>
   * 原理：通过 ASM 字节码技术生成目标类的子类，重写父类方法实现增强
   * 要求：目标类不能被 final 修饰，方法不能被 final 修饰
   * <p>
   * 实际使用（需引入 cglib 依赖）：
   * <pre>
   * Enhancer enhancer = new Enhancer();
   * enhancer.setSuperclass(OrderService.class);
   * enhancer.setCallback((MethodInterceptor) (obj, method, args, methodProxy) -> {
   *     System.out.println("前置增强");
   *     Object result = methodProxy.invokeSuper(obj, args);
   *     System.out.println("后置增强");
   *     return result;
   * });
   * OrderService proxy = (OrderService) enhancer.create();
   * </pre>
   * <p>
   * 考点4：CGLIB 为什么不能代理 final 类/final 方法？
   * 答案：CGLIB 通过继承生成子类实现代理，final 类不能被继承，
   * final 方法不能被重写，所以无法代理。
   */
  static class OrderServiceCglibProxy extends OrderService {
    private final OrderService target;

    public OrderServiceCglibProxy(OrderService target) {
      this.target = target;
    }

    @Override
    public void createOrder(String orderNo) {
      System.out.println("[CGLIB代理] 前置增强：校验权限");
      target.createOrder(orderNo);
      System.out.println("[CGLIB代理] 后置增强：发送消息");
    }
  }
}

/**
 * ==================== 面试考点总结 ====================
 * <p>
 * 【考点1】静态代理 vs 动态代理
 * - 静态代理：编译期就确定了代理类，代码手写，不够灵活
 * - 动态代理：运行期动态生成代理类，一个 InvocationHandler 可以代理所有接口
 * <p>
 * 【考点2】JDK 动态代理 vs CGLIB 动态代理
 * - JDK：基于接口，反射机制生成，目标类必须实现接口
 * - CGLIB：基于继承，ASM 字节码生成，目标类无需接口，但不能代理 final
 * - 性能：JDK 生成代理快、调用慢；CGLIB 生成代理慢、调用快（版本差异已缩小）
 * <p>
 * 【考点3】Spring AOP 如何选择代理方式？
 * - 目标类实现了接口 → 默认使用 JDK 动态代理
 * - 目标类没有实现接口 → 使用 CGLIB 代理
 * - 可以通过 proxyTargetClass = true 强制使用 CGLIB
 * <p>
 * 【考点4】Spring AOP 的底层实现
 * - 核心就是动态代理：在 Bean 初始化后（BeanPostProcessor）创建代理对象
 * - 切面（Aspect）+ 通知（Advice）+ 切点（Pointcut）织入代理逻辑
 * <p>
 * 【考点5】为什么 Spring AOP 中自调用（this 调用）不会触发增强？
 * - 因为自调用走的是目标对象本身，而不是代理对象
 * - 解决方案：注入自身代理对象、使用 AopContext.currentProxy()
 * <p>
 * 【考点6】代理模式的应用场景
 * - Spring AOP：事务、日志、权限、缓存
 * - MyBatis：Mapper 接口的 JDK 动态代理
 * - RPC 框架：Dubbo 远程调用的代理
 */
