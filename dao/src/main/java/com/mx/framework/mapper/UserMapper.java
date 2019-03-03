package com.mx.framework.mapper;

import com.mx.framework.base.mapper.MyMapper;
import com.mx.framework.po.User;
import com.mx.framework.po.UserPermission;
import com.mx.framework.po.UserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author : ShangGuanMingPeng
 * Description : 
 * Date :Create in
 * Modified By :
 */
@Mapper
@Repository
public interface UserMapper extends MyMapper<User> {

    @Select("SELECT * FROM permission p JOIN permission_role pr on p.pid = pr.pid WHERE pr.rid = #{rid}")
    List<UserPermission> queryByRoleId(Long rid);

    @Select("SELECT * FROM role r JOIN user_role ur on r.rid = ur.rid WHERE ur.uid = #{userId}")
    @Results({
            @Result(column = "rid",property = "rid"),
            @Result(property = "permissions",javaType = Set.class,column = "rid",
            many = @Many(select = "com.mx.framework.mapper.UserMapper.queryByRoleId"))
    })
    List<UserRole> queryRoleByUserId(int userId);

    @Select("SELECT * FROM `user`")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(property = "userRoles",javaType = Set.class,column = "id",
            many = @Many(select = "com.mx.framework.mapper.UserMapper.queryRoleByUserId"))
    })
    User findByUserName(String username);
}
