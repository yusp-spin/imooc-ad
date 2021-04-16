package com.imooc.ad.dao;

import com.imooc.ad.entity.AdUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1613:28
 * @description: TODO
 */
public interface AdUnitRepository extends JpaRepository<AdUnit, Long> {
    
    /**
    * @description: TODO 根据推广计划 ID 和推广单元名称查询推广单元
    * @author yusp
    * @date 2021/4/16 13:29
    */
    AdUnit findByPlanIdAndUnitName(Long planId, String userName);
    
    /**
    * @description: TODO 根据推广单元状态查询推广单元组
    * @author yusp
    * @date 2021/4/16 13:30
    */
    List<AdUnit> findAllByUnitStatus(Integer unitStatus);
}
