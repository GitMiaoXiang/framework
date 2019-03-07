package com.mx.framework.shiro;

import com.mx.framework.po.UserPermission;
import com.mx.framework.service.IPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in 2019/3/2 23:39
 * Modified By :
 */
@Component
public class ShiroUtils {

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private IPermissionService permissionService;
    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        return createShiroPerMap(permissionService,shiroFilterFactoryBean);
    }

    /**
     * 生成权数据
     * @param permissionService
     * @param shiroFilterFactoryBean
     * @return
     */
    public static Map<String, String> createShiroPerMap(IPermissionService permissionService ,ShiroFilterFactoryBean shiroFilterFactoryBean){
        // 权限控制map.从数据库获取
        Map<String, String> filterMap = new LinkedHashMap<>();
        //这些代码仅供测试使用
        filterMap.put("/index","authc"); //必须登录 在DefaultFilter中
        filterMap.put("/login","anon"); //不用校验
        filterMap.put("/loginUser","anon");
        filterMap.put("/admin","roles[admin]"); //只有在admin角色下才可以访问
        filterMap.put("/edit","perms[edit]"); //在PermissionsAuthorizationFilter中

        //正式环境配置
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterMap.put("/logout", "logout");
        filterMap.put("/css/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/img/**","anon");
        filterMap.put("/font-awesome/**","anon");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //自定义加载权限资源关系
        List<UserPermission> resourcesList = permissionService.queryAll();
        for(UserPermission permission:resourcesList){
            String url = permission.getUrl();
            if (StringUtils.isNotEmpty(url)) {
                String permissionFilter = "perms[" + url+ "]";
                filterMap.put(url,permissionFilter);
            }
        }
        filterMap.put("/**","user"); //需要登录才能访问 在UserFilter
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return filterMap;
    }

    /**
     * 重新加载权限
     */
    public void updatePermission() {

        synchronized (shiroFilterFactoryBean) {

            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean
                        .getObject();
            } catch (Exception e) {
                throw new RuntimeException(
                        "get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
                    .getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver
                    .getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean
                    .setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean
                    .getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim()
                        .replace(" ", "");
                manager.createChain(url, chainDefinition);
            }

            System.out.println("更新权限成功！！");
        }
    }
}
