package com.imooc.ad.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.common.Result;
import com.imooc.ad.common.StatusCode;
import com.imooc.ad.common.StatusMes;
import com.imooc.ad.entity.unit_condition.AdUnitDistrict;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.*;
import com.imooc.ad.response.*;
import com.imooc.ad.service.IAdUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spin
 * @date 2021/4/2119:21
 * @description: TODO
 */
@Slf4j
@RestController
@Api(value = "AdUnitOPController", tags = "推广单元接口功能",description = "推广单元相关接口介绍")
@RequestMapping("/unit")
public class AdUnitOPController {

    @Autowired
    private IAdUnitService adUnitService;

    @PostMapping("/createUnit")
    @ApiOperation(value = "新增推广单元", notes = "createUnit",produces="application/json")
    public Result<AdUnitResponse> createUnit(@RequestBody AdUnitRequest request) throws AdException {
        log.info("ad-sponsor: createUnit -> {}", JSON.toJSONString(request));
        AdUnitResponse response = adUnitService.createUnit(request);
        return new Result<>(StatusCode.OK, StatusMes.CREATESUCESS,response);
    }

    @PostMapping("/createUnitKeyword")
    @ApiOperation(value = "新增推广关键字", notes = "createUnitKeyword",produces="application/json")
    public Result<AdUnitKeywordResponse> createUnitKeyword(@RequestBody AdUnitKeywordRequest request) throws AdException {
        log.info("ad-sponsor: createUnitKeyword -> {}",JSON.toJSONString(request));
        AdUnitKeywordResponse unitKeyword = adUnitService.createUnitKeyword(request);
        return new Result<>(StatusCode.OK, StatusMes.CREATESUCESS,unitKeyword);
    }

    @PostMapping("/createUnitIt")
    @ApiOperation(value = "新增推广兴趣限制", notes = "createUnitIt",produces="application/json")
    public Result<AdUnitItResponse> createUnitIt(@RequestBody AdUnitItRequest request) throws AdException {
        log.info("ad-sponsor: createUnitIt -> {}",JSON.toJSONString(request));
        AdUnitItResponse unitIt = adUnitService.createUnitIt(request);
        return new Result<>(StatusCode.OK, StatusMes.CREATESUCESS,unitIt);
    }

    @PostMapping("/createUnitDistrict")
    @ApiOperation(value = "新增推广地区限制", notes = "createUnitDistrict",produces="application/json")
    public Result<AdUnitDistrictResponse> createUnitDistrict(@RequestBody AdUnitDistrictRequest request) throws AdException {
        log.info("ad-sponsor: createUnitDistrict -> {}",JSON.toJSONString(request));
        AdUnitDistrictResponse unitIt = adUnitService.createUnitDistrict(request);
        return new Result<>(StatusCode.OK, StatusMes.CREATESUCESS,unitIt);
    }

    @PostMapping("/createCreativeUnit")
    @ApiOperation(value = "新增推广创意单元", notes = "createCreativeUnit",produces="application/json")
    public Result<CreativeUnitResponse> createCreativeUnit(@RequestBody CreativeUnitRequest request) throws AdException {
        log.info("ad-sponsor: createCreativeUnit -> {}",JSON.toJSONString(request));
        CreativeUnitResponse creativeUnitResponse = adUnitService.createAdCreatveUnit(request);
        return new Result<>(StatusCode.OK, StatusMes.CREATESUCESS,creativeUnitResponse);
    }


}
