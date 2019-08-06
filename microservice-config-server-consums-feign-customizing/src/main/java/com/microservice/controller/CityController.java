package com.microservice.controller;

import com.microservice.domain.City;
import com.microservice.feign.CityFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value ="getCity")
    public City getCity(@RequestParam("id") Long id){
        ServiceInstance choose = loadBalancerClient.choose("microservice-config-server-feign-customizing-production");
        log.info("{}:{}",choose.getHost(),choose.getPort());
        return  this.feignClient.getCity(id);
    }

    @PostMapping(value ="findCity",produces = "application/json;charset=UTF-8")
    public City getCity(@RequestBody City city){
        ServiceInstance choose = loadBalancerClient.choose("microservice-config-server-feign-customizing-production");
        log.info("{}:{}",choose.getHost(),choose.getPort());
        return  this.feignClient.getCity(city);
    }
}
