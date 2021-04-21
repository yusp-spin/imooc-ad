package com.imooc.ad.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.common.Result;
import com.imooc.ad.common.StatusCode;
import com.imooc.ad.common.StatusMes;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdCreativeRequest;
import com.imooc.ad.response.AdCreativeResponse;
import com.imooc.ad.service.IAdCreativeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spin
 * @date 2021/4/2120:00
 * @description: TODO
 */
@Slf4j
@RestController()
@Api(value = "CreativeOPController", tags = "创意接口功能",description = "创意相关接口介绍")
@RequestMapping("/creative")
public class CreativeOPController {

    @Autowired
    private IAdCreativeService creativeService;

    @PostMapping("/create")
    public Result<AdCreativeResponse> createCreative(@RequestBody AdCreativeRequest request) throws AdException {
        log.info("ad-sponsor:createCreative ->{}", JSON.toJSONString(request));
        AdCreativeResponse creative = creativeService.createAdCreative(request);
        return new Result<>(StatusCode.OK, StatusMes.CREATESUCESS,creative);
    }
}
