package com.ssl.know.java.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 账户服务，用于演示隔离级别
 */
@Service
public class AccountService {

    /**
     * 读取数据
     */
    @Transactional
    public void readData() {
        System.out.println("读取账户数据");
    }

    /**
     * 转账操作（演示事务使用）
     */
    @Transactional
    public void transfer(Long fromId, Long toId, Double amount) {
        // 扣款
        deduct(fromId, amount);
        // 模拟异常
        // if (true) throw new RuntimeException("转账失败");
        // 加款
        add(toId, amount);
    }

    private void deduct(Long id, Double amount) {
        System.out.println("账户" + id + "扣款" + amount);
    }

    private void add(Long id, Double amount) {
        System.out.println("账户" + id + "加款" + amount);
    }
}
