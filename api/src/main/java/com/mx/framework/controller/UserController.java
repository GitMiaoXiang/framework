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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    @ResponseBody
    public ResultData<List<User>> getUserInfo(){
        List<User> users = userService.queryAll();
        return ResultUtil.successResult(users,ResponseEnum.SUCCESS);
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "loginPage";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin success";
    }

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @GetMapping("/edit")
    public String edit(){
        return "edit";
    }



    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject!=null){
            subject.logout();
        }
        return "login";
    }

    @GetMapping("/login")
    public ResultData login(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user:"+username,user);
            return ResultUtil.successResult(ResponseEnum.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.errorResult(ResponseEnum.FAILED);
        }
    }
}
