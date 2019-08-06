package com.microservice;

import com.microservice.feign.CityFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@EnableHystrixDashboard
@ComponentScan("com.microservice")
public class MicroserviceConfigServerConsumsHystrixDashboradApplication {
//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate(){
//		return  new RestTemplate();
//	}

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConfigServerConsumsHystrixDashboradApplication.class, args);
	}

}
