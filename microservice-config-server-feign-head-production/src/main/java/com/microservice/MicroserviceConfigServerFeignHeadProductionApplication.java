package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceConfigServerFeignHeadProductionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConfigServerFeignHeadProductionApplication.class, args);
	}

}
