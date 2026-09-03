package com.ssl.know.java.aop;

/**
 * Spring AOP 面试考点总结
 * <p>
 * ==================== 一、AOP 核心概念 ====================
 * <p>
 * 【切面 Aspect】
 * 横切关注点的模块化，如日志、事务、权限。一个切面 = 切点 + 通知。
 * <p>
 * 【切点 Pointcut】
 * 定义"在哪些地方"应用增强逻辑，通过切点表达式匹配连接点。
 * <p>
 * 【通知 Advice】
 * 定义"做什么"增强逻辑，即具体要执行的代码。
 * 五种类型：@Before、@After、@AfterReturning、@AfterThrowing、@Around
 * <p>
 * 【连接点 JoinPoint】
 * 程序执行过程中的一个点，如方法调用、异常抛出。Spring AOP 只支持方法级别的连接点。
 * <p>
 * 【织入 Weaving】
 * 将切面应用到目标对象创建代理对象的过程。
 * - 编译期织入（AspectJ）
 * - 类加载期织入（AspectJ LTW）
 * - 运行期织入（Spring AOP，通过动态代理）
 * <p>
 * ==================== 二、五种通知类型 ====================
 * <p>
 * | 通知类型         | 执行时机                     | 适用场景               |
 * |-----------------|----------------------------|----------------------|
 * | @Before         | 方法执行前                   | 参数校验、权限检查       |
 * | @After          | 方法执行后（无论是否异常）      | 资源释放（类似finally）  |
 * | @AfterReturning | 方法正常返回后               | 结果缓存、返回值日志      |
 * | @AfterThrowing  | 方法抛出异常后               | 异常告警、事务回滚       |
 * | @Around         | 方法执行前后（最强大）         | 性能监控、事务管理       |
 * <p>
 * 执行顺序（正常情况）：
 * @Around(proceed前) → @Before → 目标方法 → @AfterReturning → @After → @Around(proceed后)
 * <p>
 * 执行顺序（异常情况）：
 * @Around(proceed前) → @Before → 目标方法(抛异常) → @AfterThrowing → @After
 * <p>
 * ==================== 三、切点表达式 ====================
 * <p>
 * 语法：execution(修饰符? 返回值 包名.类名? 方法名(参数) 异常?)
 * <p>
 * 常用示例：
 * execution(* com.ssl.service.*.*(..))           service包下所有类的所有方法
 * execution(* com.ssl.service..*.*(..))          service包及子包下所有类的所有方法
 * execution(public * *(..))                       所有public方法
 * execution(* save*(..))                          所有以save开头的方法
 * execution(* com.ssl.service.UserService.*(..))  UserService的所有方法
 * <p>
 * 其他切点指示符：
 * @annotation(xxx)    匹配标注了指定注解的方法
 * @within(xxx)        匹配标注了指定注解的类中的方法
 * args(xxx)           匹配参数类型为xxx的方法
 * bean(xxx)           匹配指定bean名称的方法（Spring特有）
 * <p>
 * ==================== 四、Spring AOP 底层实现 ====================
 * <p>
 * Spring AOP 基于动态代理实现：
 * - 目标类实现了接口 → JDK 动态代理（默认）
 * - 目标类没有实现接口 → CGLIB 代理
 * - proxyTargetClass = true → 强制使用 CGLIB
 * <p>
 * Spring AOP vs AspectJ：
 * - Spring AOP：运行期织入，基于代理，只支持方法级别，性能略低，使用简单
 * - AspectJ：编译期/类加载期织入，基于字节码，支持字段/构造器级别，性能高，功能强大
 * <p>
 * ==================== 五、高频面试题 ====================
 * <p>
 * Q1: Spring AOP 中自调用为什么不生效？
 * A1: 自调用走的是 this（目标对象），不是代理对象，所以不会触发增强。
 *     解决：注入自身代理、AopContext.currentProxy()、拆分到不同Bean。
 * <p>
 * Q2: @Transactional 失效的场景？
 * A2: ①自调用 ②方法非public ③异常被catch未抛出 ④数据库引擎不支持事务
 *     ⑤@Transactional用在非Spring管理的Bean上 ⑥rollbackFor配置不对
 * <p>
 * Q3: Spring AOP 和 AspectJ 的区别？
 * A3: Spring AOP 是运行期动态代理，只支持方法级别；
 *     AspectJ 是编译期/加载期字节码增强，支持方法、字段、构造器级别。
 *     Spring AOP 集成了 AspectJ 的注解风格，但底层实现不同。
 * <p>
 * Q4: 如何控制多个切面的执行顺序？
 * A4: 使用 @Order 注解或实现 Ordered 接口，值越小优先级越高。
 *     对于 @Around：值小的先执行 proceed() 之前的逻辑，后执行 proceed() 之后的逻辑。
 * <p>
 * Q5: AOP 的实际应用场景？
 * A5: ①声明式事务 @Transactional ②日志记录 ③权限校验 @PreAuthorize
 *     ④接口限流 ⑤防重复提交 ⑥缓存 @Cacheable ⑦分布式锁
 * <p>
 * ==================== 六、自定义注解 + AOP 实战 ====================
 * <p>
 * 实际开发中最常用的 AOP 使用方式：
 * 1. 定义注解（如 @OperationLog、@RateLimit、@DistributedLock）
 * 2. 编写切面类，使用 @annotation(xxx) 匹配标注了注解的方法
 * 3. 在切面中实现通用逻辑（日志记录、限流、加锁等）
 * 4. 业务代码只需在方法上添加注解即可
 * <p>
 * 优点：非侵入式、可复用、集中管理横切逻辑
 *
 * @author ssl
 */
public class AopInterviewNotes {
  // 本类仅作为面试考点文档，不包含可执行代码
}
