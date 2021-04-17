package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdCreateUserRequest;
import com.imooc.ad.response.AdCreateUserResponse;

/**
 * @author spin
 * @date 2021/4/1613:56
 * @description: TODO
 */
public interface IUserService {

    /**
    * @description: TODO 创建 用户
    * @author yusp
    * @date 2021/4/16 14:00
    */
    AdCreateUserResponse createUser(AdCreateUserRequest request) throws AdException;
}
