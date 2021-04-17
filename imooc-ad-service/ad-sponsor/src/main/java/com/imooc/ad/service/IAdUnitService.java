package com.imooc.ad.service;

import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.*;
import com.imooc.ad.response.*;

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

    CreativeUnitResponse createAdCreatveUnit(CreativeUnitRequest request) throws AdException;
}
