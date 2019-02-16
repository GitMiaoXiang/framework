package com.mx.framework.vo;

import com.mx.framework.po.User;
import com.mx.framework.po.UserRole;
import lombok.Data;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/2/16 11:29
 * Modified By :
 */
@Data
public class UserRoleVo extends User {

    private List<UserRole> userRoles;

}
