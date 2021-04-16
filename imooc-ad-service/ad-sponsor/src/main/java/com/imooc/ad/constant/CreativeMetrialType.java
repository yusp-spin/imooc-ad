package com.imooc.ad.constant;

import lombok.Getter;

/**
 * @author spin
 * @date 2021/4/1613:02
 * @description: TODO
 */
@Getter
public enum CreativeMetrialType {

    JPG(1,"jpg"),
    BMP(2,"bmp"),

    MP4(3,"MP4"),
    AVI(4,"aiv"),

    TXT(5,"TXT");

    private int type;
    private String desc;

    CreativeMetrialType(int type,String desc) {
        this.type = type;
        this.desc = desc;
    }
}
