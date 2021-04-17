package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author spin
 * @date 2021/4/169:39
 * @description: TODO
 */
@EnableFeignClients
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.imooc.ad.config"})
public class SponsorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SponsorApplication.class,args);
    }
}
