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
        //可以指定拦截地址
        registry.addInterceptor(new LoginInterceptor());
    }
}
