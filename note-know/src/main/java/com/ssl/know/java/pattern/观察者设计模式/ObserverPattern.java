package com.ssl.know.java.pattern.观察者设计模式;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者设计模式 - 完整实现
 * <p>
 * 面试考点：
 * 1. 观察者模式的基本结构（主题 + 观察者）
 * 2. 推模型 vs 拉模型
 * 3. Spring 事件驱动模型（ApplicationEvent / ApplicationListener）
 * 4. Spring 事件的异步处理
 *
 * @author ssl
 */
public class ObserverPattern {

  public static void main(String[] args) {
    System.out.println("========== 1. 观察者模式基础 ==========");
    Subject subject = new Subject();
    subject.addObserver(new ConcreteObserver("观察者A"));
    subject.addObserver(new ConcreteObserver("观察者B"));
    subject.setState("状态已更新");

    System.out.println("\n========== 2. 模拟 Spring 事件驱动 ==========");
    MockApplicationContext context = new MockApplicationContext();
    context.addListener(new UserRegisterListener());
    context.addListener(new EmailSendListener());
    // 发布用户注册事件
    context.publishEvent(new UserRegisterEvent("张三"));
  }

  // ==================== 1. 观察者模式基础 ====================

  /**
   * 观察者接口
   */
  interface Observer {
    void update(String state);
  }

  /**
   * 主题（被观察者）
   * <p>
   * 考点1：观察者模式的核心角色？
   * 答案：
   * - Subject（主题）：维护观察者列表，状态变化时通知所有观察者
   * - Observer（观察者）：定义更新接口
   * - ConcreteSubject / ConcreteObserver：具体实现
   */
  static class Subject {
    private final List<Observer> observers = new ArrayList<>();
    private String state;

    public void addObserver(Observer observer) {
      observers.add(observer);
    }

    public void removeObserver(Observer observer) {
      observers.remove(observer);
    }

    public void setState(String state) {
      this.state = state;
      notifyObservers();
    }

    private void notifyObservers() {
      for (Observer observer : observers) {
        observer.update(state);
      }
    }
  }

  /**
   * 具体观察者
   */
  static class ConcreteObserver implements Observer {
    private final String name;

    public ConcreteObserver(String name) {
      this.name = name;
    }

    @Override
    public void update(String state) {
      System.out.println(name + " 收到通知，状态变更为: " + state);
    }
  }

  // ==================== 2. 模拟 Spring 事件驱动模型 ====================

  /**
   * 模拟 Spring 的 ApplicationEvent
   * <p>
   * Spring 事件三要素：
   * 1. 事件（ApplicationEvent）：继承 ApplicationEvent 或直接使用任意对象（Spring 4.2+）
   * 2. 发布者（ApplicationEventPublisher）：通过 publishEvent() 发布事件
   * 3. 监听者（ApplicationListener）：实现 onApplicationEvent() 或使用 @EventListener
   */
  static class UserRegisterEvent {
    private final String username;

    public UserRegisterEvent(String username) {
      this.username = username;
    }

    public String getUsername() {
      return username;
    }
  }

  /**
   * 模拟 Spring 的 ApplicationListener
   */
  interface MockApplicationListener<T> {
    void onEvent(T event);
  }

  /**
   * 监听器1：处理用户注册逻辑
   */
  static class UserRegisterListener implements MockApplicationListener<UserRegisterEvent> {
    @Override
    public void onEvent(UserRegisterEvent event) {
      System.out.println("[用户监听器] 用户注册成功: " + event.getUsername() + "，初始化用户配置");
    }
  }

  /**
   * 监听器2：发送欢迎邮件
   */
  static class EmailSendListener implements MockApplicationListener<UserRegisterEvent> {
    @Override
    public void onEvent(UserRegisterEvent event) {
      System.out.println("[邮件监听器] 向 " + event.getUsername() + " 发送欢迎邮件");
    }
  }

  /**
   * 模拟 Spring 的 ApplicationEventPublisher
   * <p>
   * 考点2：Spring 事件机制的执行流程？
   * 答案：
   * 1. 调用 applicationContext.publishEvent(event) 发布事件
   * 2. ApplicationEventMulticaster 将事件广播给所有匹配的监听器
   * 3. 每个监听器的 onApplicationEvent() 方法被调用
   * <p>
   * 考点3：Spring 事件是同步还是异步？
   * 答案：默认同步执行（在同一个线程中按顺序执行所有监听器）。
   * 可以通过 @Async + @EnableAsync 实现异步事件监听。
   */
  static class MockApplicationContext {
    private final List<MockApplicationListener<UserRegisterEvent>> listeners = new ArrayList<>();

    public void addListener(MockApplicationListener<UserRegisterEvent> listener) {
      listeners.add(listener);
    }

    public void publishEvent(UserRegisterEvent event) {
      System.out.println("[事件发布] 发布用户注册事件...");
      for (MockApplicationListener<UserRegisterEvent> listener : listeners) {
        listener.onEvent(event);
      }
    }
  }
}

/**
 * ==================== 面试考点总结 ====================
 * <p>
 * 【考点1】观察者模式的优缺点
 * - 优点：解耦（主题与观察者松耦合）、支持广播通信、符合开闭原则
 * - 缺点：观察者过多时通知耗时、可能引起循环依赖、无法知道观察者如何反应
 * <p>
 * 【考点2】推模型 vs 拉模型
 * - 推模型：主题把变化的数据直接推给观察者（update(state)）
 * - 拉模型：主题只通知"我变了"，观察者主动来拉取需要的数据（update(subject)）
 * - 推模型更简单，拉模型更灵活
 * <p>
 * 【考点3】Spring 事件驱动模型三要素
 * - 事件：ApplicationEvent（4.2 之前必须继承，之后任意对象都行）
 * - 发布：ApplicationEventPublisher.publishEvent()
 * - 监听：实现 ApplicationListener 接口 或 使用 @EventListener 注解
 * <p>
 * 【考点4】Spring 事件的同步 vs 异步
 * - 默认同步：所有监听器在发布线程中按顺序执行，一个异常会中断后续监听器
 * - 异步方式：@EventListener + @Async，需要 @EnableAsync 开启
 * <p>
 * 【考点5】Spring 事件的实际应用场景
 * - 用户注册后发送欢迎邮件、初始化配置（解耦主流程与附加操作）
 * - 订单创建后扣减库存、发送通知
 * - 领域驱动设计（DDD）中的领域事件
 * <p>
 * 【考点6】观察者模式 vs 发布订阅模式
 * - 观察者模式：主题直接持有观察者引用，两者松耦合但互相知道
 * - 发布订阅：通过消息中间件（Broker）解耦，发布者和订阅者完全不知道对方
 * - 消息队列（Kafka、RabbitMQ）就是发布订阅模式的典型应用
 */
