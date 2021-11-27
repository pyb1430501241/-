package com.pdsu.banmeng.manager.impl;

import com.pdsu.banmeng.bo.AuthorBo;
import com.pdsu.banmeng.bo.FansInformationBo;
import com.pdsu.banmeng.bo.PageTemplateBo;
import com.pdsu.banmeng.bo.SimpleBlobBo;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.entity.Email;
import com.pdsu.banmeng.entity.Image;
import com.pdsu.banmeng.entity.UserInformation;
import com.pdsu.banmeng.entity.UserRole;
import com.pdsu.banmeng.enums.RoleEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.ibo.*;
import com.pdsu.banmeng.manager.IUserManager;
import com.pdsu.banmeng.service.*;
import com.pdsu.banmeng.utils.Assert;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private IWebInformationService webInformationService;

    @Autowired
    private ILikeService likeService;

    @Autowired
    private IWebThumbsService webThumbsService;

    @Autowired
    private IVisitInformationService visitInformationService;

    @Autowired
    private IWebFileService fileService;

    @Autowired
    private IFileDownloadService downloadService;

    @Autowired
    private ICollectionService collectionService;

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

    @Override
    @Cacheable(value = "Code_Sharing_Community_UserManager_getAuthor", key = "#uid")
    public AuthorBo getAuthor(Integer uid, CurrentUser currentUser) {
        Assert.isTrue(userInformationService.isExist(UserSearchIbo.builder().uid(uid).build()), StatusEnum.NOT_FOUND);

        CurrentUser user = userInformationService.listByUids(Collections.singletonList(uid)).get(0);
        List<SimpleBlobBo> blobs = modelMapper.map(webInformationService.page(BlobSearchIbo
                .builder().p(1).size(5).uid(uid).build()).getRecords(), new TypeToken<List<SimpleBlobBo>>(){}.getType());
        String imagePath = imageService.getImage(ImageSearchIbo.builder().uid(uid).build()).getImagePath();
        Integer fansNumber = likeService.count(LikeSearchIbo.builder().likeId(uid).build());
        Integer thumbsNumber = webThumbsService.count(ThumbsSearchIbo.builder().bid(uid).build());
        Integer commentNumber = 0;
        Integer commentReplyNumber = 0;
        Integer collectionNumber = collectionService.count(CollectionSearchIbo.builder().bid(uid).build());
        Integer visitNumber = visitInformationService.count(VisitSearchIbo.builder().sid(uid).build());
        Integer originalNumber = webInformationService.count(BlobSearchIbo.builder().uid(uid).type(1).build());
        Integer attentionNumber = likeService.count(LikeSearchIbo.builder().uid(uid).build());
        Integer fileNumber = fileService.count(FileSearchIbo.builder().uid(uid).build());
        Integer downloadNumber = downloadService.count(FileDownloadSearchIbo.builder().bid(uid).build());
        Boolean like = true;

        System.err.println(currentUser);

        if(currentUser == null) {
            like = false;
        } else {
            like = likeService.isExist(LikeSearchIbo.builder()
                    .uid(currentUser.getUid())
                    .likeId(uid)
                    .build());
        }

        return AuthorBo.builder()
                .uid(uid)
                .id(user.getId())
                .username(user.getUsername())
                .lastBlob(blobs)
                .imagePath(imagePath)
                .fansNumber(fansNumber)
                .thumbsNumber(thumbsNumber)
                .collectionNumber(collectionNumber)
                .commentNumber(commentNumber + commentReplyNumber)
                .visitNumber(visitNumber)
                .originalNumber(originalNumber)
                .attentionNumber(attentionNumber)
                .fileNumber(fileNumber)
                .downloadNumber(downloadNumber)
                .like(like)
                .build();
    }

    @Override
    public PageTemplateBo<FansInformationBo> getFans(FansSearchIbo ibo) {
        return null;
    }

}
