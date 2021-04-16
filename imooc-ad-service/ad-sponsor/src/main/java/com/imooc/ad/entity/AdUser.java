package com.imooc.ad.entity;

import com.imooc.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author spin
 * @date 2021/4/1610:10
 * @description: TODO 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ad_user")
public class AdUser {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "id", nullable = false) //对应id字段，且不能为空，这些在服务层就要控制好
    private Long id;

    @Basic //基本属性
    @Column(name = "username",nullable = false)
    private String userName;

    @Basic //基本属性
    @Column(name = "token",nullable = false)
    private String token;

    @Basic //基本属性
    @Column(name = "user_status",nullable = false)
    private Integer userStatus;

    //基本属性
    @Basic
    //对应的字段为token，并且不能为空
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    //基本属性
    @Basic
    //对应的字段为token，并且不能为空
    @Column(name = "update_time",nullable = false)
    private Date updateTime;

    public AdUser(String userName, String token) {
        this.userName = userName;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
