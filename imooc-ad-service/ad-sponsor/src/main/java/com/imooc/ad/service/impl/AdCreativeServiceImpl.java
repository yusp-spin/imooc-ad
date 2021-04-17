package com.imooc.ad.service.impl;

import com.imooc.ad.dao.AdCreativeRepository;
import com.imooc.ad.entity.AdCreative;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdCreativeRequest;
import com.imooc.ad.response.AdCreativeResponse;
import com.imooc.ad.service.IAdCreativeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author spin
 * @date 2021/4/1620:05
 * @description: TODO
 */
@Slf4j
@Service
public class AdCreativeServiceImpl implements IAdCreativeService {

    @Autowired
    private AdCreativeRepository adCreativeRepository;

    /**
    * @description: TODO 创建新创意
    * @author yusp
    * @date 2021/4/16 20:16
    */
    @Override
    public AdCreativeResponse createAdCreative(AdCreativeRequest request) throws AdException {
        AdCreative adCreative = adCreativeRepository.save(request.convertToEntity());
        return new AdCreativeResponse(adCreative.getId(),adCreative.getName());
    }
}
