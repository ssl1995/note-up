# SQL JOIN 类型详解：CROSS JOIN vs INNER JOIN vs LEFT JOIN
## 1. 核心区别对比
| 写法 | 等价于 | 结果行数 | 说明 |
|------|--------|---------|------|
| `FROM A, B` | `CROSS JOIN` | A × B | 笛卡尔积，无连接条件 |
| `FROM A JOIN B ON A.id = B.id` | `INNER JOIN` | 匹配的行 | 有连接条件，只保留匹配 |
| `FROM A INNER JOIN B ON ...` | `INNER JOIN` | 匹配的行 | 显式 INNER JOIN |
| `FROM A LEFT JOIN B ON ...` | `LEFT JOIN` | A 全保留 | 左表全保留，右表补 NULL |
## 2. 图解三种 JOIN
### 原始数据
**A 表（2 行）：**
| id | name |
|---|------|
| 1 | Alice |
| 2 | Bob |
**B 表（3 行）：**
| id | dept |
|---|------|
| 1 | IT |
| 2 | HR |
| 3 | Sales |
### CROSS JOIN（笛卡尔积）
```sql
SELECT * FROM A, B;
-- 或
SELECT * FROM A CROSS JOIN B;
```
**结果：2 × 3 = 6 行**
| A.id | A.name | B.id | B.dept |
|-----|--------|-----|--------|
| 1 | Alice | 1 | IT |
| 1 | Alice | 2 | HR |
| 1 | Alice | 3 | Sales |
| 2 | Bob | 1 | IT |
| 2 | Bob | 2 | HR |
| 2 | Bob | 3 | Sales |
**特点：** 每一行 A 都与每一行 B 组合，**无筛选条件**。
### INNER JOIN（内连接）
```sql
SELECT * FROM A JOIN B ON A.id = B.id;
-- 或
SELECT * FROM A INNER JOIN B ON A.id = B.id;
```
**结果：2 行（只保留 id 匹配的）**
| A.id | A.name | B.id | B.dept |
|-----|--------|-----|--------|
| 1 | Alice | 1 | IT |
| 2 | Bob | 2 | HR |
**特点：** 只保留**满足连接条件**的行，不匹配的行被丢弃。
### LEFT JOIN（左连接）
```sql
SELECT * FROM A LEFT JOIN B ON A.id = B.id;
```
**结果：2 行（A 全保留，B 无匹配补 NULL）**
| A.id | A.name | B.id | B.dept |
|-----|--------|-----|--------|
| 1 | Alice | 1 | IT |
| 2 | Bob | 2 | HR |
**特点：** 左表全保留，右表无匹配时补 NULL。
## 3. 为什么 `FROM A, B` 是 CROSS JOIN？
### 历史原因
这是 **SQL-89 标准** 的遗留写法：
```sql
-- SQL-89 标准（旧写法）
SELECT * FROM A, B WHERE A.id = B.id;  -- 用 WHERE 筛选，等价于 INNER JOIN
-- SQL-92 标准（新写法，推荐）
SELECT * FROM A INNER JOIN B ON A.id = B.id;  -- 显式 INNER JOIN
```
**关键点：**
- `FROM A, B` **没有 ON 子句** → 是 CROSS JOIN
- `FROM A, B WHERE ...` **有 WHERE 筛选** → 等价于 INNER JOIN
- `FROM A JOIN B ON ...` **有 ON 子句** → 显式 INNER JOIN
## 4. 对比示例
### 示例 1：无 WHERE 条件
```sql
-- CROSS JOIN（笛卡尔积）
SELECT * FROM A, B;
-- 结果：6 行
```
### 示例 2：有 WHERE 条件
```sql
-- 等价于 INNER JOIN
SELECT * FROM A, B WHERE A.id = B.id;
-- 结果：2 行
```
### 示例 3：显式 INNER JOIN
```sql
-- 显式 INNER JOIN（推荐）
SELECT * FROM A INNER JOIN B ON A.id = B.id;
-- 结果：2 行
```
## 5. 记忆口诀
> **"逗号无 ON 是 CROSS，逗号有 WHERE 是 INNER，显式 JOIN 最清晰"**
| 场景 | 写法 | 结果 |
|------|------|------|
| 无连接条件 | `FROM A, B` | CROSS JOIN |
| 有 WHERE 筛选 | `FROM A, B WHERE A.id = B.id` | 等价 INNER JOIN |
| 显式连接 | `FROM A JOIN B ON A.id = B.id` | INNER JOIN |
## 6. 实际开发建议
| 场景 | 推荐写法 | 原因 |
|------|---------|------|
| 需要笛卡尔积 | `CROSS JOIN` | 显式表达意图 |
| 需要内连接 | `INNER JOIN ... ON` | 显式表达意图，避免遗漏 WHERE |
| 需要左连接 | `LEFT JOIN ... ON` | 显式表达意图 |
| 旧代码维护 | 理解 `,` 写法 | 兼容历史代码 |
## 7. 针对 570 题的回顾
```sql
-- CROSS JOIN（6 × 6 = 36 行）
SELECT * FROM Employee e, Employee sub ORDER BY e.id;
-- INNER JOIN（只保留匹配的 5 行）
SELECT * FROM Employee e JOIN Employee sub ON e.id = sub.managerId ORDER BY e.id;
```
**关键区别：**
- `,` 写法：所有员工 × 所有员工 = 36 行
- `JOIN ... ON`：只保留"经理-下属"关系 = 5 行
## 8. JOIN 类型速查表
| JOIN 类型 | 关键字 | 左表 | 右表 | 结果 |
|----------|--------|------|------|------|
| 笛卡尔积 | `CROSS JOIN` / `,` | 全保留 | 全保留 | A × B |
| 内连接 | `INNER JOIN` / `JOIN` | 匹配 | 匹配 | 交集 |
| 左连接 | `LEFT JOIN` | 全保留 | 匹配/NULL | 左表全集 |
| 右连接 | `RIGHT JOIN` | 匹配/NULL | 全保留 | 右表全集 |
| 全连接 | `FULL JOIN` | 全保留 | 全保留 | 并集 |