package com.imooc.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1620:23
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreativeUnitRequest {
    private List<CreativeUnitItem> unitItems;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreativeUnitItem{

        private Long creativeId;
        private Long unitId;
    }
}
