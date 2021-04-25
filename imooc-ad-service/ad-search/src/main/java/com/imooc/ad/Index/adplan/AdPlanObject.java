package com.imooc.ad.Index.adplan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author spin
 * @date 2021/4/22 13:12
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanObject {
    private Long planId;
    private Long userId;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;

    /**
    * @description: TODO 由于更新数据的时候并不一定是更新所有字段，这里是为了确定更新的字段
    * @author yusp
    * @date 2021/4/22 13:16
    */
    public void update(AdPlanObject newObject) {
        if (null != newObject.getPlanId()){
            this.planId = newObject.getPlanId();
        }
        if (null != newObject.getUserId()){
            this.userId = newObject.getUserId();
        }
        if (null != newObject.getPlanStatus()) {
            this.planStatus = newObject.getPlanStatus();
        }
        if (null != newObject.getStartDate()) {
            this.startDate = newObject.getStartDate();
        }
        if (null != newObject.getEndDate()) {
            this.endDate = newObject.getEndDate();
        }
    }
}
