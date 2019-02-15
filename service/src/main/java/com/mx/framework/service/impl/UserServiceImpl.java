package com.mx.framework.service.impl;

import com.mx.framework.service.IUserService;
import com.mx.framework.base.service.BaseService;
import com.mx.framework.po.User;
import com.mx.framework.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:上官名鹏
 * @Description:
 * @Date:Create in 2018/11/3 10:26
 * Modified By:
 */
@Service
public class UserServiceImpl extends BaseService<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

//    @Override
//    public Mapper<User> getMapper() {
//        return userMapper;
//    }
}
