CREATE TABLE `user`
(
    -- 主键约束 + 自增
    `id`       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    -- 非空约束 + 唯一约束
    `username` VARCHAR(50)  NOT NULL COMMENT '用户名',
    -- 非空约束
    `email`    VARCHAR(100) NOT NULL COMMENT '邮箱',
    -- 默认约束
    `status`   TINYINT      NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-正常',
    -- 默认约束 + 检查约束
    `age`      INT          NOT NULL DEFAULT 18 COMMENT '年龄',
    -- 外键约束（引用其他表）
    `dept_id`  BIGINT UNSIGNED COMMENT '部门ID',
    -- 检查约束（MySQL 8.0.16+）
    `score`    DECIMAL(5, 2) CHECK (`score` >= 0 AND `score` <= 100),
    -- 主键约束
    PRIMARY KEY (`id`),
    -- 唯一约束
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_email` (`email`),
    -- 外键约束
    FOREIGN KEY (`dept_id`) REFERENCES `dept` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
    -- 检查约束（表级）
    CHECK (`age` >= 0 AND `age` <= 150)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';