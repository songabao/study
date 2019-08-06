package com.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceRxjavaStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRxjavaStudyApplication.class, args);
	}

}
