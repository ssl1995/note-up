package com.ssl.know.java.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpringMVC拦截器示例
 * 拦截器是SpringMVC提供的，基于AOP实现
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 在Controller方法执行之前调用
     * @return true-继续执行，false-中断执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("【拦截器】preHandle: Controller执行前");
        // 可以获取到handler（Controller方法信息）
        System.out.println("  Handler: " + handler);
        // 可以获取请求参数
        String token = request.getHeader("token");
        if (token == null) {
            response.setStatus(401);
            return false;  // 中断请求
        }
        return true;
    }

    /**
     * 在Controller方法执行之后、视图渲染之前调用
     * 可以修改ModelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
                          Object handler, ModelAndView modelAndView) {
        System.out.println("【拦截器】postHandle: Controller执行后，视图渲染前");
        // 可以修改返回的ModelAndView
        if (modelAndView != null) {
            modelAndView.addObject("extra", "拦截器添加的数据");
        }
    }

    /**
     * 在整个请求完成后调用（视图渲染完毕）
     * 用于资源清理
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                               Object handler, Exception ex) {
        System.out.println("【拦截器】afterCompletion: 请求完成后");
        if (ex != null) {
            System.out.println("  异常信息: " + ex.getMessage());
        }
    }
}
