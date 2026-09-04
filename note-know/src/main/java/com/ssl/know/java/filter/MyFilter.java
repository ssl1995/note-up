package com.ssl.know.java.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Servlet过滤器示例
 * 过滤器是Servlet规范提供的，基于函数回调实现
 */
@Component
@Order(1)  // 执行顺序，数字越小越先执行
@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("【过滤器】init: 初始化");
    }

    /**
     * 在请求到达Servlet之前执行
     * 可以拦截所有请求（包括静态资源）
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
                        FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        System.out.println("【过滤器】doFilter: 请求到达Servlet前");
        System.out.println("  URI: " + request.getRequestURI());
        System.out.println("  Method: " + request.getMethod());
        
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
