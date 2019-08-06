package com.microservice.controller;

import com.microservice.domain.City;
import com.microservice.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * @author songabao
 * @date 2019/6/18 15:48
 */
@RestController
public class CityController {
    private static  final Logger log = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;


    @GetMapping(value = "getCity")
    public City getCity(@RequestParam("id") Long id) {
        HashMap<Object, Object> map = new HashMap<>();
        City city =  cityService.getCity(id);
        log.info("product       "+city);
        return city;
    }
}
