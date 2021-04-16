package com.imooc.ad.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1616:36
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdUnitKeywordResponse {
    private List<Long> ids;
}
