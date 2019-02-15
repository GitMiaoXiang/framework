package com.mx.framework.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author : ShangGuanMingPeng
 * Description : 用户角色表
 * Date :Create in 2019/2/15 22:42
 * Modified By :
 */
@Table(name = "user_role")
@Data
public class UserRole {

    @Id
    private Integer id;

    private String roleName;

    private String userId;
}
