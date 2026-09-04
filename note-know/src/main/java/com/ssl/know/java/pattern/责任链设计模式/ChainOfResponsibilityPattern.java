package com.ssl.know.java.pattern.责任链设计模式;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis责任链模式示例
 * MyBatis中典型的责任链模式应用：插件机制（Interceptor）
 */
public class ChainOfResponsibilityPattern {

    /**
     * 目标对象
     */
    interface Target {
        void execute();
    }

    /**
     * 目标对象实现
     */
    static class TargetImpl implements Target {
        @Override
        public void execute() {
            System.out.println("目标对象：执行核心逻辑");
        }
    }

    /**
     * 拦截器接口
     */
    interface Interceptor {
        Object intercept(Invocation invocation);
    }

    /**
     * 调用信息
     */
    static class Invocation {
        private Target target;
        private List<Interceptor> interceptors;
        private int index = 0;

        public Invocation(Target target, List<Interceptor> interceptors) {
            this.target = target;
            this.interceptors = interceptors;
        }

        public Object proceed() {
            if (index >= interceptors.size()) {
                // 所有拦截器执行完毕，执行目标方法
                target.execute();
                return null;
            }
            // 获取当前拦截器并执行
            Interceptor interceptor = interceptors.get(index);
            index++;
            return interceptor.intercept(this);
        }
    }

    /**
     * 日志拦截器
     */
    static class LogInterceptor implements Interceptor {
        @Override
        public Object intercept(Invocation invocation) {
            System.out.println("【日志拦截器】开始记录日志");
            Object result = invocation.proceed();
            System.out.println("【日志拦截器】日志记录完成");
            return result;
        }
    }

    /**
     * 缓存拦截器
     */
    static class CacheInterceptor implements Interceptor {
        @Override
        public Object intercept(Invocation invocation) {
            System.out.println("【缓存拦截器】查询缓存");
            Object result = invocation.proceed();
            System.out.println("【缓存拦截器】更新缓存");
            return result;
        }
    }

    /**
     * 分页拦截器
     */
    static class PageInterceptor implements Interceptor {
        @Override
        public Object intercept(Invocation invocation) {
            System.out.println("【分页拦截器】解析分页参数");
            Object result = invocation.proceed();
            System.out.println("【分页拦截器】封装分页结果");
            return result;
        }
    }

    /**
     * 插件链：责任链的核心
     */
    static class InterceptorChain {
        private List<Interceptor> interceptors = new ArrayList<>();

        public void addInterceptor(Interceptor interceptor) {
            interceptors.add(interceptor);
        }

        public Object pluginAll(Target target) {
            Invocation invocation = new Invocation(target, interceptors);
            return invocation.proceed();
        }
    }

    public static void main(String[] args) {
        // 创建目标对象
        Target target = new TargetImpl();

        // 创建拦截器链
        InterceptorChain chain = new InterceptorChain();
        chain.addInterceptor(new LogInterceptor());
        chain.addInterceptor(new CacheInterceptor());
        chain.addInterceptor(new PageInterceptor());

        // 执行责任链
        System.out.println("=== 责任链执行 ===");
        chain.pluginAll(target);

        System.out.println("\n=== 执行顺序说明 ===");
        System.out.println("添加顺序: 日志 → 缓存 → 分页");
        System.out.println("执行顺序: 日志前 → 缓存前 → 分页前 → 目标 → 分页后 → 缓存后 → 日志后");
    }
}
