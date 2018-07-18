package com.zyy.Dao;

import com.zyy.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:09 2018/6/7
 */
@Repository
public interface UserInfoDao extends JpaRepository<UserInfo,Long> {

    /**通过username查找用户信息;*/
    public UserInfo findByUserName(String userName);

}
