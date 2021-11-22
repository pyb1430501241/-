package com.pdsu.banmeng.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.entity.UserInformation;
import com.pdsu.banmeng.entity.UserRole;
import com.pdsu.banmeng.enums.RoleEnum;
import com.pdsu.banmeng.service.IUserInformationService;
import com.pdsu.banmeng.service.IUserRoleService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author 半梦
 * @create 2021-02-20 20:35
 */
@Log4j2
@Component
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IUserInformationService userInformationService;

    @Autowired
    private IUserRoleService userRoleService;

    /**
     * MD5 加密算法
     */
    @Override
    protected void onInit() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        this.setCredentialsMatcher(hashedCredentialsMatcher);
        super.onInit();
    }

    /**
     * 登录认证
     * 这里的 UserName 实际为
     * @see UserInformation#getUid()
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String uid = upToken.getUsername();

        // 获取登录账号的详细信息
        UserInformation userInformation = getUserInformation(Integer.parseInt(uid));

        // 查看其账号状态
        determineAccountStatus(userInformation);

        CurrentUser currentUser = modelMapper.map(userInformation, CurrentUser.class);

        // 权限分配
        authorizationInfo(currentUser);

        // 完善用户信息
        perfect(currentUser);

        Object credentials = currentUser.getPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(uid);

        return new SimpleAuthenticationInfo(currentUser, credentials, credentialsSalt, realmName);
    }

    /**
     * 完善用户信息
     * @param user 用户
     */
    private void perfect(CurrentUser user) {

    }

    @NonNull
    private UserInformation getUserInformation(@NonNull Integer uid) {
        QueryWrapper<UserInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(UserInformation.builder().uid(uid).build());
        try {
            return userInformationService.getOne(queryWrapper);
        } catch (Exception e) {
            log.warn("登录查询出现异常, " + e);
            throw new UserAbnormalException(e.getMessage());
        }
    }

    /**
     * 如果没有对应用户, 直接抛出登录异常
     */
    private void determineAccountStatus(UserInformation account) {
        if(Objects.isNull(account)) {
            throw new UserAbnormalException("没有找到对应的用户");
        }
    }

    /**
     * 负责权限分配
     */
    public void authorizationInfo(CurrentUser currentUser) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();

        queryWrapper.setEntity(UserRole.builder().uid(currentUser.getUid()).build());

        currentUser.setRoleEnum(RoleEnum.matchers(userRoleService.getOne(queryWrapper).getRoleId()));

    }




    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}
