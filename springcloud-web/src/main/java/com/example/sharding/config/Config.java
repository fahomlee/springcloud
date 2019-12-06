package com.example.sharding.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import lombok.Data;


/**
 * 配置文件类（nacos上配置参数对象化），配置文件参考：src/main/resources/nacosConfigFiles下的文件
 * @author xxxxx
 * 
 */
@Configuration
@Data
//动态获取nacos上配置的值，需要加上@RefreshScope注解
@RefreshScope
public class Config {
    
    @Value("${fieldA}")
    private String fieldA;
    
    @Value("${fieldB}")
    private String fieldB;

}
