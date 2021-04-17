package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdCreativeRequest;
import com.imooc.ad.response.AdCreativeResponse;

/**
 * @author spin
 * @date 2021/4/1620:03
 * @description: TODO
 */
public interface IAdCreativeService {
    AdCreativeResponse createAdCreative(AdCreativeRequest request) throws AdException;
}
