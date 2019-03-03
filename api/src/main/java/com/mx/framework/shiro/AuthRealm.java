package com.mx.framework.shiro;

import com.mx.framework.mapper.UserMapper;
import com.mx.framework.po.User;
import com.mx.framework.po.UserPermission;
import com.mx.framework.po.UserRole;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author : ShangGuanMingPeng
 * Description : Shiro自定义Realm
 * Date :Create in 2019/2/15 22:50
 * Modified By :
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions = new ArrayList<>();
        List<String> roleList = new ArrayList<>();
        Set<UserRole> roles = user.getUserRoles();
        if(!CollectionUtils.isEmpty(roles)){
            roles.forEach( userRole -> {
                roleList.add(userRole.getName());
                Set<UserPermission> permissionsSet = userRole.getPermissions();
                if(!CollectionUtils.isEmpty(permissionsSet)){
                    permissionsSet.forEach(userPermission -> {
                        permissions.add(userPermission.getName());
                    });
                }
            });
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        info.addRoles(roleList);
        return info;
    }

    /**
     * 认证登录功能
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userMapper.findByUserName(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPwd(), this.getClass().getName());
        return simpleAuthenticationInfo;
    }
}
