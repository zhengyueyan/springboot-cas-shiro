package com.zyy.Service.impl;

import com.zyy.Dao.UserInfoDao;
import com.zyy.Service.UserInfoService;
import com.zyy.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: 郑玥延
 * @Date: 13:08 2018/6/7
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");

        return userInfoDao.findByUserName(username);
    }
}
