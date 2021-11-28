package com.pdsu.banmeng.manager.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.banmeng.bo.*;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.entity.*;
import com.pdsu.banmeng.enums.RoleEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.ibo.*;
import com.pdsu.banmeng.manager.IUserManager;
import com.pdsu.banmeng.service.*;
import com.pdsu.banmeng.utils.Assert;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    @Cacheable(value = "Code_Sharing_Community_UserManger_getFans", key = "#ibo")
    public PageTemplateBo<FansInformationBo> getFansOrFollow(LikeSearchIbo ibo, CurrentUser currentUser) {
        Page<Like> page = likeService.page(ibo);

        List<Integer> uids;

        // 如果uid 为空则代表使用LikeId 查询信息
        // 即 查询的是fans
        // 反之则认为查询的是follow
        if(ibo.getUid() == null) {
            uids = page.getRecords().stream().map(Like :: getUid).collect(Collectors.toList());
        } else {
            uids = page.getRecords().stream().map(Like :: getLikeId).collect(Collectors.toList());
        }

        Assert.isTrue(uids.size() != 0, StatusEnum.NOT_FOUND);

        List<CurrentUser> users = userInformationService.listByUids(uids);
        Map<Integer, String> images = imageService.listImageByUids(uids).stream()
                .collect(Collectors.toMap(ImageBo :: getUid, ImageBo :: getImagePath));

        List<FansInformationBo> list = new ArrayList<>();

        users.forEach(user -> {

            user.setImgPath(images.get(user.getUid()));

            list.add(FansInformationBo.builder()
                    .user(user)
                    // 如果当前有登录用户, 则判断当前用户是否关注了的fans
                    .like(currentUser != null ? likeService.isExist(LikeSearchIbo.builder()
                            .uid(currentUser.getUid())
                            .likeId(user.getUid())
                            .build()) : false)
                    .build());

        });

        PageTemplateBo<FansInformationBo> lastList = new PageTemplateBo<>();

        lastList.init(page);
        lastList.setRecords(list);

        return lastList;
    }

}
