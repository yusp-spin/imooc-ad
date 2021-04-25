package com.imooc.ad.client;

import com.imooc.ad.common.Result;
import com.imooc.ad.request.AdPlanGetRequest;
import com.imooc.ad.response.AdPlanResponse;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/2122:10
 * @description: TODO
 */
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public Result<List<AdPlanResponse>> getAdPlans(AdPlanGetRequest request) {
        return new Result<>(-1,"eureka-client-ad-sponsor error");
    }
}
