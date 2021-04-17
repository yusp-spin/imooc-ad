package com.imooc.ad.service.impl;

import com.imooc.ad.constant.CommonStatus;
import com.imooc.ad.constant.Constants;
import com.imooc.ad.dao.AdPlanRepository;
import com.imooc.ad.dao.AdUserRepository;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUser;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdPlanGetRequest;
import com.imooc.ad.request.AdPlanRequest;
import com.imooc.ad.response.AdPlanResponse;
import com.imooc.ad.service.IAdPlanService;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author spin
 * @date 2021/4/1615:04
 * @description: TODO
 */
@Slf4j
@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Autowired
    private AdPlanRepository adPlanRepository;

    /**
    * @description: TODO 创建推广计划
    * @author yusp
    * @date 2021/4/16 15:28 
    */
    @Override
    @Transactional
    public AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException {
        if(!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        //确保userId存在
        Optional<AdUser> adUser = adUserRepository.findById(request.getUserId());
        if(!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        AdPlan oldPlan = adPlanRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if(oldPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }
        AdPlan adPlan = new AdPlan(){{
           setUserId(request.getUserId());
           setPlanName(request.getPlanName());
           setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
           setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }};
        adPlanRepository.save(adPlan);
        return new AdPlanResponse(adPlan.getId(),adPlan.getPlanName());
    }

    /**
    * @description: TODO 获取推广计划
    * @author yusp
    * @date 2021/4/16 15:28
    */
    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {
        if(!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        return adPlanRepository.findAllByIdAndUserId(request.getIds(),request.getUserId());
    }

    /**
    * @description: TODO 更新推广计划
    * @author yusp
    * @date 2021/4/16 15:30
    */
    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {
        if(! request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = adPlanRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if(plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        String planName = request.getPlanName();
        String startDate = request.getStartDate();
        String endDate = request.getEndDate();
        if(planName != null) {
            plan.setPlanName(planName);
        }
        if(startDate != null) {
            plan.setStartDate(CommonUtils.parseStringDate(startDate));
        }
        if(endDate != null) {
            plan.setEndDate(CommonUtils.parseStringDate(endDate));
        }
        plan.setUpdateTime(new Date());
        adPlanRepository.save(plan);
        return new AdPlanResponse(plan.getId(),plan.getPlanName());
    }

    /**
    * @description: TODO 删除推广计划：通过传入的参数获取推广计划，将其状态设为无效
    * @author yusp
    * @date 2021/4/16 15:45
    */
    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if(!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }
        AdPlan plan = adPlanRepository.findByIdAndUserId(request.getId(),request.getUserId());
        if(plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        adPlanRepository.save(plan);
    }
}
