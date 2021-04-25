package com.imooc.ad.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.client.SponsorClient;
import com.imooc.ad.common.Result;
import com.imooc.ad.request.AdPlanGetRequest;
import com.imooc.ad.response.AdPlanResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/21 21:01
 * @description: TODO
 */
@Slf4j
@RestController
@RequestMapping("/plan")
@Api(value = "SearchController", tags = "RPC推广计划",description = "RPC调用推广计划介绍")
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SponsorClient sponsorClient;

    @IgnoreResponseAdvice
    @PostMapping("/getAdPlans")
    @ApiOperation(value = "Feign获取推广计划", notes = "getAdPlan",produces="application/json")
    public Result<List<AdPlanResponse>> getAdPlan (@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlan -> {}", JSON.toJSONString(request));
        return sponsorClient.getAdPlans(request);
    }

    @SuppressWarnings("all")
    @IgnoreResponseAdvice
    @PostMapping("/getAdPlansByRibbon")
    @ApiOperation(value = "Ribbon获取推广计划", notes = "getAdPlanByRibbon",produces="application/json")
    public Result<List<AdPlanResponse>> getAdPlanByRibbon(@RequestBody AdPlanGetRequest request) {
        log.info("ad-search: getAdPlansByRibbon -> {}", JSON.toJSONString(request));
        return restTemplate.postForEntity(
                "http://eureka-client-ad-sponsor/ad-sponsor/plan/get",
                request,
                Result.class
        ).getBody();
    }

}
