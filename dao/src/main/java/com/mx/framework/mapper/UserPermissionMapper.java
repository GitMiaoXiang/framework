package com.mx.framework.mapper;

import com.mx.framework.base.mapper.MyMapper;
import com.mx.framework.po.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/3/7 17:18
 * Modified By :
 */
@Mapper
@Repository
public interface UserPermissionMapper extends MyMapper<UserPermission> {
}
