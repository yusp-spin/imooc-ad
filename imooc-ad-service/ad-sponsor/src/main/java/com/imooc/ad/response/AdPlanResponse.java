package com.imooc.ad.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author spin
 * @date 2021/4/1614:42
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanResponse {

    private Long id;

    private String planName;
}
