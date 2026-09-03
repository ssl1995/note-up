package com.ssl.know.java.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP 切面类 - 五种通知类型完整示例
 * <p>
 * 面试考点：
 * 1. AOP 核心概念：切面(Aspect)、切点(Pointcut)、通知(Advice)、连接点(JoinPoint)、织入(Weaving)
 * 2. 五种通知类型：@Before、@After、@AfterReturning、@AfterThrowing、@Around
 * 3. 切点表达式语法
 * 4. 通知执行顺序
 *
 * @author ssl
 */
@Aspect
@Component
public class LogAspect {

  /**
   * 切点定义：匹配 service 包下所有类的所有方法
   * <p>
   * 切点表达式语法：execution(修饰符 返回值 包名.类名.方法名(参数))
   * <p>
   * 常用通配符：
   * *   匹配任意字符（一个包/类/方法级别）
   * ..  匹配任意层级（包层级或参数个数）
   * +   匹配类及其子类
   */
  @Pointcut("execution(* com.ssl.know.java.aop.service.*.*(..))")
  public void servicePointcut() {
  }

  /**
   * 切点定义：匹配所有 public 方法
   */
  @Pointcut("execution(public * *(..))")
  public void publicMethodPointcut() {
  }

  // ==================== 1. @Before 前置通知 ====================

  /**
   * 前置通知：目标方法执行之前执行
   * <p>
   * 适用场景：参数校验、权限检查、日志记录
   * 特点：无法阻止目标方法执行（除非抛异常）
   */
  @Before("servicePointcut()")
  public void beforeAdvice() {
    System.out.println("[@Before] 前置通知：方法执行前 - 参数校验/权限检查");
  }

  // ==================== 2. @After 后置通知 ====================

  /**
   * 后置通知：目标方法执行之后执行（无论是否异常）
   * <p>
   * 适用场景：资源释放、清理工作
   * 特点：类似 finally 块，一定会执行
   */
  @After("servicePointcut()")
  public void afterAdvice() {
    System.out.println("[@After] 后置通知：方法执行后 - 无论是否异常都会执行（类似finally）");
  }

  // ==================== 3. @AfterReturning 返回通知 ====================

  /**
   * 返回通知：目标方法正常返回后执行
   * <p>
   * 适用场景：结果缓存、返回值日志
   * 特点：只有正常返回才执行，异常时不执行
   * returning = "result" 可以获取返回值
   */
  @AfterReturning(pointcut = "servicePointcut()", returning = "result")
  public void afterReturningAdvice(Object result) {
    System.out.println("[@AfterReturning] 返回通知：方法正常返回，返回值=" + result);
  }

  // ==================== 4. @AfterThrowing 异常通知 ====================

  /**
   * 异常通知：目标方法抛出异常后执行
   * <p>
   * 适用场景：异常日志、告警通知、事务回滚
   * 特点：只有抛出异常才执行
   * throwing = "e" 可以获取异常对象
   */
  @AfterThrowing(pointcut = "servicePointcut()", throwing = "e")
  public void afterThrowingAdvice(Exception e) {
    System.out.println("[@AfterThrowing] 异常通知：方法抛出异常=" + e.getMessage());
  }

  // ==================== 5. @Around 环绕通知（最强大） ====================

  /**
   * 环绕通知：目标方法执行前后都可以增强
   * <p>
   * 适用场景：性能监控、事务管理、缓存
   * 特点：
   * - 可以控制目标方法是否执行（不调用 proceed() 就不执行）
   * - 可以修改参数和返回值
   * - 必须显式调用 joinPoint.proceed() 才会执行目标方法
   * <p>
   * 考点：@Around 和其他通知的执行顺序？
   * 答案：@Around 的 proceed() 之前 → @Before → 目标方法 →
   * @AfterReturning/@AfterThrowing → @After → @Around 的 proceed() 之后
   */
  @Around("servicePointcut()")
  public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    String methodName = joinPoint.getSignature().getName();
    long start = System.currentTimeMillis();

    System.out.println("[@Around] 环绕通知-前：方法=" + methodName);

    // 执行目标方法（不调用则目标方法不执行）
    Object result = joinPoint.proceed();

    long cost = System.currentTimeMillis() - start;
    System.out.println("[@Around] 环绕通知-后：方法=" + methodName + "，耗时=" + cost + "ms");
    return result;
  }
}
