package com.imooc.ad.advice;

import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author spin
 * @date 2021/4/1519:45
 * @description: TODO
 */
@RestControllerAdvice //会作用与controller的方法上
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

     /**
     　　* @description: TODO 对controller的过滤选择
     　　* @author yusp
     　　* @date 2021/4/15 19:51
     　　*/
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //如果那个类被@IgnoreResponseAdvice标志
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }
        return true;
    }

     /**
     　　* @description: TODO 写入响应之前，拦截：将数据转化为CommonResponse类型
     　　* @author yusp
     　　* @date 2021/4/15 19:52
     　　*/
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> response = new CommonResponse<>(0,"");
        if(o == null) {
            return response;
        }else if(o instanceof  CommonResponse) {
            response = (CommonResponse<Object>)o;
        }else {
            response.setData(o);
        }
        return response;
    }
}
