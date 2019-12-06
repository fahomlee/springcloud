package com.example.sharding;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringcloudWebApplicationTests {
    
    @Value("${godlike}")
    private String godlike;

	@Test
	void contextLoads() {
	    //有则获取nacos中springcloud-web.yml文件中配置的信息，没有读取到nacos则会读取本地application的值
	    System.out.println("**********"+godlike);

	}

}
