package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdUnitDistrictRequest;
import com.imooc.ad.request.AdUnitItRequest;
import com.imooc.ad.request.AdUnitKeywordRequest;
import com.imooc.ad.request.AdUnitRequest;
import com.imooc.ad.response.AdUnitDistrictResponse;
import com.imooc.ad.response.AdUnitItResponse;
import com.imooc.ad.response.AdUnitKeywordResponse;
import com.imooc.ad.response.AdUnitResponse;

/**
 * @author spin
 * @date 2021/4/1616:03
 * @description: TODO
 */
public interface IAdUnitService {
    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException;

}
