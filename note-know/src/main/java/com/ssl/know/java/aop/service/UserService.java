package com.ssl.know.java.aop.service;

import com.ssl.know.java.aop.OperationLog;
import org.springframework.stereotype.Service;

/**
 * 用户服务 - AOP 目标类
 *
 * @author ssl
 */
@Service
public class UserService {

  @OperationLog(value = "新增用户", type = "新增")
  public String addUser(String name) {
    System.out.println("【业务逻辑】添加用户: " + name);
    return "用户[" + name + "]添加成功";
  }

  @OperationLog(value = "删除用户", type = "删除")
  public String deleteUser(String name) {
    System.out.println("【业务逻辑】删除用户: " + name);
    return "用户[" + name + "]删除成功";
  }

  @OperationLog(value = "查询用户", type = "查询")
  public String getUser(String name) {
    System.out.println("【业务逻辑】查询用户: " + name);
    if ("error".equals(name)) {
      throw new RuntimeException("用户不存在");
    }
    return "用户[" + name + "]";
  }
}
