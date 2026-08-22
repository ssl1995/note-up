# CASE WHEN 语法详解
## 1. 两种基本语法
### 1）简单 CASE（Simple CASE）
等值判断，类似于 `switch`：
```sql
CASE 表达式
    WHEN 值1 THEN 结果1
    WHEN 值2 THEN 结果2
    ...
    [ELSE 默认结果]
END
```
**示例：**
```sql
SELECT 
    name,
    CASE gender
        WHEN 'M' THEN '男'
        WHEN 'F' THEN '女'
        ELSE '未知'
    END AS gender_desc
FROM users;
```
### 2）搜索 CASE（Searched CASE）
条件判断，类似于 `if-else if-else`，**更常用**：
```sql
CASE
    WHEN 条件1 THEN 结果1
    WHEN 条件2 THEN 结果2
    ...
    [ELSE 默认结果]
END
```
**示例：**
```sql
SELECT 
    name,
    score,
    CASE
        WHEN score >= 90 THEN '优秀'
        WHEN score >= 80 THEN '良好'
        WHEN score >= 60 THEN '及格'
        ELSE '不及格'
    END AS grade
FROM students;
```
## 2. 核心特性
| 特性 | 说明 |
|------|------|
| **短路求值** | 按顺序判断，第一个满足条件的 `WHEN` 生效，后面的不再执行 |
| **ELSE 可选** | 省略 `ELSE` 时，所有条件都不满足则返回 `NULL` |
| **返回类型** | 所有 `THEN`/`ELSE` 的返回值类型必须兼容（SQL 会自动类型提升） |
| **可嵌套** | `THEN` 或 `ELSE` 里可以再放一个 `CASE` |
| **可用位置** | `SELECT`、`WHERE`、`ORDER BY`、`GROUP BY`、`HAVING`、聚合函数内部 |
## 3. 常见使用场景
### 场景 1：SELECT 中做数据映射/分类
```sql
SELECT 
    product_name,
    price,
    CASE 
        WHEN price < 100 THEN '低价'
        WHEN price < 500 THEN '中价'
        ELSE '高价'
    END AS price_level
FROM products;
```
### 场景 2：条件聚合（最常用！）
这是 `CASE WHEN` 最高级的用法，配合 `SUM`/`COUNT`/`AVG` 实现**行转列**或**条件统计**。
#### 示例 A：统计各部门男女员工数
```sql
SELECT 
    dept_name,
    COUNT(CASE WHEN gender = 'M' THEN 1 END) AS male_count,
    COUNT(CASE WHEN gender = 'F' THEN 1 END) AS female_count
FROM employees
GROUP BY dept_name;
```
#### 示例 B：计算各状态订单金额
```sql
SELECT 
    user_id,
    SUM(CASE WHEN status = '已完成' THEN amount ELSE 0 END) AS completed_amount,
    SUM(CASE WHEN status = '待支付' THEN amount ELSE 0 END) AS pending_amount
FROM orders
GROUP BY user_id;
```
#### 示例 C：1661 题的条件聚合
```sql
SELECT 
    machine_id,
    ROUND(
        SUM(CASE WHEN activity_type = 'end' THEN `timestamp` ELSE -`timestamp` END)
        / COUNT(DISTINCT process_id),
        3
    ) AS processing_time
FROM Activity
GROUP BY machine_id;
```
> 这里 `start` 时间戳取负、`end` 取正，`SUM` 后自然得到 `end - start` 的总和。
### 场景 3：WHERE 中做动态过滤
```sql
SELECT *
FROM orders
WHERE 
    CASE 
        WHEN :status = 'ALL' THEN 1=1
        ELSE status = :status
    END;
```
> 常用于 MyBatis 等框架的动态 SQL，但注意可能影响索引使用。
### 场景 4：ORDER BY 中自定义排序
```sql
SELECT *
FROM products
ORDER BY 
    CASE category
        WHEN '手机' THEN 1
        WHEN '电脑' THEN 2
        WHEN '平板' THEN 3
        ELSE 4
    END;
```
### 场景 5：UPDATE 中批量更新不同值
```sql
UPDATE employees
SET salary = 
    CASE 
        WHEN performance = 'A' THEN salary * 1.2
        WHEN performance = 'B' THEN salary * 1.1
        ELSE salary * 1.05
    END;
```
## 4. 注意事项
| 注意点 | 说明 |
|--------|------|
| **NULL 处理** | `CASE WHEN NULL THEN ...` 永远不会匹配，需要用 `IS NULL` |
| **类型一致性** | 所有分支返回值类型要兼容，否则可能报错或隐式转换 |
| **性能** | 在 `WHERE` 中使用 `CASE` 可能导致索引失效，谨慎使用 |
| **可读性** | 嵌套 `CASE` 会降低可读性，建议复杂逻辑用视图或临时表 |
## 5. 与 `IF()` 的区别（MySQL 特有）
| 写法 | 说明 |
|------|------|
| `IF(条件, 值1, 值2)` | MySQL 专有，只能处理两个分支 |
| `CASE WHEN` | 标准 SQL，支持多分支，可移植性好 |
```sql
-- MySQL 特有
SELECT IF(score >= 60, '及格', '不及格') FROM students;
-- 标准 SQL（推荐）
SELECT CASE WHEN score >= 60 THEN '及格' ELSE '不及格' END FROM students;
```
## 6. 实战练习建议
1. **行转列**：把 `subject` 列的 '语文'/'数学'/'英语' 转成三列显示成绩
2. **条件统计**：统计每个班级及格/不及格人数
3. **动态排序**：根据传入参数按不同字段排序
4. **数据清洗**：把异常值（如负数年龄）映射为 NULL 或默认值
`CASE WHEN` 是 SQL 中**最灵活、最强大**的表达式之一，掌握它能让你的 SQL 从"查询"升级到"计算"。