package com.mx.framework.mapper;

import com.mx.framework.base.mapper.MyMapper;
import com.mx.framework.business.UserRolePer;
import com.mx.framework.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
}
