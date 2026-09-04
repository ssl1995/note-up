# MyBatis设计模式详解（对比Spring）
## 一、建造者模式（Builder Pattern）
**MyBatis应用**：SqlSessionFactoryBuilder、XMLConfigBuilder、XMLMapperBuilder
**核心思想**：将复杂对象的构建过程与表示分离，使得同样的构建过程可以创建不同的表示。
**MyBatis源码分析**：
```java
// SqlSessionFactoryBuilder 建造者
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream inputStream) {
        XMLConfigBuilder parser = new XMLConfigBuilder(inputStream);
        return build(parser.parse());  // 解析XML，构建Configuration
    }
}

// XMLConfigBuilder 具体建造者
public class XMLConfigBuilder {
    private Configuration configuration;
    
    public Configuration parse() {
        // 一步步构建Configuration
        parseConfiguration();      // 解析<configuration>
        environmentsElement();     // 解析<environments>
        typeAliasesElement();      // 解析<typeAliases>
        // ... 更多解析步骤
        return configuration;
    }
}
```
**代码示例**：
```java
// 建造者模式：一步步构建复杂对象
ConfigurationBuilder builder = new XmlConfigurationBuilder();
builder.buildDataSource();    // 构建数据源
builder.buildPool();          // 构建连接池
Configuration config = builder.getResult();  // 获取最终产品
```
**与Spring工厂模式对比**：
| 对比项 | Spring工厂模式 | MyBatis建造者模式 |
|--------|---------------|------------------|
| 目的 | 创建Bean实例 | 构建复杂Configuration对象 |
| 过程 | 简单new或反射 | 多步骤、可配置的构建过程 |
| 灵活性 | 较低（直接创建） | 较高（可分步构建） |
| 适用场景 | 对象创建 | 复杂对象组装 |
## 二、代理模式（Proxy Pattern）
**MyBatis应用**：Mapper接口的动态代理
**核心思想**：为其他对象提供一种代理以控制对这个对象的访问。
**MyBatis源码分析**：
```java
// MapperProxyFactory 代理工厂
public class MapperProxyFactory<T> {
    public T newInstance(SqlSession sqlSession) {
        // 使用JDK动态代理创建Mapper接口的代理对象
        return (T) Proxy.newProxyInstance(
            mapperInterface.getClassLoader(),
            new Class[]{mapperInterface},
            new MapperProxy<>(sqlSession, mapperInterface, methodCache)
        );
    }
}

// MapperProxy 代理处理器
public class MapperProxy<T> implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 将Mapper接口方法调用转换为SQL执行
        MapperMethod mapperMethod = cachedMapperMethod(method);
        return mapperMethod.execute(sqlSession, args);
    }
}
```
**使用示例**：
```java
// 开发者只定义接口，不写实现类
public interface UserMapper {
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(Long id);
}

// MyBatis通过动态代理生成实现类
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
User user = userMapper.selectById(1L);  // 实际执行SQL
```
**与Spring代理模式对比**：
| 对比项 | Spring代理模式 | MyBatis代理模式 |
|--------|---------------|----------------|
| 代理目标 | 已有实现类 | 只有接口，无实现 |
| 代理目的 | 增强功能（AOP） | 生成实现（执行SQL） |
| 代理方式 | JDK或CGLIB | 仅JDK（接口） |
| 适用场景 | 事务、日志、权限 | Mapper接口实现 |
## 三、责任链模式（Chain of Responsibility Pattern）
**MyBatis应用**：插件机制（Interceptor）
**核心思想**：将请求沿着处理者链传递，直到有处理者处理它。
**MyBatis源码分析**：
```java
// InterceptorChain 责任链
public class InterceptorChain {
    private final List<Interceptor> interceptors = new ArrayList<>();
    
    public Object pluginAll(Object target) {
        // 责任链：层层包装
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }
}

// Interceptor 拦截器接口
public interface Interceptor {
    Object intercept(Invocation invocation);
    default Object plugin(Object target) {
        // 使用动态代理包装目标对象
        return Plugin.wrap(target, this);
    }
}
```
**使用示例**：
```java
// 自定义分页插件
@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) {
        // 1. 获取SQL
        // 2. 解析分页参数
        // 3. 修改SQL（添加LIMIT）
        // 4. 执行目标方法
        return invocation.proceed();
    }
}

// 配置插件链
<plugins>
    <plugin interceptor="com.example.PageInterceptor"/>
    <plugin interceptor="com.example.CacheInterceptor"/>
    <plugin interceptor="com.example.LogInterceptor"/>
</plugins>
```
**执行顺序**：
```
请求 → LogInterceptor → CacheInterceptor → PageInterceptor → 目标方法
     ← LogInterceptor ← CacheInterceptor ← PageInterceptor ← 返回结果
```
**特点**：后添加的插件先执行（类似栈结构）
## 四、模板模式（Template Pattern）
**MyBatis应用**：BaseExecutor、BaseStatementHandler
**核心思想**：定义算法骨架，将某些步骤延迟到子类实现。
**MyBatis源码分析**：
```java
// BaseExecutor 模板方法
public abstract class BaseExecutor implements Executor {
    
    // 模板方法：定义查询流程
    public <E> List<E> query(MappedStatement ms, Object parameter, ...) {
        // 1. 获取缓存
        CacheKey key = createCacheKey(...);
        List<E> list = getLocalCache(key);
        if (list != null) {
            return list;
        }
        // 2. 查询数据库（抽象方法，子类实现）
        list = queryFromDatabase(ms, parameter, ...);
        // 3. 放入缓存
        putLocalCache(key, list);
        return list;
    }
    
    // 抽象方法：由子类实现具体查询
    protected abstract <E> List<E> doQuery(MappedStatement ms, Object parameter, ...);
}

// SimpleExecutor 简单执行器（具体实现）
public class SimpleExecutor extends BaseExecutor {
    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, ...) {
        // 具体查询实现
        StatementHandler handler = ms.getStatementHandler();
        Statement stmt = handler.prepare(connection);
        return handler.query(stmt, resultHandler);
    }
}
```
**与Spring模板模式对比**：
| 对比项 | Spring模板模式 | MyBatis模板模式 |
|--------|---------------|----------------|
| 典型应用 | JdbcTemplate | BaseExecutor |
| 解决的问题 | 消除样板代码 | 统一执行流程 |
| 变化点 | SQL和结果映射 | 具体执行方式 |
| 扩展方式 | 实现RowMapper | 继承BaseExecutor |
## 五、工厂模式（Factory Pattern）
**MyBatis应用**：ObjectFactory、SqlSessionFactory
**核心思想**：定义创建对象的接口，让子类决定实例化哪个类。
**MyBatis源码分析**：
```java
// ObjectFactory 对象工厂
public interface ObjectFactory {
    <T> T create(Class<T> type);
    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);
}

// DefaultObjectFactory 默认工厂
public class DefaultObjectFactory implements ObjectFactory {
    @Override
    public <T> T create(Class<T> type) {
        return create(type, null, null);
    }
    
    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        // 通过反射创建对象
        Constructor<T> constructor = type.getDeclaredConstructor();
        return constructor.newInstance();
    }
}
```
**使用场景**：
```java
// MyBatis创建结果对象时使用ObjectFactory
User user = objectFactory.create(User.class);  // 创建User实例

// 可以自定义ObjectFactory来创建对象
public class CustomObjectFactory extends DefaultObjectFactory {
    @Override
    public <T> T create(Class<T> type) {
        if (type == User.class) {
            // 自定义创建逻辑（如依赖注入）
            return (T) new User("defaultName");
        }
        return super.create(type);
    }
}
```
## 六、总结对比表
| 设计模式 | MyBatis应用 | Spring应用 | 核心区别 |
|---------|------------|-----------|---------|
| **建造者模式** | SqlSessionFactoryBuilder | - | MyBatis：分步构建Configuration；Spring：无典型应用 |
| **代理模式** | Mapper动态代理 | AOP代理 | MyBatis：生成接口实现；Spring：增强已有实现 |
| **责任链模式** | 插件机制（Interceptor） | Filter/Interceptor | MyBatis：插件层层包装；Spring：请求处理链 |
| **模板模式** | BaseExecutor | JdbcTemplate | MyBatis：统一执行流程；Spring：消除样板代码 |
| **工厂模式** | ObjectFactory | BeanFactory | MyBatis：创建结果对象；Spring：管理Bean生命周期 |
| **单例模式** | - | Bean默认单例 | MyBatis：无典型应用；Spring：容器管理 |
| **观察者模式** | - | ApplicationEvent | MyBatis：无典型应用；Spring：事件驱动 |
| **适配器模式** | - | HandlerAdapter | MyBatis：无典型应用；Spring：统一多种Controller |
| **策略模式** | - | Resource | MyBatis：无典型应用；Spring：资源访问策略 |
## 七、MyBatis vs Spring 设计模式对比总结
**相同点**：
1. 都大量使用工厂模式创建对象
2. 都使用代理模式增强功能
3. 都使用模板模式统一流程
**不同点**：
1. **建造者模式**：MyBatis特有（构建Configuration），Spring无
2. **责任链模式**：MyBatis插件机制，SpringMVC拦截器链
3. **单例/观察者/适配器/策略**：Spring有，MyBatis无典型应用
**记忆口诀**：
- MyBatis：建造者构建配置、代理生成Mapper、责任链做插件、模板统一执行、工厂创建对象
- Spring：工厂管理Bean、代理做AOP、单例省内存、模板消样板、观察者做事件、适配器统一Controller、策略换算法
## 八、参考代码
代码路径：`note-know/src/main/java/com/ssl/know/java/pattern/`
- `建造者设计模式/BuilderPattern.java`：MyBatis建造者模式示例
- `责任链设计模式/ChainOfResponsibilityPattern.java`：MyBatis责任链模式示例
- 其他设计模式代码已在Spring文档中提供，此处不重复
