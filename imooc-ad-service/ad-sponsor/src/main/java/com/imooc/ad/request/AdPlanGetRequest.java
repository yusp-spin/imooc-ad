package com.imooc.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1614:36
 * @description: TODO 查询请求
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanGetRequest {
    private Long userId;
    private List<Long> ids;

    public boolean validate() {
        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
