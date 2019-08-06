package com.microservice.controller;

import com.microservice.domain.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author songabao
 * @date 2019/6/18 16:10
 */
@RestController
public class CityController {
    private static  final Logger log = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalanced;

    @GetMapping(value = "/getCity")
    public City getCity(@RequestParam("id") Long id){
        ResponseEntity<City> forObject = restTemplate.getForEntity("Http://microservice-config-server-production/getCity?id=" + id, City.class);
        City city  = forObject.getBody();
        return city;
    }

    /**
     * <b>说明：查看当前选中的节点</b>
     */
    @GetMapping(value = "/findUserInstance")
    public void  findUserInstance(){
        ServiceInstance choose = this.loadBalanced.choose("microservice-config-server-production");
        log.info("{}：{}：{}",choose.getInstanceId(),choose.getHost(),choose.getPort());
    }
}
