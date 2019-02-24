package com.mx.framework.mapper;

import com.mx.framework.base.mapper.MyMapper;
import com.mx.framework.business.UserRolePer;
import com.mx.framework.po.User;
import com.mx.framework.po.UserRole;
import com.mx.framework.vo.UserRoleVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description : 
 * Date :Create in
 * Modified By :
 */
@Mapper
@Repository
public interface UserMapper extends MyMapper<User> {

    @Select("SELECT user.id as userId, user.`name` as userName,user.pwd as password,user_role.role_name as userRoleName,user_permission.permission as permissionName from `user` \n" +
            "LEFT JOIN user_role ON `user`.id = user_role.user_id" +
            "LEFT JOIN user_permission ON user_role.id = user_permission.role_id" +
            "where `user`.name=#{name}")
    UserRolePer queryRolePer(@Param("name") String name);

    @Select("SELECT u.id,u.`name` from `user` as u where u.id = #{userId}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(property = "userRoles",javaType = List.class,column = "id",
            many = @Many(select = "com.mx.framework.mapper.UserMapper.queryRoleByUserId"))
    })
    UserRoleVo queryUserRole(@Param("userId") Integer userId);

    @Select("SELECT * from user_rule where user_id = #{userId}")
    List<UserRole> queryRoleByUserId(int userId);


    User findByUserName(String username);
}
