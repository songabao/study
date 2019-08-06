package com.microservice.feign;

import com.microservice.configuration.FeignConfiguration;
import com.microservice.domain.City;
import feign.Param;
import feign.RequestLine;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @author songabao
 * @date 2019/7/23 16:33
 */

@FeignClient(name="microservice-config-server-feign-customizing-production",configuration=FeignConfiguration.class)
@Component
public interface CityFeignClient {

    @RequestLine("GET /getCity/{id}")
    public City getCity(@Param("id") Long id);

    @RequestLine("POST /findCity")
    public City getCity(@RequestBody City city);
}
