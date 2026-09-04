# SpringMVC拦截器与过滤器详解
## 一、核心区别对比表
| 对比维度 | 过滤器(Filter) | 拦截器(Interceptor) |
|---------|---------------|-------------------|
| **规范来源** | Servlet规范（J2EE标准） | SpringMVC框架提供 |
| **实现原理** | 函数回调（doFilter） | AOP动态代理（反射） |
| **依赖容器** | 依赖Servlet容器（Tomcat等） | 依赖Spring容器 |
| **拦截范围** | 所有请求（包括静态资源、JSP等） | 只拦截Controller请求 |
| **执行时机** | 请求到达Servlet之前 | 请求到达Controller之前/之后 |
| **获取Spring Bean** | 困难（需要额外处理） | 容易（直接@Autowired） |
| **获取Controller信息** | 无法获取 | 可以获取HandlerMethod |
| **生命周期** | init → doFilter → destroy | preHandle → postHandle → afterCompletion |
| **使用场景** | 编码转换、跨域处理、XSS过滤 | 登录校验、权限验证、日志记录 |
## 二、执行顺序图解
```
客户端请求
    ↓
┌─────────────────┐
│   过滤器(Filter)  │  ← 第1层：Servlet容器级别
│   doFilter()    │
└────────┬────────┘
         ↓
┌─────────────────┐
│   DispatcherServlet │  ← SpringMVC核心Servlet
│   (前端控制器)      │
└────────┬────────┘
         ↓
┌─────────────────┐
│  拦截器(Interceptor)│  ← 第2层：SpringMVC级别
│   preHandle()   │
└────────┬────────┘
         ↓
┌─────────────────┐
│    Controller    │  ← 业务逻辑执行
│   (业务方法)      │
└────────┬────────┘
         ↓
┌─────────────────┐
│  拦截器(Interceptor)│
│   postHandle()  │
└────────┬────────┘
         ↓
┌─────────────────┐
│    视图渲染       │
└────────┬────────┘
         ↓
┌─────────────────┐
│  拦截器(Interceptor)│
│  afterCompletion()│
└────────┬────────┘
         ↓
┌─────────────────┐
│   过滤器(Filter)  │
│  doFilter()返回  │
└────────┬────────┘
         ↓
    客户端接收响应
```
**记忆口诀**：过滤器在外，拦截器在内；过滤器先执行，拦截器后执行。
## 三、代码示例
### 3.1 过滤器示例
```java
@Component
@Order(1)  // 执行顺序，数字越小越先执行
@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("【过滤器】init: 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
                        FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        System.out.println("【过滤器】doFilter: 请求到达Servlet前");
        
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // 跨域处理
        response.setHeader("Access-Control-Allow-Origin", "*");
        
        // 继续执行（如果不调用，请求不会到达Servlet）
        filterChain.doFilter(request, response);
        
        System.out.println("【过滤器】doFilter: 响应返回客户端前");
    }

    @Override
    public void destroy() {
        System.out.println("【过滤器】destroy: 销毁");
    }
}
```
### 3.2 拦截器示例
```java
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("【拦截器】preHandle: Controller执行前");
        // 可以获取到handler（Controller方法信息）
        System.out.println("  Handler: " + handler);
        // 登录校验
        String token = request.getHeader("token");
        if (token == null) {
            response.setStatus(401);
            return false;  // 中断请求
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
                          Object handler, ModelAndView modelAndView) {
        System.out.println("【拦截器】postHandle: Controller执行后，视图渲染前");
        // 可以修改返回的ModelAndView
        if (modelAndView != null) {
            modelAndView.addObject("extra", "拦截器添加的数据");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                               Object handler, Exception ex) {
        System.out.println("【拦截器】afterCompletion: 请求完成后");
        if (ex != null) {
            System.out.println("  异常信息: " + ex.getMessage());
        }
    }
}
```
### 3.3 拦截器配置
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**")           // 拦截所有请求
                .excludePathPatterns("/login",    // 排除登录接口
                                   "/static/**",   // 排除静态资源
                                   "/error");      // 排除错误页
    }
}
```
## 四、关键区别详解
### 4.1 拦截范围不同
**过滤器**：
- 拦截所有请求：Controller、静态资源（JS/CSS/图片）、JSP、Servlet等
- 配置：`urlPatterns = "/*"` 或 `urlPatterns = "*.do"`
**拦截器**：
- 只拦截Controller请求（DispatcherServlet处理的请求）
- 不拦截静态资源（默认由Servlet容器直接处理）
- 配置：`addPathPatterns("/**")`
### 4.2 获取Spring Bean的能力
**过滤器**：
```java
// 困难！需要手动从ServletContext获取ApplicationContext
@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    // 方式1：通过ServletContext获取（麻烦）
    ApplicationContext ctx = WebApplicationContextUtils
        .getWebApplicationContext(request.getServletContext());
    UserService userService = ctx.getBean(UserService.class);
    
    // 方式2：使用Spring的DelegatingFilterProxy（推荐）
}
```
**拦截器**：
```java
// 简单！直接@Autowired注入
@Component
public class MyInterceptor implements HandlerInterceptor {
    
