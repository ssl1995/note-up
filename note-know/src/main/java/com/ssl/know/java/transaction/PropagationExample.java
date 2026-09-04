package com.ssl.know.java.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring事务传播行为示例
 * 7种传播行为详解
 */
@Service
public class PropagationExample {

    @Autowired
    private InnerService innerService;

    /**
     * 1. REQUIRED（默认）：如果当前没有事务，就新建一个事务；如果已经存在一个事务中，加入到这个事务中
     * 场景：最常用的传播行为，大多数业务场景适用
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void requiredExample() {
        // 业务逻辑
        innerService.requiredMethod();
        // 如果innerService抛出异常，整个事务回滚（包括当前方法）
    }

    /**
     * 2. SUPPORTS：如果当前有事务，就在事务中执行；如果没有事务，就以非事务方式执行
     * 场景：查询操作，有事务更好，没有也能运行
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supportsExample() {
        // 查询操作，有没有事务都可以
        innerService.supportsMethod();
    }

    /**
     * 3. MANDATORY：如果当前有事务，就在事务中执行；如果没有事务，就抛出异常
     * 场景：强制要求必须在事务中执行的方法
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatoryExample() {
        // 如果外层没有事务，直接抛异常
        innerService.mandatoryMethod();
    }

    /**
     * 4. REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起
     * 场景：需要独立事务的操作，不受外层事务影响
     * 典型应用：日志记录、审计操作
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNewExample() {
        // 当前事务
        innerService.requiresNewMethod();  // 新起一个事务
        // innerService的事务独立提交或回滚，不影响外层
    }

    /**
     * 5. NOT_SUPPORTED：以非事务方式执行，如果当前存在事务，把当前事务挂起
     * 场景：不需要事务的操作，比如发送通知
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupportedExample() {
        // 非事务执行，外层事务被挂起
        innerService.notSupportedMethod();
    }

    /**
     * 6. NEVER：以非事务方式执行，如果当前存在事务，则抛出异常
     * 场景：明确不需要事务的方法
     */
    @Transactional(propagation = Propagation.NEVER)
    public void neverExample() {
        // 如果外层有事务，直接抛异常
        innerService.neverMethod();
    }

    /**
     * 7. NESTED：如果当前存在事务，则在嵌套事务内执行；如果当前没有事务，则执行与REQUIRED类似的操作
     * 场景：嵌套事务，可以独立回滚（部分回滚）
     * 注意：需要数据库支持保存点（SAVEPOINT）
     */
    @Transactional(propagation = Propagation.NESTED)
    public void nestedExample() {
        // 外层事务
        try {
            innerService.nestedMethod();  // 嵌套事务
        } catch (Exception e) {
            // 只回滚嵌套事务，外层事务继续
            System.out.println("嵌套事务回滚，外层继续");
        }
    }
}
