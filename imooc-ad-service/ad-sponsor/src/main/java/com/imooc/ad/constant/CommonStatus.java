package com.imooc.ad.constant;

import lombok.Getter;

/**
 * @author spin
 * @date 2021/4/169:52
 * @description: TODO 构造token
 */
@Getter
public enum CommonStatus {
    VALID(1,"有效状态"),
    INVALID(0,"无效状态");

    private Integer status;
    private String desc;

    CommonStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }}
