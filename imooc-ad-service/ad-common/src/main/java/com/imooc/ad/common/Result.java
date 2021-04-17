package com.imooc.ad.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author spin
 * @date 2021/4/1519:21
 * @description: TODO 统一的响应结构
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
