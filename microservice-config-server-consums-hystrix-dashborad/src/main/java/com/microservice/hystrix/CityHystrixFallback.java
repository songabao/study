package com.microservice.hystrix;

import com.microservice.domain.City;
import com.microservice.feign.CityFeignClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author songabao
 * @date 2019/8/2 11:36
 */
@Component
public class CityHystrixFallback implements FallbackFactory<CityFeignClient>{

    static Logger logger = LoggerFactory.getLogger(CityHystrixFallback.class);
    @Override
    public CityFeignClient create(Throwable cause) {
        return new CityFeignClient() {
            @Override
            public City getCity(Long id) {
                CityHystrixFallback.logger.info("city--->"+ cause);
                City city  = new City();
                city.setStatus(1);
                city.setStatus(1);
                city.setName("测试");
                city.setId(id);
                return city;
            }
        };
    }
}
