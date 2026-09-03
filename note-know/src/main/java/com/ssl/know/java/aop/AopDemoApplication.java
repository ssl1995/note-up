package com.ssl.know.java.aop;

import com.ssl.know.java.aop.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * AOP 演示启动类
 * <p>
 * 面试考点总结：
 * 1. AOP 核心概念
 * 2. 五种通知类型及执行顺序
 * 3. 切点表达式语法
 * 4. Spring AOP 底层实现（JDK/CGLIB 代理）
 * 5. 自定义注解 + AOP 的实际应用
 *
 * @author ssl
 */
@SpringBootApplication
public class AopDemoApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(AopDemoApplication.class, args);
    UserService userService = context.getBean(UserService.class);

    System.out.println("\n========== 1. 正常方法调用 ==========");
    userService.addUser("张三");

    System.out.println("\n========== 2. 另一个正常方法 ==========");
    userService.deleteUser("李四");

    System.out.println("\n========== 3. 异常方法调用 ==========");
    try {
      userService.getUser("error");
    } catch (RuntimeException e) {
      System.out.println("捕获异常: " + e.getMessage());
    }

    context.close();
  }
}
