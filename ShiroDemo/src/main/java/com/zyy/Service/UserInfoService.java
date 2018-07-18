package com.zyy.Service;

import com.zyy.entity.UserInfo;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:06 2018/6/7
 */
public interface UserInfoService {

    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);

}
