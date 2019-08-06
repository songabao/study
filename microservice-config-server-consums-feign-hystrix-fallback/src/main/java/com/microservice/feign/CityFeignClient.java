package com.microservice.feign;

import com.microservice.domain.City;
import com.microservice.hystrix.CityHystrixFallback;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author songabao
 * @date 2019/7/23 16:33
 */
@FeignClient(name = "microservice-config-server-feign-hystrix-fallback-production",fallback = CityHystrixFallback.class)
public interface CityFeignClient {

    @GetMapping("getCity")
    public City getCity(@RequestParam("id") Long id);
}
