package com.ssl.know.java.aop;

import java.lang.annotation.*;

/**
 * 自定义注解：操作日志
 * <p>
 * 面试考点：自定义注解 + AOP 实现通用功能
 * 这是实际开发中最常用的 AOP 使用方式
 *
 * @author ssl
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

  /**
   * 操作描述
   */
  String value() default "";

  /**
   * 操作类型
   */
  String type() default "其他";
}
