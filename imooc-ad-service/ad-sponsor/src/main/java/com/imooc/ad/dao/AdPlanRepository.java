package com.imooc.ad.dao;

import com.imooc.ad.entity.AdPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author spin
 * @date 2021/4/1613:15
 * @description: TODO
 */
public interface AdPlanRepository extends JpaRepository<AdPlan,Long> {
    
     /**
 　　* @description: TODO 根据ID和用户ID查询推广计划
 　　* @author yusp
 　　* @date 2021/4/16 13:16
 　　*/
    AdPlan findByIdAndUserId(Long id,Long userId);

     /**
 　　* @description: TODO 根据ID和用户ID查询推广计划组
 　　* @author yusp
 　　* @date 2021/4/16 13:22
 　　*/
    List<AdPlan> findAllByIdAndUserId(List<Long> ids, Long userId);

     /**
 　　* @description: TODO 根据用户ID和推广计划名查询推广计划
 　　* @author yusp
 　　* @date 2021/4/16 13:24
 　　*/
    AdPlan findByUserIdAndPlanName(Long userId, String planName);

     /**
 　　* @description: TODO 根据推广计划状态查询推广计划组
 　　* @author yusp
 　　* @date 2021/4/16 13:25
 　　*/
    List<AdPlan> findAllByPlanStatus(Integer status);
}
