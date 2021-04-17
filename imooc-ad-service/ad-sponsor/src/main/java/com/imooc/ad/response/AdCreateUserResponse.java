package com.imooc.ad.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author spin
 * @date 2021/4/1613:49
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

@ApiModel(value = "AdCreateUserResponse", description = "用户相关返回接口")
public class AdCreateUserResponse {

    @ApiModelProperty(name = "userId", value = "用户Id", example = "1")
    private Long userId;
    @ApiModelProperty(name = "userName", value = "用户名", example = "xiaofang")
    private String userName;
    @ApiModelProperty(name = "token", value = "Token")
    private String token;
    @ApiModelProperty(name = "createTime", value = "创建时间", example = "yyyy-MM-dd\"")
    private Date createTime;
    @ApiModelProperty(name = "updateTime", value = "更新时间", example = "yyyy-MM-dd\"")
    private Date updateTime;
}
