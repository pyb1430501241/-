package com.pdsu.banmeng.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.entity.UserInformation;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    public LoginRealm( ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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
     * NewsAccount#getAccount()
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String account = upToken.getUsername();

        // 获取登录账号的详细信息
        UserInformation newsAccount = getNewsAccount(account);

        // 查看其账号状态
        determineAccountStatus(newsAccount);

        // 因需要修改该对象信息, 且因为有缓存,
        // 故对其进行复制使用
        CurrentUser currentUser = modelMapper.map(newsAccount, CurrentUser.class);

        Object credentials = currentUser.getPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(account);

        return new SimpleAuthenticationInfo(currentUser, credentials, credentialsSalt, realmName);
    }

    @NonNull
    private UserInformation getNewsAccount(@NonNull String account) {
        QueryWrapper<UserInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", account);
        try {
            return new UserInformation();
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
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        CurrentUser account = (CurrentUser) principals.getPrimaryPrincipal();

        QueryWrapper<UserInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", account.getId());

//        NewsAccountRole role = newsAccountRoleService.getOne(queryWrapper);
//
//        // 设置权限
//        account.setRole(role);

        return null;
    }

}
