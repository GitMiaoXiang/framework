package com.mx.framework.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @author : ShangGuanMingPeng
 * Description : 自定义密码校验
 * Date :Create in 2019/2/24 23:14
 * Modified By :
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String pwd = new String(usernamePasswordToken.getPassword());
        String dbPwd = (String) info.getCredentials();
        return this.equals(pwd,dbPwd);
    }
}
