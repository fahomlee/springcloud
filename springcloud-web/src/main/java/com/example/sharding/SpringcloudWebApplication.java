package com.example.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableDiscoveryClient//支持nacos配置
@EnableHystrix//支持Hystrix
@EnableHystrixDashboard //Hystrix监控服务的情况  监控界面地址：http://localhost:8080/hystrix
public class SpringcloudWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudWebApplication.class, args);
	}

}
