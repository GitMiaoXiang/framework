package com.mx.framework.business;

import lombok.Data;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/2/15 23:50
 * Modified By :
 */
@Data
public class UserRolePer {

    private Integer userId;

    private String userName;

    private String password;

    private String userRoleName;

    private String permissionName;
}
