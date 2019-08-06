package com.microservice.controller;

import com.microservice.domain.City;
import com.microservice.feign.FeignCity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songabao
 * @date 2019/7/3 14:35
 */
@RestController
@RequestMapping(value = "/city")
public class FeignCityController {
    private static  final Logger log = LoggerFactory.getLogger(FeignCityController.class);
    @Autowired
    private FeignCity feignCity;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/getCity")
    public City getCity(@RequestParam("id") Long  id){
        ServiceInstance choose = loadBalancerClient.choose("microservice-config-server-production");
        log.info("{}:{}",choose.getHost(),choose.getPort());
        return this.feignCity.getCity(id);
    }
}
