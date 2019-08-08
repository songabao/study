package com.microservice.controller;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songabao
 * @date 2019/8/7 10:32
 */
@RestController
@RefreshScope
public class ConfigClientController {
    @Value("${key}")
    private String  key;

    @GetMapping(value = "key")
    public String getProfiles(){
        return key;
    }
}
