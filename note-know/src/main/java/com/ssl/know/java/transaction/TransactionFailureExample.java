package com.ssl.know.java.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 事务失效场景示例
 */
@Service
public class TransactionFailureExample {

    @Autowired
    private InnerService innerService;

    /**
     * 场景1：自调用导致事务失效
     * 原因：Spring事务基于AOP代理，自调用不走代理
     */
    public void selfInvocationExample() {
        // 直接调用this.transactionalMethod()，事务不生效！
        this.transactionalMethod();
    }

    @Transactional
    public void transactionalMethod() {
        System.out.println("这个方法的事务不会生效，因为是自调用");
    }

    /**
     * 场景2：异常被捕获导致事务失效
     * 原因：默认只回滚RuntimeException和Error
     */
    @Transactional
    public void exceptionCaughtExample() {
        try {
            innerService.requiredMethod();
            // 抛出受检异常
            throw new Exception("受检异常");
        } catch (Exception e) {
            // 异常被捕获，事务不会回滚！
            System.out.println("异常被捕获，事务不会回滚");
        }
    }

    /**
     * 场景3：多线程导致事务失效
     * 原因：事务是线程绑定的
     */
    @Transactional
    public void multiThreadExample() {
        new Thread(() -> {
            // 新线程中的操作不在原事务中
            innerService.requiredMethod();
        }).start();
    }

    /**
     * 场景4：数据库引擎不支持事务
     * 原因：MyISAM不支持事务
     */
    @Transactional
    public void myisamExample() {
        // 如果表是MyISAM引擎，事务不会生效
        System.out.println("MyISAM引擎不支持事务");
    }

    /**
     * 场景5：方法不是public
     * 原因：Spring AOP只能代理public方法
     */
    @Transactional
    protected void protectedMethod() {
        System.out.println("protected方法事务不生效");
    }

    @Transactional
    private void privateMethod() {
        System.out.println("private方法事务不生效");
    }
}
