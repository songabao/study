package com.microservice.controller;

import ch.qos.logback.core.encoder.Encoder;
import com.microservice.domain.City;
import com.microservice.feign.CityFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Semaphore;

/**
 * @author songabao
 * @date 2019/7/25 10:29
 */
@RestController
@RequestMapping(value = "city")
public class CityController {
    private final Logger log = LoggerFactory.getLogger(CityController.class);
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private CityFeignClient feignClient;

    @HystrixCommand(fallbackMethod = "failCity" ,
            commandProperties = @HystrixProperty(name="execution.isolation.strategy",value = "Semaphore"))
    @GetMapping(value = "getCity")
    public City getCity(@RequestParam("id") Long id){
         ServiceInstance choose = loadBalancerClient.choose("microservice-config-server-feign-head-production");
        log.info("{}:{}",choose.getHost(),choose.getPort());
        log.info("id : "+id);
        return  this.feignClient.getCity(id);
    }

    public  City failCity(Long id){
        City city = new City();
        city.setId(id);
        city.setName("测试city");
        city.setStatus(1);
        return city;
    }
}
