# Comparable 和 Comparator 详解

## 一、核心区别对比

| 对比项 | Comparable | Comparator |
|-------|-----------|------------|
| **包位置** | `java.lang` | `java.util` |
| **方法名** | `compareTo(T o)` | `compare(T o1, T o2)` |
| **实现位置** | 类内部实现 | 类外部实现（独立比较器） |
| **排序逻辑** | 类的**自然排序**（默认排序） | 类的**自定义排序**（灵活多变） |
| **修改源码** | 需要修改原类 | 不需要修改原类 |
| **使用场景** | 单一排序规则 | 多种排序规则 |
| **排序方法** | `Collections.sort(list)`<br>`Arrays.sort(array)` | `Collections.sort(list, comparator)`<br>`Arrays.sort(array, comparator)` |

---

## 二、Comparable 接口（自然排序）

### 2.1 定义

```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

### 2.2 实现示例

```java
import java.util.*;

// 学生类实现 Comparable 接口
public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private double score;
    
    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }
    
    // getter/setter 省略...
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getScore() { return score; }
    
    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", score=" + score + "}";
    }
    
    /**
     * 实现 compareTo 方法 - 定义自然排序规则
     * 返回值：
     *   负数：this < o（当前对象排在前面）
     *   零：  this == o（相等）
     *   正数：this > o（当前对象排在后面）
     */
    @Override
    public int compareTo(Student other) {
        // 按年龄升序排序（默认排序规则）
        return Integer.compare(this.age, other.age);
        
        // 或者手动写：
        // if (this.age < other.age) return -1;
        // if (this.age > other.age) return 1;
        // return 0;
    }
    
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 20, 85.5));
        students.add(new Student("李四", 18, 92.0));
        students.add(new Student("王五", 22, 78.5));
        students.add(new Student("赵六", 19, 88.0));
        
        System.out.println("排序前：");
        students.forEach(System.out::println);
        
        // 使用自然排序（Comparable）
        Collections.sort(students);
        
        System.out.println("\n按年龄升序排序后（自然排序）：");
        students.forEach(System.out::println);
    }
}
```

**输出结果：**
```
排序前：
Student{name='张三', age=20, score=85.5}
Student{name='李四', age=18, score=92.0}
Student{name='王五', age=22, score=78.5}
Student{name='赵六', age=19, score=88.0}

按年龄升序排序后（自然排序）：
Student{name='李四', age=18, score=92.0}
Student{name='赵六', age=19, score=88.0}
Student{name='张三', age=20, score=85.5}
Student{name='王五', age=22, score=78.5}
```

---

## 三、Comparator 接口（自定义排序）

### 3.1 定义

```java
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

### 3.2 多种实现方式

```java
import java.util.*;

public class ComparatorDemo {
    
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("张三", 20, 85.5));
        students.add(new Student("李四", 18, 92.0));
        students.add(new Student("王五", 22, 78.5));
        students.add(new Student("赵六", 19, 88.0));
        
        // ========== 方式1：匿名内部类 ==========
        Comparator<Student> scoreComparator = new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // 按分数降序
                return Double.compare(s2.getScore(), s1.getScore());
            }
        };
        Collections.sort(students, scoreComparator);
        System.out.println("按分数降序（匿名内部类）：");
        students.forEach(System.out::println);
        
        // ========== 方式2：Lambda 表达式（Java 8+）==========
        students.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));
        System.out.println("\n按分数降序（Lambda）：");
        students.forEach(System.out::println);
        
        // ========== 方式3：方法引用（Java 8+）==========
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("\n按姓名升序（方法引用）：");
        students.forEach(System.out::println);
        
        // ========== 方式4：链式调用（多条件排序）==========
        students.sort(
            Comparator.comparing(Student::getAge)           // 先按年龄升序
                      .thenComparing(Student::getScore)     // 年龄相同按分数升序
                      .reversed()                           // 整体反转（降序）
        );
        System.out.println("\n多条件排序（年龄+分数，整体降序）：");
        students.forEach(System.out::println);
    }
}
```

**输出结果：**
```
按分数降序（匿名内部类）：
Student{name='李四', age=18, score=92.0}
Student{name='赵六', age=19, score=88.0}
Student{name='张三', age=20, score=85.5}
Student{name='王五', age=22, score=78.5}

按分数降序（Lambda）：
Student{name='李四', age=18, score=92.0}
Student{name='赵六', age=19, score=88.0}
Student{name='张三', age=20, score=85.5}
Student{name='王五', age=22, score=78.5}

按姓名升序（方法引用）：
Student{name='张三', age=20, score=85.5}
Student{name='李四', age=18, score=92.0}
Student{name='王五', age=22, score=78.5}
Student{name='赵六', age=19, score=88.0}

多条件排序（年龄+分数，整体降序）：
Student{name='王五', age=22, score=78.5}
Student{name='张三', age=20, score=85.5}
Student{name='赵六', age=19, score=88.0}
Student{name='李四', age=18, score=92.0}
```

