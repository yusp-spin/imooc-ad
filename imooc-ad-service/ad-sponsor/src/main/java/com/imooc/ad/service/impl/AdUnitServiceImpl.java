package com.imooc.ad.service.impl;

import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.*;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUnit;
import com.imooc.ad.entity.unit_condition.AdUnitDistrict;
import com.imooc.ad.entity.unit_condition.AdUnitIt;
import com.imooc.ad.entity.unit_condition.AdUnitKeyword;
import com.imooc.ad.entity.unit_condition.CreativeUnit;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.*;
import com.imooc.ad.response.*;
import com.imooc.ad.service.IAdUnitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author spin
 * @date 2021/4/1616:04
 * @description: TODO
 */
@Slf4j
@Service
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

    @Autowired
    private CreativeUnitRepository creativeUnitRepository;



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


    @Override
    public CreativeUnitResponse createAdCreatveUnit(CreativeUnitRequest request) throws AdException {
        List<CreativeUnitRequest.CreativeUnitItem> unitItems = request.getUnitItems();
        List<Long> unitIds  = unitItems.stream().map(CreativeUnitRequest.CreativeUnitItem::getUnitId).collect(Collectors.toList());
        List<Long> creativeIds = unitItems.stream().map(CreativeUnitRequest.CreativeUnitItem::getCreativeId).collect(Collectors.toList());
        if(isRelatedUnitExist(unitIds) || isRelatedCreativeUnit(creativeIds)) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        List<CreativeUnit> creativeUnits = new ArrayList<>();
        unitItems.stream()
                .forEach(u -> {
                    creativeUnits.add(new CreativeUnit(u.getCreativeId(),u.getUnitId()));
                });
        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits).stream()
                .map(CreativeUnit::getId).collect(Collectors.toList());
        return new CreativeUnitResponse(ids);
    }


    /**
    * @description: TODO 对输入的unitID进行校验
    * @author yusp
    * @date 2021/4/16 20:40
    */
    private boolean isRelatedUnitExist(List<Long> unitIds) {
        if(CollectionUtils.isEmpty(unitIds)) {
            return false;
        }
        //查询ids是否都存在
        return adUnitRepository.findAllById(unitIds).size() == new HashSet<>(unitIds).size();
    }

    /**
     * @description: TODO 对输入的creativeID进行校验
     * @author yusp
     * @date 2021/4/16 20:40
     */
    private boolean isRelatedCreativeUnit(List<Long> creativeIdS) {
        if(CollectionUtils.isEmpty(creativeIdS)) {
            return false;
        }
        //查询ids是否都存在
        return creativeUnitRepository.findAllById(creativeIdS).size() == new HashSet<>(creativeIdS).size();
    }
}
