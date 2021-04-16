package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.*;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUnit;
import com.imooc.ad.entity.unit_condition.AdUnitDistrict;
import com.imooc.ad.entity.unit_condition.AdUnitIt;
import com.imooc.ad.entity.unit_condition.AdUnitKeyword;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdUnitDistrictRequest;
import com.imooc.ad.request.AdUnitItRequest;
import com.imooc.ad.request.AdUnitKeywordRequest;
import com.imooc.ad.request.AdUnitRequest;
import com.imooc.ad.response.AdUnitDistrictResponse;
import com.imooc.ad.response.AdUnitItResponse;
import com.imooc.ad.response.AdUnitKeywordResponse;
import com.imooc.ad.response.AdUnitResponse;
import com.imooc.ad.service.IAdUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author spin
 * @date 2021/4/1616:04
 * @description: TODO
 */
public class AdUnitServiceImpl implements IAdUnitService {

    @Autowired
    private AdPlanRepository adPlanRepository;

    @Autowired
    private AdUnitRepository adUnitRepository;

    @Autowired
    private AdUnitKeyWordRepository adUnitKeyWordRepository;

    @Autowired
    private AdUnitItRepository adUnitItRepository;

    @Autowired
    private AdUnitDistrictRepository adUnitDistrictRepository;



    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {
        if(!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        Optional<AdPlan> adPlan = adPlanRepository.findById(request.getPlanId());
        if(!adPlan.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdUnit oldAdunit = adUnitRepository.findByPlanIdAndUnitName(request.getPlanId(), request.getUnitName());
        if(oldAdunit!= null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }
        AdUnit adUnit = new AdUnit(){{
           setPlanId(request.getPlanId());
           setUnitName(request.getUnitName());
           setPositionType(request.getPositionType());
           setBudget(request.getBudget());
        }};
        adUnitRepository.save(adUnit);

        return new AdUnitResponse(adUnit.getId(),adUnit.getUnitName());
    }

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request) throws AdException {
        List<AdUnitKeywordRequest.UnitKeyword> unitKeywordList = request.getUnitKeywords();
        List<Long> unitIds  = unitKeywordList
                .stream()
                .map(AdUnitKeywordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> ids = Collections.emptyList();
        List<AdUnitKeyword> unitKeywords = new ArrayList<>();
        if(!CollectionUtils.isEmpty(unitKeywordList)) {
            unitKeywordList.stream()
                    .forEach(i -> unitKeywords.add(new AdUnitKeyword(i.getUnitId(),i.getKeyword())));
            ids = adUnitKeyWordRepository.saveAll(unitKeywords)
                    .stream()
                    .map(AdUnitKeyword::getId)
                    .collect(Collectors.toList());
        }
        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {
        List<AdUnitItRequest.UnitIt> unitIts = request.getUnitIts();
        List<Long> unitItIds = unitIts
                .stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());
        if(!isRelatedUnitExist(unitItIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<AdUnitIt> adUnitIts = new ArrayList<>();
        List<Long> ids = Collections.emptyList();
        if(!CollectionUtils.isEmpty(unitIts)) {
            unitIts.stream()
                    .forEach(i -> {
                        adUnitIts.add(new AdUnitIt(i.getUnitId(), i.getItTag()));
                    });
            ids = adUnitItRepository.saveAll(adUnitIts)
                    .stream()
                    .map(AdUnitIt::getId)
                    .collect(Collectors.toList());
        }

        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request) throws AdException {
        List<AdUnitDistrictRequest.UnitDistrict> unitDistricts = request.getUnitDistricts();
        List<Long> unitIds  = unitDistricts.stream().map(AdUnitDistrictRequest.UnitDistrict::getUnitId).collect(Collectors.toList());
        if(!isRelatedUnitExist(unitIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<Long> ids = Collections.emptyList();
        List<AdUnitDistrict> adUnitDistricts = new ArrayList<>();
        if(CollectionUtils.isEmpty(unitDistricts)) {
            unitDistricts
                    .stream()
                    .forEach(d -> {
                        adUnitDistricts.add(new AdUnitDistrict(d.getUnitId(),d.getProvince(),d.getCity()));
                    });
            ids = adUnitDistrictRepository.saveAll(adUnitDistricts)
                    .stream()
                    .map(AdUnitDistrict::getId)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private boolean isRelatedUnitExist(List<Long> unitIds) {
        if(CollectionUtils.isEmpty(unitIds)) {
            return false;
        }
        //查询ids是否都存在
        return adUnitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }
}
