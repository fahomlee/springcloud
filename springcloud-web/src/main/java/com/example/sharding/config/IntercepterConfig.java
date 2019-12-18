package com.example.sharding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.sharding.interceptor.LoginInterceptor;

/**
 * 
 * 注册拦截器
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 忽略后面的路径（hystrix-Dashboard界面的路径）
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/hystrix/**","/webjars/**");
    }
}
