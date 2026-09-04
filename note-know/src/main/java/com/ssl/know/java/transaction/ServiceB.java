package com.ssl.know.java.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务B：调用方
 */
@Service
public class ServiceB {

    @Autowired
    private ServiceA serviceA;

    /**
     * 场景1：B没有事务，调用A的NEVER方法 → 正常执行
     */
    public void callAWithoutTransaction() {
        // B没有@Transactional注解，当前没有事务
        serviceA.neverMethod();  // ✅ 正常执行，因为A要求NEVER（非事务）
    }

    /**
     * 场景2：B有事务，调用A的NEVER方法 → 抛出异常
     */
    @Transactional
    public void callAWithTransaction() {
        // B有@Transactional注解，当前存在事务
        serviceA.neverMethod();  // ❌ 抛出异常：Existing transaction found for method marked with propagation 'never'
    }
}
