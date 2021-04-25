package com.imooc.ad.common.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author spin
 * @date 2021/4/2320:26
 * @description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdPlanTable {
    private Long planId;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;
}
