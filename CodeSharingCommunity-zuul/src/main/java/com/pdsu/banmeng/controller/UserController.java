package com.pdsu.banmeng.controller;

import com.pdsu.banmeng.business.Tourist;
import com.pdsu.banmeng.business.User;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.dto.LoginDto;
import com.pdsu.banmeng.dto.SimpleResponse;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.ibo.ApplyAccountIbo;
import com.pdsu.banmeng.ibo.EmailSearchIbo;
import com.pdsu.banmeng.ibo.UserUpdateIbo;
import com.pdsu.banmeng.manager.IUserManager;
import com.pdsu.banmeng.manager.impl.UserManager;
import com.pdsu.banmeng.service.IEmailService;
import com.pdsu.banmeng.service.IUserInformationService;
import com.pdsu.banmeng.utils.*;
import com.pdsu.banmeng.vo.ApplyAccountVo;
import com.pdsu.banmeng.vo.ApplyCodeVo;
import com.pdsu.banmeng.vo.LoginVo;
import com.pdsu.banmeng.vo.UserUpdateVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-22 18:43
 */
@Slf4j
@Validated
@RestController
@Api(tags = "用户信息相关")
public class UserController {

    private static final Integer REMEMBER_ME = 1;

    private static final long CODE_EXIST_TIME = DateUtils.NEWS_TIME_MINUTE * 5;

    private static final String APPLY_CODE_PREFIX = "apply:code:";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IUserManager userManager;

    @Autowired
    private IUserInformationService userInformationService;

    @PostMapping("/login")
    @Tourist
    @ApiOperation(value = "登录")
    public SimpleResponse<LoginDto> login(@Valid @RequestBody LoginVo loginVo) {
        Subject subject = SecurityUtils.getSubject();

        // 如果已登录, 则退出
        if(Objects.nonNull(ShiroUtils.currentUser())) {
            subject.logout();
        }

        // 生成登录密匙, 并判断是否记住
        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUid(), loginVo.getPassword());

        token.setRememberMe(REMEMBER_ME.equals(loginVo.getRememberMe()));

        log.debug("开始登录");

        subject.login(token);

        CurrentUser currentUser = ShiroUtils.currentUser();

        Serializable sessionId = subject.getSession().getId();

        log.info("登录成功, sessionId: " + subject.getSession().getId());

        return new SimpleResponse<>(new LoginDto(currentUser, sessionId));
    }

    @GetMapping("/nav")
    @User
    @ApiOperation(value = "获取当前用户信息")
    public SimpleResponse<CurrentUser> currentUser() {
        return new SimpleResponse<>(ShiroUtils.currentUser());
    }

    @PostMapping("/logout")
    @User
    @ApiOperation(value = "退出登录")
    public SimpleResponse<Boolean> logout() {
        SecurityUtils.getSubject().logout();
        return new SimpleResponse<>(true);
    }

    @PostMapping("/applyCode")
    @Tourist
    @ApiOperation(value = "申请账号时, 发送邮箱验证码")
    public SimpleResponse<String> applySendEmail(@Valid @RequestBody ApplyCodeVo applyCodeVo) throws EmailException {
        Assert.isFalse(emailService.isExist(modelMapper.map(applyCodeVo, EmailSearchIbo.class)), StatusEnum.EMAIL_EXIST);

        return new SimpleResponse<>(sendEmail(APPLY_CODE_PREFIX, applyCodeVo.getEmail(), applyCodeVo.getName()));
    }

    /**
     * 申请账号
     * @return json字符串
     */
    @PostMapping(value = "/applyAccount")
    @Tourist
    @ApiOperation(value = "申请账号")
    public SimpleResponse<Boolean> applyAccountNumber(@Valid @RequestBody ApplyAccountVo applyAccount) {
        checkCode(APPLY_CODE_PREFIX + applyAccount.getKey(), applyAccount.getCode());

        applyAccount.setPassword(HashUtils.md5(applyAccount.getUid(), applyAccount.getPassword()));

        return new SimpleResponse<>(userManager.applyAccount(modelMapper.map(applyAccount, ApplyAccountIbo.class)));
    }

    @PostMapping(value = "/image")
    @User
    @ApiOperation(value = "更换头像")
    public SimpleResponse<Boolean> updateUserImage(MultipartFile image) {
        CurrentUser currentUser = ShiroUtils.currentUser();
        Boolean b = userManager.updateUserImage(image, currentUser);

        // 如果更换成功则刷新当前用户的信息
        if(b) {
            ShiroUtils.flushCurrentUser(currentUser);
        }

        return new SimpleResponse<>(b);
    }

    @PostMapping("/update")
    @User
    @ApiOperation(value = "更新用户信息")
    public SimpleResponse<CurrentUser> updateUserInformation(@RequestBody UserUpdateVo vo) {
        Boolean b = userInformationService.update(modelMapper.map(vo, UserUpdateIbo.class));

        CurrentUser currentUser = ShiroUtils.currentUser();

        if(b) {
            modelMapper.map(vo, currentUser);
            ShiroUtils.flushCurrentUser(currentUser);
        }

        return new SimpleResponse<>(currentUser);
    }

    /**
     * 校验验证码
     * @param key 存储在redis 的 key
     * @param code 输入的验证码
     * @throws com.pdsu.banmeng.exception.BusinessException
     *  验证码错误/过期
     */
    private void checkCode(@NonNull String key, @NonNull String code) {
        String redisCode = RedisUtils.get(key);

        Assert.nonNull(redisCode, StatusEnum.EMAIL_CODE_TIME_OUT);

        Assert.isTrue(code.equalsIgnoreCase(redisCode), StatusEnum.EMAIL_CODE_ERROR);
    }

    /**
     * 发送邮箱信息
     * @param prefix token 前缀
     * @param email 邮箱
     * @param name 用户名
     * @return
     *  token
     * @throws EmailException
     * 发送失败
     */
    @NonNull
    private String sendEmail(String prefix, String email, String name) throws EmailException {
        EmailHelper helper = new EmailHelper();
        helper.sendEmailForApply(email, name);

        String code = helper.getText();
        String token = RandomUtils.getUUID();

        RedisUtils.set(prefix + token, code, CODE_EXIST_TIME);

        return token;
    }

}
