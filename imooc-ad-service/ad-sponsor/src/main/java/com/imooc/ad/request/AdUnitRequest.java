package com.imooc.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author spin
 * @date 2021/4/1615:51
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitRequest {

    private Long planId;
    private String unitName;

    private Integer positionType;
    private Long budget;

    public boolean createValidate() {
        return planId != null
                && !StringUtils.isEmpty(unitName)
                && positionType != null
                && budget != null;
    }
}
