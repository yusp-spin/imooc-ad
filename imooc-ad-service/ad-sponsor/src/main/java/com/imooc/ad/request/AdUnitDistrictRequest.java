package com.imooc.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1616:42
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitDistrictRequest {

    private List<UnitDistrict> unitDistricts;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnitDistrict {
        private Long unitId;
        private String province;
        private String city;
    }
}
