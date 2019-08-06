package com.microservice.feign;

import com.microservice.domain.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author songabao
 * @date 2019/7/3 14:27
 */
@Component
@FeignClient(name = "microservice-config-server-production")
public interface FeignCity {
    @GetMapping(value = "getCity")
    public City getCity(@RequestParam("id") Long id);
}
