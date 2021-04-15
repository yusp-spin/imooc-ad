package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author spin
 * @date 2021/4/1516:17
 * @description: TODO 启动类
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaAppliction {

    public static void main(String[] args) {
        SpringApplication.run(EurekaAppliction.class,args);
    }
}
