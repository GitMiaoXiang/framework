package com.mx.framework.controller;

import com.mx.framework.cosntenum.ResponseEnum;
import com.mx.framework.entity.cto.ResultData;
import com.mx.framework.entity.model.User;
import com.mx.framework.service.IUserService;
import com.mx.framework.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description : 用户Controller
 * Date :Create in 2018/12/6 22:26
 * Modified By :
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user")
    public ResultData<List<User>> getUserInfo(){
        List<User> users = userService.queryAll();
        return ResultUtil.successResult(users,ResponseEnum.SUCCESS);
    }
}