---

## 四、完整对比示例

```java
import java.util.*;

public class ComparableVsComparator {
    
    // 商品类 - 实现 Comparable
    static class Product implements Comparable<Product> {
        private String name;
        private double price;
        private int sales;
        
        public Product(String name, double price, int sales) {
            this.name = name;
            this.price = price;
            this.sales = sales;
        }
        
        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getSales() { return sales; }
        
        @Override
        public String toString() {
            return String.format("Product{name='%s', price=%.2f, sales=%d}", name, price, sales);
        }
        
        /**
         * Comparable：自然排序 - 按价格升序
         */
        @Override
        public int compareTo(Product other) {
            return Double.compare(this.price, other.price);
        }
    }
    
    // 独立的 Comparator 实现类
    static class SalesComparator implements Comparator<Product> {
        @Override
        public int compare(Product p1, Product p2) {
            // 按销量降序
            return Integer.compare(p2.getSales(), p1.getSales());
        }
    }
    
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("iPhone", 5999.0, 100));
        products.add(new Product("华为", 4999.0, 200));
        products.add(new Product("小米", 3999.0, 300));
        products.add(new Product("OPPO", 3999.0, 150));
        
        System.out.println("原始顺序：");
        products.forEach(System.out::println);
        
        // ========== 使用 Comparable（自然排序：价格升序）==========
        Collections.sort(products);
        System.out.println("\n1. Comparable 自然排序（价格升序）：");
        products.forEach(System.out::println);
        
        // ========== 使用 Comparator（自定义排序：销量降序）==========
        products.sort(new SalesComparator());
        System.out.println("\n2. Comparator 自定义排序（销量降序）：");
        products.forEach(System.out::println);
        
        // ========== Comparator 多条件排序 ==========
        products.sort(
            Comparator.comparing(Product::getPrice)      // 价格升序
                      .thenComparing(Product::getSales)  // 价格相同按销量升序
        );
        System.out.println("\n3. Comparator 多条件（价格升序，销量升序）：");
        products.forEach(System.out::println);
        
        // ========== Comparator 反转排序 ==========
        products.sort(Comparator.comparing(Product::getPrice).reversed());
        System.out.println("\n4. Comparator 反转（价格降序）：");
        products.forEach(System.out::println);
    }
}
```

**输出结果：**
```
原始顺序：
Product{name='iPhone', price=5999.00, sales=100}
Product{name='华为', price=4999.00, sales=200}
Product{name='小米', price=3999.00, sales=300}
Product{name='OPPO', price=3999.00, sales=150}

1. Comparable 自然排序（价格升序）：
Product{name='小米', price=3999.00, sales=300}
Product{name='OPPO', price=3999.00, sales=150}
Product{name='华为', price=4999.00, sales=200}
Product{name='iPhone', price=5999.00, sales=100}

2. Comparator 自定义排序（销量降序）：
Product{name='小米', price=3999.00, sales=300}
Product{name='华为', price=4999.00, sales=200}
Product{name='OPPO', price=3999.00, sales=150}
Product{name='iPhone', price=5999.00, sales=100}

3. Comparator 多条件（价格升序，销量升序）：
Product{name='OPPO', price=3999.00, sales=150}
Product{name='小米', price=3999.00, sales=300}
Product{name='华为', price=4999.00, sales=200}
Product{name='iPhone', price=5999.00, sales=100}

4. Comparator 反转（价格降序）：
Product{name='iPhone', price=5999.00, sales=100}
Product{name='华为', price=4999.00, sales=200}
Product{name='小米', price=3999.00, sales=300}
Product{name='OPPO', price=3999.00, sales=150}
```

---

## 五、TreeMap/TreeSet 中的应用

