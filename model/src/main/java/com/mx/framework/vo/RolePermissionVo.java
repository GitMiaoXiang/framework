package com.mx.framework.vo;

import com.mx.framework.po.UserPermission;
import com.mx.framework.po.UserRole;
import lombok.Data;

import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/2/16 11:31
 * Modified By :
 */
@Data
public class RolePermissionVo extends UserRole {

    private List<UserPermission> userPermissions;

}
