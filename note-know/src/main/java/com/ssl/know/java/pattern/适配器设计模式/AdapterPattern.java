package com.ssl.know.java.pattern.适配器设计模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 适配器设计模式 - 完整实现
 * <p>
 * 面试考点：
 * 1. 类适配器（继承）vs 对象适配器（组合）
 * 2. Spring MVC 中的适配器模式（HandlerAdapter）
 * 3. Spring AOP 中的适配器模式（AdvisorAdapter）
 *
 * @author ssl
 */
public class AdapterPattern {

  public static void main(String[] args) {
    System.out.println("========== 1. 对象适配器：充电头适配 ==========");
    // 国标的插座，但手机需要 Type-C 充电
    ChinaSocket chinaSocket = new ChinaSocket();
    TypeCCharger adapter = new SocketAdapter(chinaSocket);
    adapter.chargeWithTypeC();

    System.out.println("\n========== 2. 模拟 Spring MVC HandlerAdapter ==========");
    // 模拟 DispatcherServlet 根据不同类型的 Controller 选择适配器
    MockDispatcherServlet dispatcher = new MockDispatcherServlet();
    dispatcher.addAdapter(new SimpleControllerAdapter());
    dispatcher.addAdapter(new AnnotationControllerAdapter());

    // 模拟请求到达不同类型的 Controller
    dispatcher.doDispatch(new SimpleController());
    dispatcher.doDispatch(new AnnotationController());
  }

  // ==================== 1. 对象适配器 ====================

  /**
   * 已有的接口：国标插座（被适配者 Adaptee）
   */
  static class ChinaSocket {
    public void chargeWithGB() {
      System.out.println("使用国标插座充电");
    }
  }

  /**
   * 目标接口：Type-C 充电器（Target）
   */
  interface TypeCCharger {
    void chargeWithTypeC();
  }

  /**
   * 对象适配器：将国标插座适配为 Type-C 充电
   * <p>
   * 考点1：类适配器 vs 对象适配器？
   * 答案：
   * - 类适配器：通过继承被适配者实现，Java 单继承限制，不灵活
   * - 对象适配器：通过组合持有被适配者引用，更灵活（推荐）
   * <p>
   * 考点2：适配器模式的角色？
   * - Target（目标接口）：客户端期望的接口
   * - Adaptee（被适配者）：已有的、需要被适配的类
   * - Adapter（适配器）：将 Adaptee 适配为 Target
   */
  static class SocketAdapter implements TypeCCharger {
    private final ChinaSocket chinaSocket;

    public SocketAdapter(ChinaSocket chinaSocket) {
      this.chinaSocket = chinaSocket;
    }

    @Override
    public void chargeWithTypeC() {
      System.out.println("[适配器] Type-C 接口转国标插座");
      chinaSocket.chargeWithGB();
    }
  }

  // ==================== 2. 模拟 Spring MVC HandlerAdapter ====================

  /**
   * 模拟不同类型的 Controller
   * <p>
   * Spring MVC 中 Controller 有多种形态：
   * - 实现 Controller 接口
   * - 实现 HttpRequestHandler 接口
   * - 使用 @Controller + @RequestMapping 注解
   * DispatcherServlet 通过 HandlerAdapter 适配不同类型的 Controller
   */
  interface MockController {
  }

  static class SimpleController implements MockController {
    public void handleRequest() {
      System.out.println("SimpleController 处理请求（实现 Controller 接口）");
    }
  }

  static class AnnotationController implements MockController {
    public void handleRequest() {
      System.out.println("AnnotationController 处理请求（@RequestMapping 注解方式）");
    }
  }

  /**
   * 模拟 HandlerAdapter 接口
   * <p>
   * 考点3：Spring MVC 为什么需要 HandlerAdapter？
   * 答案：因为 Controller 有多种实现方式（接口、注解、HttpRequestHandler），
   * DispatcherServlet 无法直接调用，通过 HandlerAdapter 统一适配，
   * 每种 Controller 对应一个 Adapter，DispatcherServlet 只需面向 Adapter 编程。
   * <p>
   * 考点4：HandlerAdapter 的核心方法？
   * - supports(handler)：判断是否支持该类型的 handler
   * - handle(request, response, handler)：调用 handler 处理请求
   */
  interface MockHandlerAdapter {
    boolean supports(MockController controller);

    void handle(MockController controller);
  }

  static class SimpleControllerAdapter implements MockHandlerAdapter {
    @Override
    public boolean supports(MockController controller) {
      return controller instanceof SimpleController;
    }

    @Override
    public void handle(MockController controller) {
      System.out.println("[适配器] SimpleControllerAdapter 适配调用");
      ((SimpleController) controller).handleRequest();
    }
  }

  static class AnnotationControllerAdapter implements MockHandlerAdapter {
    @Override
    public boolean supports(MockController controller) {
      return controller instanceof AnnotationController;
    }

    @Override
    public void handle(MockController controller) {
      System.out.println("[适配器] AnnotationControllerAdapter 适配调用");
      ((AnnotationController) controller).handleRequest();
    }
  }

  /**
   * 模拟 DispatcherServlet
   */
  static class MockDispatcherServlet {
    private final List<MockHandlerAdapter> adapters = new ArrayList<>();

    public void addAdapter(MockHandlerAdapter adapter) {
      adapters.add(adapter);
    }

    public void doDispatch(MockController controller) {
      for (MockHandlerAdapter adapter : adapters) {
        if (adapter.supports(controller)) {
          adapter.handle(controller);
          return;
        }
      }
      System.out.println("未找到匹配的 HandlerAdapter");
    }
  }
}

/**
 * ==================== 面试考点总结 ====================
 * <p>
 * 【考点1】适配器模式的三种形式
 * - 类适配器：继承被适配者，实现目标接口（Java 单继承限制）
 * - 对象适配器：组合被适配者，实现目标接口（推荐）
 * - 接口适配器（缺省适配）：抽象类实现接口所有方法（空实现），子类按需覆盖
 * <p>
 * 【考点2】适配器 vs 装饰器 vs 代理
 * - 适配器：改变接口，让不兼容的类能一起工作
 * - 装饰器：不改变接口，增强原有功能
 * - 代理：不改变接口，控制对对象的访问
 * <p>
 * 【考点3】Spring MVC 中的适配器模式
 * - HandlerAdapter：适配各种类型的 Controller
 * - SimpleControllerHandlerAdapter：适配实现 Controller 接口的
 * - RequestMappingHandlerAdapter：适配 @RequestMapping 注解的
 * - HttpRequestHandlerAdapter：适配 HttpRequestHandler 接口的
 * <p>
 * 【考点4】Spring AOP 中的适配器模式
 * - AdvisorAdapter：将不同类型的 Advice（MethodBeforeAdvice、
 *   AfterReturningAdvice、ThrowsAdvice）适配为统一的 MethodInterceptor
 * - 这样 AOP 框架只需面向 MethodInterceptor 编程，不用关心具体 Advice 类型
 * <p>
 * 【考点5】适配器模式的应用场景
 * - 系统需要使用已有的类，但接口不匹配
 * - 统一多个不同来源的接口（如支付网关适配微信、支付宝、银联）
 * - 老系统改造，新接口适配旧接口
 */
