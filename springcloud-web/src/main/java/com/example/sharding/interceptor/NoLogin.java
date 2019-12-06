package com.example.sharding.interceptor;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 免登校验注解
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface NoLogin {
}
