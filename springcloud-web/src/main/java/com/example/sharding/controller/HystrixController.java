package com.example.sharding.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.sharding.interceptor.NoLogin;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hystrixTest")
@DefaultProperties(defaultFallback = "defaultFail")
@Slf4j
public class HystrixController {
    
    /**
     * 服务降级执行fail12
     * fail11报错执行fail12
     * @return
     */
    @HystrixCommand(fallbackMethod = "fail12")
    @RequestMapping(value="/fail11", method = RequestMethod.GET)
    @NoLogin
    public String fail11() {
        log.info("request fail11 ....");
        throw new RuntimeException();
    }

    
    @HystrixCommand
    private String fail12() {
        return "fail12";
    }
    
    
    
    /**
     * 服务降级，最终执行defaultFail
     * fail21报错执行fail22，fail22报错执行fail23，fail23报错执行defaultFail
     * @return
     */
    @HystrixCommand(fallbackMethod = "fail22")
    @RequestMapping(value="/fail21",method = RequestMethod.GET)
    @NoLogin
    public String fail21() {
        log.info("request fail21 ....");
        throw new RuntimeException();
    }
    
    @HystrixCommand(fallbackMethod = "fail23")
    private String fail22() {
        throw new RuntimeException();
    }
    
    @HystrixCommand
    private String fail23() {
        throw new RuntimeException();
    }
    
    
    /**
     * 服务降级默认执行方法
     * @return
     */
    @SuppressWarnings("unused")
    private String defaultFail() {
        return "defaultFail";
    }
    
    
}
