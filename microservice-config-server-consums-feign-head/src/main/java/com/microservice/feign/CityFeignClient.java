package com.microservice.feign;

import com.microservice.domain.City;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author songabao
 * @date 2019/7/23 16:33
 */

public interface CityFeignClient {

    @GetMapping("getCity")
    public City getCity(@RequestParam("id") Long id);
}
