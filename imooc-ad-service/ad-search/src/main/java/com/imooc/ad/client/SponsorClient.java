package com.imooc.ad.client;

import com.imooc.ad.common.Result;
import com.imooc.ad.request.AdPlanGetRequest;
import com.imooc.ad.response.AdPlanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/2122:07
 * @description: TODO
 */
@FeignClient(value = "eureka-client-ad-sponsor",fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @RequestMapping(value = "ad-sponsor/adPlan/get",method = RequestMethod.POST)
    Result<List<AdPlanResponse>> getAdPlans(@RequestBody AdPlanGetRequest request);

}
