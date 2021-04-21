package com.imooc.ad.request;

import com.imooc.ad.constant.CommonStatus;
import com.imooc.ad.entity.AdCreative;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author spin
 * @date 2021/4/1619:51
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AdCreativeRequest", description = "创意相关请求接口")
public class AdCreativeRequest {

    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Long size;
    private Integer duration;
    private Long userId;
    private String url;

    public AdCreative convertToEntity(){
        AdCreative adCreative = new AdCreative(){{
            setName(name);
            setType(type);
            setMaterialType(materialType);
            setHeight(height);
            setWidth(width);
            setSize(size);
            setDuration(duration);
            setAuditStatus(CommonStatus.VALID.getStatus());
            setUserId(userId);
            setUrl(url);
            setCreateTime(new Date());
            setUpdateTime(getCreateTime());
        }};
        return adCreative;
    }
}
