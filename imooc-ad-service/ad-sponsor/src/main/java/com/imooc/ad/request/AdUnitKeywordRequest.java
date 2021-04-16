package com.imooc.ad.request;

import com.imooc.ad.entity.unit_condition.AdUnitKeyword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1616:31
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordRequest {
    private List<UnitKeyword> unitKeywords;

    //批量请求的时候可以这样
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitKeyword {
        private Long unitId;
        private String keyword;
    }
}
