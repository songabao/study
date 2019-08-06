package com.microservice.configuration;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songabao
 * @date 2019/7/23 16:23
 */
@Configuration
public class FeignConfiguration {
    @Bean
    public Contract feignContrat(){

        return new feign.Contract.Default();
    }
    @Bean
    Logger.Level feignLogger(){
        return   Logger.Level.FULL;
    }
}
