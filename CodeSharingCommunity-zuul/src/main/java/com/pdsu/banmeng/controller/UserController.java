package com.pdsu.banmeng.controller;

import com.pdsu.banmeng.business.Tourist;
import com.pdsu.banmeng.business.User;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.dto.SimpleResponse;
import com.pdsu.banmeng.utils.ShiroUtils;
import com.pdsu.banmeng.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 18:43
 */
@Slf4j
@RestController
@Api(tags = "用户信息相关")
public class UserController {

    private static final Integer REMEMBER_ME = 1;

    @PostMapping("/login")
    @Tourist
    @ApiOperation(value = "登录")
    public SimpleResponse<CurrentUser> login(@RequestBody LoginVo loginVo) {
        Subject subject = SecurityUtils.getSubject();

        // 如果已登录, 则退出
        if(Objects.nonNull(ShiroUtils.getCurrentUser())) {
            subject.logout();
        }

        // 生成登录密匙, 并判断是否记住
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUid(), loginVo.getPassword());

        if(REMEMBER_ME.equals(loginVo.getRememberMe())) {
            token.setRememberMe(true);
        }

        log.debug("开始登录");

        subject.login(token);

        CurrentUser currentUser = ShiroUtils.getCurrentUser();

        log.info("登录成功, sessionId: " + subject.getSession().getId());

        return new SimpleResponse<>(currentUser);
    }

    @GetMapping("/nav")
    @User
    @ApiOperation(value = "获取当前用户信息")
    public SimpleResponse<CurrentUser> currentUser() {
        return new SimpleResponse<>(ShiroUtils.getCurrentUser());
    }

}
