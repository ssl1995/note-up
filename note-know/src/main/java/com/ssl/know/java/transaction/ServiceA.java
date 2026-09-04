package com.ssl.know.java.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务A：被调用方
 */
@Service
public class ServiceA {

    /**
     * NEVER传播行为：以非事务方式执行，如果当前存在事务，则抛出异常
     */
    @Transactional(propagation = Propagation.NEVER)
    public void neverMethod() {
        System.out.println("NEVER: 必须在非事务中执行");
        // 业务逻辑...
    }
}
