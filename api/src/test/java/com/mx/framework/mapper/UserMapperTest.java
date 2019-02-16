package com.mx.framework.mapper;

import com.mx.framework.ApiApplication;
import com.mx.framework.po.UserRole;
import com.mx.framework.vo.UserRoleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/2/16 11:48
 * Modified By :
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void queryRoleByUserId() {
        List<UserRole> userRoles = userMapper.queryRoleByUserId(1);
        System.out.println(userRoles.toString());
        assert !userRoles.isEmpty();
    }

    @Test
    public void queryUserRole() {
        UserRoleVo userRoleVo = userMapper.queryUserRole(1);
        assert userRoleVo!=null;
    }
}