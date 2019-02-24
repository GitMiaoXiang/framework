package com.mx.framework.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : ShangGuanMingPeng
 * Description : 用户角色表
 * Date :Create in 2019/2/15 22:42
 * Modified By :
 */
@Table(name = "role")
@Data
public class UserRole {

    @Id
    private Integer rid;

    private String name;

    private Set<UserPermission> permissions = new HashSet<>();

    private Set<User> users = new HashSet<>();
}
