package com.microservice.hystrix;

import com.microservice.domain.City;
import com.microservice.feign.CityFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author songabao
 * @date 2019/8/2 11:36
 */
@Component
public class CityHystrixFallback implements CityFeignClient{
    @Override
    public City getCity(Long id) {
        City city = new City();
        city.setId(id);
        city.setName("测试city");
        city.setStatus(1);
        return city;
    }
}
