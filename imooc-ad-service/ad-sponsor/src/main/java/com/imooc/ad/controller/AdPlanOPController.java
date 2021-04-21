package com.imooc.ad.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.common.Result;
import com.imooc.ad.common.StatusCode;
import com.imooc.ad.common.StatusMes;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdPlanGetRequest;
import com.imooc.ad.request.AdPlanRequest;
import com.imooc.ad.response.AdPlanResponse;
import com.imooc.ad.service.IAdPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/2116:05
 * @description: TODO 创建推广计划
 */
@Slf4j
@RestController
@RequestMapping("/plan")
@Api(value = "AdPlanOPController", tags = "推广计划接口功能",description = "推广计划相关接口介绍")
public class AdPlanOPController {

    @Autowired
    private IAdPlanService adPlanService;

    @PostMapping("/create")
    @ApiOperation(value = "新增推广计划", notes = "createAdPlan",produces="application/json")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType="query", name = "id", value = "主键Id", required = false, dataType = "Integer"),
//            @ApiImplicitParam(paramType="query", name = "userId", value = "用户Id", required = true, dataType = "Integer"),
//            @ApiImplicitParam(paramType="query", name = "planName", value = "推广名称", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType="query", name = "startDate", value = "开始日期", required = true, dataType = "String"),
//            @ApiImplicitParam(paramType="query", name = "endDate", value = "结束日期", required = true, dataType = "String")
//    })
    public Result<AdPlanResponse> createAdPlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: createAdPlan -> {}", JSON.toJSONString(request));
        AdPlanResponse response = adPlanService.createAdPlan(request);
        return new Result<>(StatusCode.OK, StatusMes.CREATESUCESS,response);
    }

    @PostMapping("/get")
    @ApiOperation(value = "查询推广计划", notes = "getAdPlanByIds",produces="application/json")
    public Result<List<AdPlan>> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException {
        log.info("ad-sponsor: getAdPlanIds -> {}",JSON.toJSONString(request));
        List<AdPlan> result = adPlanService.getAdPlanByIds(request);
        return new Result<>(StatusCode.OK, StatusMes.SELECTSUCESS,result);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新推广计划", notes = "updatePlan",produces="application/json")
    public Result<AdPlanResponse> updatePlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: updateAdPlan -> {}",JSON.toJSONString(request));
        AdPlanResponse response = adPlanService.updateAdPlan(request);
        return new Result<>(StatusCode.OK, StatusMes.UPDATESUCESS,response);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除推广计划", notes = "deletePlan",produces="application/json")
    public Result<AdPlanResponse> deletePlan(@RequestBody AdPlanRequest request) throws AdException {
        log.info("ad-sponsor: deletePlan -> {}",JSON.toJSONString(request));
        adPlanService.deleteAdPlan(request);
        return new Result<>(StatusCode.OK, StatusMes.DELETESUCESS);
    }



}
