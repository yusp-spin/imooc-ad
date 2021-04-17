package com.imooc.ad.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.service.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author spin
 * @date 2021/4/1621:12
 * @description: TODO
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger2.enable:false}")
    private boolean enableSwagger;

    /**
    * @description: TODO swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    * @author yusp
    * @date 2021/4/16 21:14
    */
    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        //设置所有接口的请求头
//        ticketPar.name("token").description("用户token信息")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                //header中的ticket参数非必填，传空也可以
//                .required(false).build();
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
                //接口文档的相关信息
                .apiInfo(apiInfo())
                //配置是否启用swagger
                .enable(enableSwagger)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.ad.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("广告系统项目")
                .description("广告系统项目API文档")
                .version("1.0.0")
                .contact(new Contact("yusp", "https://yusp-spin.github.io/", "623817565@qq.com"))
                .build();
    }
}
