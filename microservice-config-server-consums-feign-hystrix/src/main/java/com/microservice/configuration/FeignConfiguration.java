package com.microservice.configuration;

import com.microservice.feign.CityFeignClient;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author songabao
 * @date 2019/7/23 16:23
 */
@Configuration
@Import(FeignClientsConfiguration.class)
public class FeignConfiguration {

    @Bean
    public CityFeignClient cityFeignClient(Contract contract, Decoder decoder, Encoder encoder){
        return Feign.builder().contract(contract).encoder(encoder).decoder(decoder).target(CityFeignClient.class, "http://localhost:8941");
    }
    @Bean
    Logger.Level feignLogger(){
        return  Logger.Level.FULL;
    }
}
