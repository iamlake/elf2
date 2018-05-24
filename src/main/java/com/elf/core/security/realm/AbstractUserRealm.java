package com.elf.core.security.realm;

import com.elf.sys.org.entity.User;
import com.elf.sys.org.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Title AbstractUserRealm
 * @Description
 * @Author icelake
 * @Date 2018/3/11 18:23
 */
public abstract class AbstractUserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AbstractUserRealm.class);

    /*@Autowired
    private UserMapper userMapper;*/

    @Autowired
    private UserService userService;

    //获取用户组的权限信息
    public abstract UserRolesAndPermissions doGetGroupAuthorizationInfo(User userInfo);

    //获取用户角色的权限信息
    public abstract UserRolesAndPermissions doGetRoleAuthorizationInfo(User userInfo);

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String currentLoginName = (String) principals.getPrimaryPrincipal();
        Set<String> userRoles = new HashSet<>();
        Set<String> userPermissions = new HashSet<>();
        //从数据库中获取当前登录用户的详细信息
        User userInfo = userService.getUserByAccount(currentLoginName);
        if (null != userInfo) {
            //UserRolesAndPermissions groupContainer = doGetGroupAuthorizationInfo(userInfo);
            UserRolesAndPermissions roleContainer = doGetRoleAuthorizationInfo(userInfo);
            //userRoles.addAll(groupContainer.getUserRoles());
            userRoles.addAll(roleContainer.getUserRoles());
            //userPermissions.addAll(groupContainer.getUserPermissions());
            userPermissions.addAll(roleContainer.getUserPermissions());
        } else {
            throw new AuthorizationException();
        }
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(userRoles);
        userPermissions.add("org:unit:getDimensionList");
        authorizationInfo.addStringPermissions(userPermissions);
        logger.info("###【获取角色成功】[SessionId] => {}", SecurityUtils.getSubject().getSession().getId());
        return authorizationInfo;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        final User user = userService.getUserByAccount(String.valueOf(authenticationToken.getPrincipal()));
        if (user == null) {
            //找不到账号
            throw new UnknownAccountException();
        }
        if (Boolean.TRUE.equals(user.getIsLocked())) {
            //账号已锁定
            throw new LockedAccountException();
        }
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getAccount());
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名
                user.getAccount(),
                // 密码
                user.getPassword(),
                // salt=username+salt
                //ByteSource.Util.bytes(user.getCredentialsSalt()),
                // realm name
                getName()
        );
        return authenticationInfo;
        /**
         //UsernamePasswordToken对象用来存放提交的登录信息
         UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
         //查出是否有此用户
         User user = userMapper.selectByAccount(token.getUsername());
         if (user != null) {
         // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
         return new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), getName());
         }
         return null;
         */
    }

    @Setter
    @Getter
    @ToString
    protected class UserRolesAndPermissions {
        Set<String> userRoles;
        Set<String> userPermissions;

        UserRolesAndPermissions(Set<String> userRoles, Set<String> userPermissions) {
            this.userRoles = userRoles;
            this.userPermissions = userPermissions;
        }
    }
}
