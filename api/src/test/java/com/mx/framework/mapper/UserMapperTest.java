package com.mx.framework.mapper;

import com.mx.framework.po.User;
import com.mx.framework.po.UserRole;
import com.mx.framework.vo.UserRoleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    public void queryByName(){
        User user = userMapper.findByUserName("admin");
        assert user!=null;
    }

}