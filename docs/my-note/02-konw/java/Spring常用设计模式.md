# Spring 中常用的设计模式（面试总结）
## 一、工厂模式
**Spring 应用**：Spring 容器本质是一个大工厂，通过 BeanFactory、ApplicationContext 创建 Bean 对象。
**三种工厂模式对比**：
| 类型 | 核心思路 | 优点 | 缺点 | 适用场景 |
|------|---------|------|------|---------|
| 简单工厂 | 一个工厂 + 参数判断 | 简单直观 | 违反开闭原则 | 产品少且固定 |
| 工厂方法 | 每个产品一个工厂 | 符合开闭原则 | 类数量膨胀 | 产品需频繁扩展 |
| 抽象工厂 | 一个工厂造一族产品 | 保证产品族一致性 | 新增产品等级困难 | 产品族场景 |
**面试考点**：
- BeanFactory vs FactoryBean：BeanFactory 是 Spring 容器本身，负责创建和管理所有 Bean；FactoryBean 是一个特殊 Bean，getObject() 返回的对象才是最终 Bean。
- BeanFactory 延迟加载 Bean；ApplicationContext 启动时预实例化单例 Bean。
## 二、代理模式
**Spring 应用**：Spring AOP 功能通过代理模式实现，分为 JDK 动态代理和 CGLIB 动态代理。
**JDK vs CGLIB 对比**：
| 对比项 | JDK 动态代理 | CGLIB 动态代理 |
|--------|-------------|---------------|
| 原理 | 反射生成接口实现类 | ASM 字节码生成子类 |
| 要求 | 目标类必须实现接口 | 目标类不能是 final |
| 生成速度 | 快 | 慢 |
| 调用速度 | 慢（反射调用） | 快（FastClass 索引） |
**面试考点**：
- Spring AOP 选择策略：目标类实现接口 → JDK 代理；无接口 → CGLIB；proxyTargetClass=true 强制 CGLIB。
- 自调用不生效：this 调用走的是目标对象本身而非代理对象，解决方案：注入自身代理或 AopContext.currentProxy()。
- 代理模式应用：Spring AOP（事务/日志/权限）、MyBatis Mapper 代理、Dubbo RPC 代理。
## 三、单例模式
**Spring 应用**：Spring 中的 Bean 默认都是单例的，有利于容器对 Bean 的管理。
**六种实现方式对比**：
| 方式 | 线程安全 | 延迟加载 | 防反射 | 防序列化 | 推荐度 |
|------|---------|---------|--------|---------|--------|
| 饿汉式 | ✅ | ❌ | ❌ | ❌ | ⭐⭐ |
| 懒汉式 | ❌ | ✅ | ❌ | ❌ | ⭐ |
| 同步懒汉 | ✅ | ✅ | ❌ | ❌ | ⭐⭐ |
| DCL | ✅ | ✅ | ❌ | ❌ | ⭐⭐⭐⭐ |
| 静态内部类 | ✅ | ✅ | ❌ | ❌ | ⭐⭐⭐⭐ |
| 枚举 | ✅ | ❌ | ✅ | ✅ | ⭐⭐⭐⭐⭐ |
**面试考点**：
- DCL 为什么需要 volatile：防止指令重排序，new 对象分三步（分配内存→初始化→赋值引用），重排可能导致引用指向未初始化对象。
- 枚举单例为什么是最佳实践：天然线程安全、防反射（Constructor.newInstance 检查枚举直接抛异常）、防序列化（JVM 特殊处理）。
- Spring 单例 vs 单例模式：Spring 单例是容器级别（每个容器一个实例），单例模式是 JVM 级别（一个类一个实例）。
## 四、模板模式
**Spring 应用**：JdbcTemplate、RestTemplate、RedisTemplate 等以 Template 结尾的模板类。
**核心结构**：抽象类定义算法骨架（final 模板方法）→ 抽象方法由子类实现 → 钩子方法（Hook）控制可选步骤。
**面试考点**：
- 模板方法 vs 策略模式：模板方法基于继承，算法骨架固定部分步骤可替换（编译期确定）；策略模式基于组合，整个算法可替换运行时切换。
- JdbcTemplate 解决的问题：封装 JDBC 样板代码（获取连接、创建 Statement、异常处理、释放资源），用户只需关注 SQL 和结果映射。
- 好莱坞原则："不要调用我们，我们会调用你"，父类控制流程，在适当时机调用子类实现。
## 五、观察者模式
**Spring 应用**：Spring 事件驱动模型（ApplicationEvent / ApplicationListener / ApplicationEventPublisher）。
**Spring 事件三要素**：
| 要素 | 说明 |
|------|------|
| 事件 | ApplicationEvent（4.2+ 支持任意对象） |
| 发布 | ApplicationEventPublisher.publishEvent() |
| 监听 | 实现 ApplicationListener 或使用 @EventListener |
**面试考点**：
- 同步 vs 异步：默认同步执行（同一线程按顺序执行所有监听器），@Async + @EnableAsync 实现异步。
- 观察者 vs 发布订阅：观察者模式中主题直接持有观察者引用；发布订阅通过消息中间件（Broker）完全解耦，Kafka/RabbitMQ 是典型应用。
- 应用场景：用户注册后发送邮件/初始化配置、订单创建后扣库存/发通知、DDD 领域事件。
## 六、适配器模式
**Spring 应用**：Spring AOP 的 Advice 适配（AdvisorAdapter）、Spring MVC 的 Controller 适配（HandlerAdapter）。
**Spring MVC HandlerAdapter 适配器**：
| 适配器 | 适配的 Controller 类型 |
|--------|----------------------|
| SimpleControllerHandlerAdapter | 实现 Controller 接口 |
| RequestMappingHandlerAdapter | @RequestMapping 注解 |
| HttpRequestHandlerAdapter | 实现 HttpRequestHandler 接口 |
**面试考点**：
- 为什么需要 HandlerAdapter：Controller 有多种实现方式，DispatcherServlet 无法直接调用，通过 HandlerAdapter 统一适配，面向 Adapter 编程。
- 适配器 vs 装饰器 vs 代理：适配器改变接口让不兼容类协同工作；装饰器不改接口增强功能；代理不改接口控制访问。
- 类适配器（继承）vs 对象适配器（组合）：对象适配器更灵活，推荐使用。
## 七、策略模式
**Spring 应用**：Resource 接口的不同实现类（ClassPathResource、FileSystemResource、UrlResource）根据不同策略访问资源。
**面试考点**：
- 策略模式消除 if-else：将策略注册到 Map 中，通过 key 获取策略，替代分支判断。
- Spring 中的策略自动注册：策略实现类标注 @Component，通过 Map<String, Strategy> 注入（key 是 beanName），业务代码从 Map 获取策略。
- 策略 vs 模板方法：策略基于组合运行时切换整个算法；模板方法基于继承编译期确定骨架。
- 应用场景：支付方式选择、文件解析、排序算法切换、表单校验规则。
## 八、总结对比表
| 设计模式 | Spring 应用 | 核心思想 | 一句话记忆 |
|---------|------------|---------|-----------|
| 工厂模式 | BeanFactory / ApplicationContext | 封装对象创建过程 | 容器就是大工厂 |
| 代理模式 | Spring AOP | 控制对象访问，增强功能 | AOP 的底层实现 |
| 单例模式 | Bean 默认单例 | 保证全局唯一实例 | 容器管理 Bean 生命周期 |
| 模板模式 | JdbcTemplate / RestTemplate | 固定流程 + 可变步骤 | 消除样板代码 |
| 观察者模式 | ApplicationEvent / @EventListener | 一对多依赖，状态变更自动通知 | 事件驱动解耦 |
| 适配器模式 | HandlerAdapter / AdvisorAdapter | 接口转换，兼容不同实现 | 统一多种 Controller |
| 策略模式 | Resource 接口 | 算法族封装，运行时切换 | 消除 if-else |
