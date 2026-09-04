package com.ssl.know.java.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 内部服务类，用于演示传播行为
 */
@Service
public class InnerService {

    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredMethod() {
        System.out.println("REQUIRED: 加入当前事务或新建事务");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsMethod() {
        System.out.println("SUPPORTS: 有事务就用，没有就不用");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryMethod() {
        System.out.println("MANDATORY: 必须在事务中执行");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewMethod() {
        System.out.println("REQUIRES_NEW: 挂起当前事务，新建独立事务");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupportedMethod() {
        System.out.println("NOT_SUPPORTED: 挂起当前事务，非事务执行");
    }

    @Transactional(propagation = Propagation.NEVER)
    public void neverMethod() {
        System.out.println("NEVER: 必须在非事务中执行");
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nestedMethod() {
        System.out.println("NESTED: 在当前事务中创建嵌套事务");
        // 模拟异常，演示部分回滚
        // throw new RuntimeException("嵌套事务异常");
    }
}
