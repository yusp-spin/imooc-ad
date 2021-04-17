package com.imooc.ad.dao;

import com.imooc.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author spin
 * @date 2021/4/1613:10
 * @description: TODO
 */
public interface AdUserRepository extends JpaRepository<AdUser,Long> {

     /**
 　　* @description: TODO 根据用户名查找用户记录
 　　* @author yusp
 　　* @date 2021/4/16 13:13
 　　*/
    AdUser findByUserName(String username);
}
