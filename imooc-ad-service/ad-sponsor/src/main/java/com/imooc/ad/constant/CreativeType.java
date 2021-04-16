package com.imooc.ad.constant;

import lombok.Getter;

/**
 * @author spin
 * @date 2021/4/1613:00
 * @description: TODO
 */
@Getter
public enum CreativeType {
    IMAGE(1,"图片"),
    VIDEO(2,"视频"),
    TEXT(3,"文本");

    private int type;
    private String desc;

    CreativeType(int type,String desc) {
        this.type = type;
        this.desc = desc;
    }
}
