package com.ssl.know.java.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring事务隔离级别示例
 * 4种隔离级别详解
 */
@Service
public class IsolationExample {

    @Autowired
    private AccountService accountService;

    /**
     * 1. READ_UNCOMMITTED（读未提交）
     * 问题：脏读、不可重复读、幻读
     * 场景：几乎不用，性能最好但数据最不安全
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommittedExample() {
        // 可以读到其他事务未提交的数据
        // 如果那个事务回滚了，读到的就是"脏数据"
        accountService.readData();
    }

    /**
     * 2. READ_COMMITTED（读已提交）
     * 问题：不可重复读、幻读
     * 场景：大多数数据库的默认级别（如Oracle、PostgreSQL、SQL Server）
     * 解决：脏读
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readCommittedExample() {
        // 只能读到已提交的数据
        // 但同一事务中，两次读取可能结果不同（其他事务提交了修改）
        accountService.readData();
    }

    /**
     * 3. REPEATABLE_READ（可重复读）
     * 问题：幻读（MySQL通过MVCC和间隙锁解决了大部分幻读）
     * 场景：MySQL的默认隔离级别
     * 解决：脏读、不可重复读
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void repeatableReadExample() {
        // 同一事务中，多次读取结果一致
        // 但可能出现幻读（读取到其他事务插入的新数据）
        accountService.readData();
    }

    /**
     * 4. SERIALIZABLE（串行化）
     * 问题：无并发问题，但性能最差
     * 场景：对数据一致性要求极高的场景，如金融交易
     * 解决：脏读、不可重复读、幻读
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void serializableExample() {
        // 完全串行执行，相当于单线程
        // 性能最差，但数据最安全
        accountService.readData();
    }
}
