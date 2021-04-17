package com.imooc.ad.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.common.StatusCode;
import com.imooc.ad.common.StatusMes;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdCreateUserRequest;
import com.imooc.ad.response.AdCreateUserResponse;
import com.imooc.ad.service.IUserService;
import com.imooc.ad.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spin
 * @date 2021/4/1620:55
 * @description: TODO 用户
 */

@RestController
@Slf4j
@RequestMapping("/user")
@Api(value = "UserOPController", tags = "用户接口功能",description = "用户相关接口介绍")
public class UserOPController {

    @Autowired
    private IUserService userService;


    @PostMapping("/create")
    @ApiOperation(value = "新增用户", notes = "create")
    @ApiImplicitParam(paramType="create", name = "request", value = "用户新增请求", required = true, dataType = "com.imooc.ad.request.AdCreateUserRequest")
    public Result<AdCreateUserResponse> createUser(@RequestBody AdCreateUserRequest request) throws AdException {
        log.info("ad-sponsor: createUser -> {}", JSON.toJSONString(request));
        AdCreateUserResponse user = userService.createUser(request);
        return new Result<>(StatusCode.OK, StatusMes.CREATESUCESS,user);
    }
}
