package com.pdsu.banmeng.config;

import com.pdsu.banmeng.dto.SimpleResponse;
import com.pdsu.banmeng.shiro.UserAbnormalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 19:26
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlingSupport {

    /**
     * 处理 AuthenticationException 异常
     */
    @ExceptionHandler(AuthenticationException.class)
    public SimpleResponse<String> processAuthenticationException(AuthenticationException e) {

        if(e instanceof IncorrectCredentialsException) {
            log.info("用户登录时出现未知错误, 原因: 账号或密码错误");
            return new SimpleResponse<>("账号或密码错误");
        } else if (e instanceof UnknownAccountException) {
            log.info("用户登录时出现未知错误, 原因: 账号不存在");
            return new SimpleResponse<>("用户不存在");
        } else if (e instanceof UserAbnormalException) {
            log.info("用户登录时出现未知错误, 原因: " + e.getMessage());
            return new SimpleResponse<>(e.getMessage());
        }

        log.info("用户登录时出现未知错误, 原因: " + e.getMessage());
        SecurityUtils.getSubject().logout();
        return new SimpleResponse<>("网络异常");
    }

}
