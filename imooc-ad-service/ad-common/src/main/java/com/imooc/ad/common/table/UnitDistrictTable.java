package com.imooc.ad.common.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitDistrictTable {

    private Long unitId;
    private String province;
    private String city;

}
