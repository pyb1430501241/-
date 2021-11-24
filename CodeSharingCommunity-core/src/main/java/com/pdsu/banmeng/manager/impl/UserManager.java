package com.pdsu.banmeng.manager.impl;

import com.pdsu.banmeng.entity.Email;
import com.pdsu.banmeng.entity.Image;
import com.pdsu.banmeng.entity.UserInformation;
import com.pdsu.banmeng.entity.UserRole;
import com.pdsu.banmeng.enums.RoleEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.ibo.ApplyAccountIbo;
import com.pdsu.banmeng.ibo.UserSearchIbo;
import com.pdsu.banmeng.manager.IUserManager;
import com.pdsu.banmeng.service.IEmailService;
import com.pdsu.banmeng.service.IImageService;
import com.pdsu.banmeng.service.IUserInformationService;
import com.pdsu.banmeng.service.IUserRoleService;
import com.pdsu.banmeng.utils.Assert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-24 18:18
 */
@Service
public class UserManager implements IUserManager {

    @Autowired
    private IUserInformationService userInformationService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean applyAccount(ApplyAccountIbo applyAccountIbo) {
        Assert.isFalse(userInformationService.isExist(UserSearchIbo.builder()
                .uid(Integer.parseInt(applyAccountIbo.getUid())).build()), StatusEnum.USER_UID_EXIST);

        Assert.isFalse(userInformationService.isExist(UserSearchIbo.builder()
                .username(applyAccountIbo.getUsername()).build()), StatusEnum.USER_NAME_EXIST);

       return userInformationService.applyAccount(applyAccountIbo, user -> {
            Assert.isTrue(imageService.save(Image.builder().uid(user.getUid()).imagePath("12.png").build()), StatusEnum.USER_IMAGE_ADD_ERROR);

            Assert.isTrue(userRoleService.save(UserRole.builder().uid(user.getUid()).roleId(RoleEnum.USER.getId()).build()), StatusEnum.USER_ROLE_ADD_ERROR);

            Assert.isTrue(emailService.save(Email.builder().uid(user.getUid()).email(applyAccountIbo.getEmail()).build()), StatusEnum.USER_EMAIL_ADD_ERROR);

            return afterApplyAccount(user);
       });
    }

    /**
     * es 在添加用户后执行同步用户信息操作
     * @param user
     * @return
     */
    private boolean afterApplyAccount(UserInformation user) {
        return true;
    }

}
