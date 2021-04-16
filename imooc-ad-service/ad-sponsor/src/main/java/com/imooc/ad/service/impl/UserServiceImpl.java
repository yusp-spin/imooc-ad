package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.CreateUserRequest;
import com.imooc.ad.response.CreateUserResponse;
import com.imooc.ad.service.IUserService;
import com.imooc.ad.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author spin
 * @date 2021/4/1614:02
 * @description: TODO
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {
        if(!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdUser oldUser = adUserRepository.findByUsername(request.getUserName());
        if(oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }
        AdUser newUser = adUserRepository.save(new AdUser(
                request.getUserName(),
        CommonUtils.md5(request.getUserName())
        ));
        CreateUserResponse response = new CreateUserResponse(){{
            setUserId(newUser.getId());
            setUserName(newUser.getUserName());
            setToken(newUser.getToken());
            setCreateTime(newUser.getCreateTime());
            setUpdateTime(newUser.getUpdateTime());
        }};
       return response;
    }


}
