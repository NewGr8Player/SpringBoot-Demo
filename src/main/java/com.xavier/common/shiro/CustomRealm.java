package com.xavier.common.shiro;

import com.xavier.common.jwt.JWTGen;
import com.xavier.common.jwt.JWTToken;
import com.xavier.dao.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Shiro鉴权
 *
 * @author NewGr8Player
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    /**
     * 此处使用 {@code @Lazy} 注解原因
     * 不适用会影响Redis缓存的正常使用
     * https://docs.spring.io/spring-framework/docs/4.0.0.RELEASE/javadoc-api/org/springframework/context/annotation/Lazy.html
     */
    @Lazy
    @Autowired
    private UserMapper userMapper;
    @Lazy
    @Autowired
    private JWTGen jwtGen;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = jwtGen.getUsername(token);
        if (username == null || !jwtGen.verify(token, username)) {
            throw new AuthenticationException("token认证失败！");
        }
        String password = userMapper.getPassword(username);
        if (password == null) {
            throw new AuthenticationException("该用户不存在！");
        }
        return new SimpleAuthenticationInfo(token, token, "MyRealm");
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法
     * 例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /**
         * TODO 权限设置在这里配置获取
         */
        String username = jwtGen.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        /* 获得该用户角色 */
        String role = userMapper.getRole(username);
        /* 每个角色拥有默认的权限 */
        String rolePermission = userMapper.getRolePermission(username);
        /* 每个用户可以设置新的权限 */
        String permission = userMapper.getPermission(username);
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        /* 需要将 role, permission 封装到 Set 作为 info.setRoles(), info.setStringPermissions() 的参数 */

        roleSet.add(role);/* 角色 */

        permissionSet.add(rolePermission); /* 权限01 */
        permissionSet.add(permission);/* 权限02 */
        /* 设置该用户拥有的角色和权限 */
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }
}
