package com.imooc.ad.service;

import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.request.AdPlanGetRequest;
import com.imooc.ad.request.AdPlanRequest;
import com.imooc.ad.response.AdPlanResponse;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1614:48
 * @description: TODO
 */
public interface IAdPlanService {

    /**
    * @description: TODO 创建推广计划
    * @author yusp
    * @date 2021/4/16 14:57
    */
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;
    
    /**
    * @description: TODO 获取推广计划
    * @author yusp
    * @date 2021/4/16 14:58
    */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;
    
    /**
    * @description: TODO 更新推广计划
    * @author yusp
    * @date 2021/4/16 15:03
    */
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;

    /**
    * @description: TODO 删除推广计划
    * @author yusp
    * @date 2021/4/16 15:03
    */
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
