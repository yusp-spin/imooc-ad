package com.imooc.ad.response;

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
public class CreateUserResponse {
    private Long userId;
    private String userName;
    private String token;
    private Date createTime;
    private Date updateTime;
}
