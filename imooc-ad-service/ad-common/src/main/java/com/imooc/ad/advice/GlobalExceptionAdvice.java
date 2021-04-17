package com.imooc.ad.advice;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author spin
 * @date 2021/4/1520:02
 * @description: TODO 定义 异常处理方法
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public Result<String> handlerAdException(HttpServletRequest req, AdException ex) {
        Result<String> response = new Result<>(-1,"business error");
        response.setData(ex.getMessage());
        return response;
    }
}
