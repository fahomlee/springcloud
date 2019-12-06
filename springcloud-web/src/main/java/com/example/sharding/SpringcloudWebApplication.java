package com.example.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudWebApplication.class, args);
	}

}