```java
import java.util.*;

public class TreeMapComparatorDemo {
    
    public static void main(String[] args) {
        // ========== 使用 Comparable（自然排序）==========
        TreeMap<Student, String> treeMap1 = new TreeMap<>();
        treeMap1.put(new Student("张三", 20, 85.5), "A");
        treeMap1.put(new Student("李四", 18, 92.0), "B");
        treeMap1.put(new Student("王五", 22, 78.5), "C");
        System.out.println("TreeMap 自然排序（按年龄）：");
        treeMap1.forEach((k, v) -> System.out.println(k + " -> " + v));
        
        // ========== 使用 Comparator（自定义排序）==========
        TreeMap<Student, String> treeMap2 = new TreeMap<>(
            Comparator.comparing(Student::getScore).reversed()  // 按分数降序
        );
        treeMap2.put(new Student("张三", 20, 85.5), "A");
        treeMap2.put(new Student("李四", 18, 92.0), "B");
        treeMap2.put(new Student("王五", 22, 78.5), "C");
        System.out.println("\nTreeMap 自定义排序（按分数降序）：");
        treeMap2.forEach((k, v) -> System.out.println(k + " -> " + v));
        
        // ========== 字符串长度排序 ==========
        TreeMap<String, Integer> lengthMap = new TreeMap<>(
            Comparator.comparingInt(String::length)  // 按字符串长度排序
        );
        lengthMap.put("a", 1);
        lengthMap.put("ccc", 3);
        lengthMap.put("bb", 2);
        System.out.println("\n按字符串长度排序：");
        lengthMap.forEach((k, v) -> System.out.println(k + " (长度:" + k.length() + ")"));
    }
}
```

---

## 六、面试常见问题

### Q1: Comparable 和 Comparator 的主要区别？

| 方面 | Comparable | Comparator |
|-----|-----------|------------|
| **排序数量** | 只能有一种排序规则 | 可以有多种排序规则 |
| **代码侵入性** | 需要修改原类 | 不修改原类，外部定义 |
| **灵活性** | 低（固定排序） | 高（灵活切换） |
| **使用方式** | `Collections.sort(list)` | `Collections.sort(list, comparator)` |

### Q2: 什么时候用 Comparable，什么时候用 Comparator？

- **用 Comparable**：当排序规则是类的**固有属性**时（如：人的年龄、商品的编号）
- **用 Comparator**：当需要**多种排序规则**，或**不能修改原类**时（如：第三方库的类）

### Q3: compareTo 和 compare 的返回值规则？

```
返回值 < 0：第一个参数 < 第二个参数（升序）
返回值 = 0：第一个参数 = 第二个参数
返回值 > 0：第一个参数 > 第二个参数
```

**记忆技巧**：`this - other` 或 `o1 - o2`，负数表示"小于"，正数表示"大于"

### Q4: 为什么推荐用 `Integer.compare()` 而不是 `>` `<`？

```java
// ❌ 不推荐：可能溢出
return this.age - other.age;  // 当 age 很大时可能溢出

// ✅ 推荐：安全且清晰
return Integer.compare(this.age, other.age);
```

---

## 七、Java 8+ 常用 Comparator 写法速查

```java
import java.util.*;

public class ComparatorQuickReference {
    
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("张三", 20, 85.5),
            new Student("李四", 18, 92.0),
            new Student("王五", 22, 78.5)
        );
        
        // 1. 单字段升序
        students.sort(Comparator.comparing(Student::getAge));
        
        // 2. 单字段降序
        students.sort(Comparator.comparing(Student::getAge).reversed());
        
        // 3. 多字段排序
        students.sort(Comparator.comparing(Student::getAge)
                                .thenComparing(Student::getScore));
        
        // 4. 自定义比较逻辑
        students.sort((s1, s2) -> {
            if (s1.getScore() == s2.getScore()) {
                return s1.getName().compareTo(s2.getName());
            }
            return Double.compare(s2.getScore(), s1.getScore());
        });
        
        // 5. 空值处理（nullsFirst / nullsLast）
        List<String> names = Arrays.asList("张三", null, "李四", "王五", null);
        names.sort(Comparator.nullsFirst(String::compareTo));   // null 排前面
        names.sort(Comparator.nullsLast(String::compareTo));    // null 排后面
        
        // 6. 反转比较器
        Comparator<Student> ageComparator = Comparator.comparing(Student::getAge);
        students.sort(ageComparator.reversed());
        
        // 7. 提取 key 后排序
        students.sort(Comparator.comparing(s -> s.getName().length()));  // 按姓名长度
    }
}
```

---

## 八、记忆口诀

> **"Comparable 内置自然序，Comparator 外置灵活序"**
>
> - **Comparable**：类实现接口，定义**默认排序**，一种规则
> - **Comparator**：外部定义，**灵活多变**，多种规则
> - **返回值**：负小零等正大（负数=小于，零=等于，正数=大于）
> - **Java 8**：`Comparator.comparing()` 链式调用最优雅

---

*参考资料：*
- [Java Comparable 官方文档](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html)
- [Java Comparator 官方文档](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)
