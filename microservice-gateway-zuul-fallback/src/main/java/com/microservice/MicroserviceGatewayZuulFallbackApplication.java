package com.microservice;

import com.microservice.filter.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
//@EnableFeignClients
public class MicroserviceGatewayZuulFallbackApplication {

	@Bean
	public MyFilter myFilter(){
		return  new MyFilter();
	}
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceGatewayZuulFallbackApplication.class, args);
	}

}
