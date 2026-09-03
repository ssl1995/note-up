package com.ssl.know.java.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 自定义注解切面：通过 @OperationLog 注解实现操作日志记录
 * <p>
 * 面试考点：
 * 1. 如何通过自定义注解 + AOP 实现通用功能（日志、权限、限流、防重提交）
 * 2. @annotation 切点表达式的使用
 * 3. 如何获取注解上的属性值
 *
 * @author ssl
 */
@Aspect
@Component
public class OperationLogAspect {

  /**
   * 环绕通知：拦截所有标注了 @OperationLog 的方法
   * <p>
   * @annotation(operationLog) 表示匹配标注了该注解的方法，
   * 同时通过参数绑定获取注解实例，从而读取注解属性
   */
  @Around("@annotation(operationLog)")
  public Object around(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
    // 获取方法信息
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    String className = joinPoint.getTarget().getClass().getSimpleName();
    String methodName = method.getName();

    // 读取注解属性
    String description = operationLog.value();
    String type = operationLog.type();

    System.out.println("========== 操作日志 ==========");
    System.out.println("操作类型: " + type);
    System.out.println("操作描述: " + description);
    System.out.println("目标方法: " + className + "." + methodName);
    System.out.println("方法参数: " + java.util.Arrays.toString(joinPoint.getArgs()));

    long start = System.currentTimeMillis();
    try {
      Object result = joinPoint.proceed();
      long cost = System.currentTimeMillis() - start;
      System.out.println("执行结果: 成功，耗时 " + cost + "ms");
      return result;
    } catch (Throwable e) {
      long cost = System.currentTimeMillis() - start;
      System.out.println("执行结果: 失败，异常=" + e.getMessage() + "，耗时 " + cost + "ms");
      throw e;
    }
  }
}
