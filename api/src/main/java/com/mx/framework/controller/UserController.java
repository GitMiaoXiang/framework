package com.mx.framework.controller;

import com.mx.framework.cosntenum.ResponseEnum;
import com.mx.framework.dto.ResultData;
import com.mx.framework.po.User;
import com.mx.framework.service.IUserService;
import com.mx.framework.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @PostMapping("/loginPage")
    public ResultData<String> loginPage(){
        return ResultUtil.successResult("登录页面",ResponseEnum.SUCCESS);
    }

    @GetMapping("/index")
    public ResultData<String> index(){
        return ResultUtil.successResult("首页",ResponseEnum.SUCCESS);
    }

    @PostMapping("/login")
    public ResultData login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletRequest request){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            HttpSession session = request.getSession();
            session.setAttribute("user:"+username,user);
            return ResultUtil.successResult(ResponseEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.errorResult(ResponseEnum.FAILED);
        }
    }
}
