package com.microservice;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import java.util.Properties;

@SpringBootApplication
@EnableEurekaClient
public class MicroserviceConfigServerConsumsApplication {
	@Bean
	@LoadBalanced
	public RestTemplate restTemplates(){
		return  new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceConfigServerConsumsApplication.class, args);
	}

}
