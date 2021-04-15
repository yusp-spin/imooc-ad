package com.imooc.ad.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1520:11
 * @description: TODO 通用配置：定义一个 HTTP 的消息转换器。这个转换器实现了对象的转换，将 Java 的实体对象转换为实体输出流，或者将 HTTP 的输出转换成 Java 的实体对象
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        //添加一个消息转化器：将Java对象转换为JSON对象
        //告系统的其他模块引用这个 common 之后，响应和请求对象的处理都会被消息转化器进行处理。
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
