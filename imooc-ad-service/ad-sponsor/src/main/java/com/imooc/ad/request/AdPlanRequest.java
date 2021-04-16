package com.imooc.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author spin
 * @date 2021/4/1614:28
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanRequest {
    private Long id;
    private Long userId;
    private String planName;
    private String startDate;
    private String endDate;

    /**
    * @description: TODO 对创建操作的输入参数进行校验
    * @author yusp
    * @date 2021/4/16 14:34 
    */
    public boolean createValidate() {
        return userId != null
                && !StringUtils.isEmpty(planName)
                && !StringUtils.isEmpty(startDate)
                && !StringUtils.isEmpty(endDate);
    }
    
    /**
    * @description: TODO 对更新操作的输入参数进行校验
    * @author yusp
    * @date 2021/4/16 14:34
    */
    public boolean updateValidate() {
        return id != null && userId != null;
    }

    /**
    * @description: TODO 对删除操作的输入参数进行校验
    * @author yusp
    * @date 2021/4/16 14:34
    */
    public boolean deleteValidate() {
        return id != null && userId != null;
    }
    
    
}