    @Autowired
    private UserService userService;  // 直接注入，非常方便
    
    @Override
    public boolean preHandle(...) {
        userService.doSomething();  // 直接使用
    }
}
```
### 4.3 获取Controller信息
**过滤器**：
```java
// 无法获取Controller信息，只能获取原始请求
@Override
public void doFilter(...) {
    // 不知道请求要调用哪个Controller方法
}
```
**拦截器**：
```java
// 可以获取HandlerMethod，知道具体调用哪个方法
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (handler instanceof HandlerMethod) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取方法名
        String methodName = handlerMethod.getMethod().getName();
        // 获取类名
        String className = handlerMethod.getBeanType().getName();
        // 获取方法上的注解
        RequiresAuth annotation = handlerMethod.getMethodAnnotation(RequiresAuth.class);
    }
}
```
### 4.4 使用场景对比
**过滤器适用场景**：
1. **设置编码**：`request.setCharacterEncoding("UTF-8")`
2. **跨域处理**：`response.setHeader("Access-Control-Allow-Origin", "*")`
3. **XSS攻击防护**：对请求参数进行转义
4. **敏感词过滤**：替换请求中的敏感词汇
5. **静态资源处理**：压缩、缓存控制
6. **请求日志**：记录所有请求（包括静态资源）
**拦截器适用场景**：
1. **登录校验**：检查用户是否登录
2. **权限验证**：检查用户是否有权限访问某个方法
3. **接口耗时统计**：统计Controller方法的执行时间
4. **操作日志**：记录用户的操作行为（需要知道调用了哪个方法）
5. **统一返回处理**：在postHandle中修改返回结果
6. **多租户切换**：根据请求头切换数据源
## 五、常见问题
### 5.1 过滤器和拦截器同时存在，执行顺序？
```
请求 → 过滤器1 → 过滤器2 → 拦截器1 → 拦截器2 → Controller
     ← 拦截器2 ← 拦截器1 ← 过滤器2 ← 过滤器1 ← 响应
```
**原则**：过滤器在外层，先执行；拦截器在内层，后执行。
### 5.2 为什么拦截器不拦截静态资源？
因为静态资源不经过DispatcherServlet，而是由Servlet容器（如Tomcat）直接处理。拦截器是SpringMVC的组件，只能拦截经过DispatcherServlet的请求。
### 5.3 如何让过滤器也能注入Spring Bean？
**方案1：使用DelegatingFilterProxy（推荐）**
```java
@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<MyFilter> myFilter() {
        FilterRegistrationBean<MyFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new MyFilter());  // Spring管理的Filter
        bean.addUrlPatterns("/*");
        return bean;
    }
}
```
**方案2：使用@Component + @WebFilter**
```java
@Component
@WebFilter("/*")
public class MyFilter implements Filter {
    @Autowired
    private UserService userService;  // 可以注入
}
```
### 5.4 拦截器中如何获取请求体？
**问题**：拦截器中直接读取请求体会导致后续Controller无法读取。
**解决方案**：
```java
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 使用ContentCachingRequestWrapper包装请求
    ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
    // 读取请求体
    String body = new String(wrappedRequest.getContentAsByteArray());
    // 继续执行，Controller可以正常读取
    return true;
}
```
## 六、面试高频问题
### 6.1 过滤器和拦截器的区别？
**标准答案**：
1. **规范不同**：Filter是Servlet规范，Interceptor是SpringMVC框架
2. **原理不同**：Filter基于函数回调，Interceptor基于AOP
3. **范围不同**：Filter拦截所有请求，Interceptor只拦截Controller
4. **依赖不同**：Filter依赖Servlet容器，Interceptor依赖Spring容器
5. **场景不同**：Filter用于编码/跨域/XSS，Interceptor用于登录/权限/日志
### 6.2 什么场景用Filter，什么场景用Interceptor？
- **用Filter**：需要拦截静态资源、不依赖Spring、处理编码/跨域
- **用Interceptor**：需要获取Controller信息、需要注入Spring Bean、处理业务逻辑
### 6.3 拦截器的三个方法分别在什么时候执行？
- `preHandle`：Controller执行前（可用于登录校验）
- `postHandle`：Controller执行后，视图渲染前（可修改ModelAndView）
- `afterCompletion`：视图渲染后，请求完成（用于资源清理）
## 七、参考代码
代码路径：`note-know/src/main/java/com/ssl/know/java/`
- `filter/MyFilter.java`：过滤器示例
- `interceptor/MyInterceptor.java`：拦截器示例
- `interceptor/WebConfig.java`：拦截器配置
- `controller/TestController.java`：测试Controller
