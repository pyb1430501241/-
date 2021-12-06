package com.pdsu.banmeng.manager.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.banmeng.bo.*;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.entity.*;
import com.pdsu.banmeng.entity.Collection;
import com.pdsu.banmeng.enums.RoleEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import com.pdsu.banmeng.ibo.*;
import com.pdsu.banmeng.manager.IBlobManager;
import com.pdsu.banmeng.service.*;
import com.pdsu.banmeng.utils.Assert;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 11:35
 */
@Service
public class BlobManager implements IBlobManager {

    private static final Integer INDEX = 0;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IWebInformationService webInformationService;

    @Autowired
    private IWebLabelControlService webLabelControlService;

    @Autowired
    private IUserInformationService userInformationService;

    @Autowired
    private IVisitInformationService visitInformationService;

    @Autowired
    private IWebThumbsService webThumbsService;

    @Autowired
    private ICollectionService collectionService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private ILikeService likeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {
            "Code_Sharing_Community_BlobManage_getBlobs",
//            "Code_Sharing_Community_BlobManager_getIndex"
    }, allEntries = true)
    public Integer contribution(BlobInsertIbo ibo, CurrentUser currentUser) {
        SimpleBlobInsertIbo simpleBlobInsertIbo = modelMapper.map(ibo, SimpleBlobInsertIbo.class);
        simpleBlobInsertIbo.setUid(currentUser.getUid());

        return webInformationService.insert(simpleBlobInsertIbo, blob -> {

            if(ibo.getLabelIds().size() != 0) {
                List<WebLabelControl> list = new ArrayList<>();

                ibo.getLabelIds().forEach(e -> {
                    list.add(WebLabelControl.builder().lid(e).wid(blob.getId()).build());
                });

                if(webLabelControlService.saveBatch(list)) {
                    return afterContribution(blob);
                }
            } else {
                return afterContribution(blob);
            }

            throw new BusinessException(StatusEnum.BLOB_ADD_ERROR);
        });

    }

    /**
     * 为es 插入文章信息做准备
     * @param webInformation
     * @return
     */
    private Integer afterContribution(WebInformation webInformation) {
        return webInformation.getId();
    }

    @Override
    @Cacheable(value = "Code_Sharing_Community_BlobManager_getIndex", key = "#ibo")
    public PageTemplateBo<SimpleBlobIndexBo> getIndex(BlobSearchIbo ibo) {
        Page<WebInformation> page = new Page<>();

        if(INDEX.equals(ibo.getLid())) {
            ibo.setSize(10);
            page = webInformationService.page(ibo);
        } else {
            List<Integer> webIds = webLabelControlService.getWebIdsById(ibo);

            Assert.isFalse(webIds.size() == 0, StatusEnum.NOT_FOUND);

            page.setRecords(webInformationService.listByIds(webIds));
        }

        Assert.isFalse(page.getRecords().size() == 0, StatusEnum.NOT_FOUND);

        List<Integer> uids = new ArrayList<>();
        List<Integer> webIds = new ArrayList<>();

        page.getRecords().forEach(e -> {
            uids.add(e.getUid());
            webIds.add(e.getId());
        });

        Map<Integer, CurrentUser> users = userInformationService.listByUids(uids).stream()
                .collect(Collectors.toMap(CurrentUser :: getUid, CurrentUser :: context));
        Map<Integer, String> userImages = imageService.listImageByUids(uids).stream()
                .collect(Collectors.toMap(ImageBo :: getUid, ImageBo :: getImagePath));
        List<SimpleBlobBo> blobs = modelMapper.map(page.getRecords(), new TypeToken<List<SimpleBlobBo>>(){}.getType());

        // 设置用户头像
        users.keySet().forEach(key -> {
            CurrentUser currentUser = users.get(key);
            currentUser.setImgPath(userImages.get(currentUser.getUid()));
        });

        return fillPageInformation(webIds, blobs, page, users);
    }

    @Override
    @CachePut(value = "Code_Sharing_Community_BlobManager_toBlob", key = "#webId")
    public BlobInformationBo toBlob(Integer webId, CurrentUser currentUser) {
        List<Integer> webIds = Collections.singletonList(webId);

        Assert.isTrue(webInformationService.isExistById(webId), StatusEnum.NOT_FOUND);

        WebInformation webInformation = webInformationService.getById(webId);

        // 默认访问角色
        if(currentUser == null) {
            currentUser = new CurrentUser();
            currentUser.setUid(0);
        }

        // 添加访问记录
        visitInformationService.save(VisitInformation
                .builder().wid(webId).sid(webInformation.getUid())
                .vid(currentUser.getUid()).build());

        Map<Integer, Integer> collections = collectionService.countByWebIds(webIds);
        Map<Integer, Integer> visits = visitInformationService.countByWebIds(webIds);
        Map<Integer, Integer> thumbs = webThumbsService.countByWebIds(webIds);
        // 如果为 uid 为 0 则为默认访问角色, 认为为游客
        Boolean thumbsStatus = currentUser.getUid().equals(0) ?
                false : webThumbsService.isExist(ThumbsSearchIbo.builder().uid(currentUser.getUid()).webId(webId).build());
        Boolean collectionStatus = currentUser.getUid().equals(0) ?
                false : collectionService.isExist(CollectionSearchIbo.builder().uid(currentUser.getUid()).wid(webId).build());



        BlobInformationBo blobInformationBo = modelMapper.map(webInformation, BlobInformationBo.class);

        blobInformationBo.setCollection(collections.get(webId));
        blobInformationBo.setThumbs(thumbs.get(webId));
        blobInformationBo.setVisit(visits.get(webId));
        blobInformationBo.setCollectionStatus(collectionStatus);
        blobInformationBo.setThumbsStatus(thumbsStatus);
        blobInformationBo.setData(new String(webInformation.getWebData(), StandardCharsets.UTF_8));

        return blobInformationBo;
    }

    @Override
    @CacheEvict(value = {
            "Code_Sharing_Community_UserManger_getFans",
            "Code_Sharing_Community_BlobManager_getCollection"
    }, allEntries = true)
    public ReversalBo reversal(ReversalStatusIbo ibo, CurrentUser currentUser) {
        ReversalBo reversalBo = new ReversalBo();

        switch (ibo.getType()) {
            case "thumbs":
                Assert.isTrue(webInformationService.isExistById(ibo.getWebId()), StatusEnum.NOT_FOUND);

                return webThumbsService.isExist(modelMapper.map(ibo, ThumbsSearchIbo.class)
                        , searchIbo ->  {
                            Assert.isTrue(webThumbsService.remove(modelMapper.map(searchIbo
                                    , ThumbsRemoveIbo.class)), StatusEnum.REVERSAL_ERROR);

                            reversalBo.setStatus(false);
                            reversalBo.setMsg("取消点赞");
                            return reversalBo;
                        }, searchIbo -> {
                            Assert.isTrue(webThumbsService.save(WebThumbs.builder()
                                    .webId(ibo.getWebId())
                                    .uid(currentUser.getUid())
                                    .bid(ibo.getBid())
                                    .build()), StatusEnum.REVERSAL_ERROR);

                            reversalBo.setStatus(true);
                            reversalBo.setMsg("点赞成功");
                            return reversalBo;
                        });
            case "collection":
                Assert.isTrue(webInformationService.isExistById(ibo.getWebId()), StatusEnum.NOT_FOUND);

                return collectionService.isExist(CollectionSearchIbo.builder()
                        .wid(ibo.getWebId()).bid(ibo.getBid()).build()
                        , searchIbo -> {
                            Assert.isTrue(collectionService.remove(modelMapper.map(searchIbo
                                    , CollectionRemoveIbo.class)), StatusEnum.REVERSAL_ERROR);

                            reversalBo.setStatus(false);
                            reversalBo.setMsg("取消收藏");
                            return reversalBo;
                        }
                        , searchIbo -> {
                           Assert.isTrue(collectionService.save(Collection.builder()
                                    .bid(ibo.getBid())
                                    .uid(currentUser.getUid())
                                    .wid(ibo.getWebId())
                                    .build()), StatusEnum.REVERSAL_ERROR);

                           reversalBo.setStatus(true);
                           reversalBo.setMsg("收藏成功");
                           return reversalBo;
                        });
            case "like":
                Assert.isTrue(userInformationService.isExist(UserSearchIbo.builder().uid(ibo.getLikeId()).build()), StatusEnum.NOT_FOUND);

                return likeService.isExist(LikeSearchIbo.builder()
                        .likeId(ibo.getLikeId())
                        .uid(currentUser.getUid())
                        .build(), searchIbo -> {
                            Assert.isTrue(likeService.remove(LikeRemoveIbo.builder()
                                    .likeId(ibo.getLikeId())
                                    .uid(currentUser.getUid())
                                    .build()), StatusEnum.REVERSAL_ERROR);

                            reversalBo.setStatus(false);
                            reversalBo.setMsg("取消关注");
                            return reversalBo;
                        }, searchIbo -> {
                            Assert.isTrue(likeService.save(Like.builder()
                                    .uid(currentUser.getUid())
                                    .likeId(ibo.getLikeId())
                                    .build()), StatusEnum.REVERSAL_ERROR);

                            reversalBo.setStatus(true);
                            reversalBo.setMsg("关注成功");
                            return reversalBo;
                        });
            default:
                throw new BusinessException(StatusEnum.NOT_FOUND);
        }
    }

    /**
     * 填充博客的点赞、收藏、浏览量信息。
     * @param webIds 博客集
     * @param list 目的集合
     * @param <T> simpleUserBlob
     */
    private <T extends SimpleUserBlobBo> void fillBlobInformation(List<Integer> webIds
            , List<T> list) {
        Map<Integer, Integer> collections = collectionService.countByWebIds(webIds);
        Map<Integer, Integer> visits = visitInformationService.countByWebIds(webIds);
        Map<Integer, Integer> thumbs = webThumbsService.countByWebIds(webIds);

        list.forEach(blob -> {
            Integer webId = blob.getBlob().getId();

            blob.setVisit(visits.get(webId));
            blob.setThumbs(thumbs.get(webId));
            blob.setCollection(collections.get(webId));
        });

    }

    @Override
    @Cacheable(value = "Code_Sharing_Community_BlobManage_getBlobs", key = "#ibo")
    public PageTemplateBo<SimpleUserBlobBo> getBlobs(UserBlobSearchIbo ibo) {
        Page<WebInformation> page = webInformationService.page(modelMapper.map(ibo, BlobSearchIbo.class));

        Assert.isFalse(page.getRecords().isEmpty(), StatusEnum.NOT_FOUND);

        List<Integer> webIds = new ArrayList<>();

        page.getRecords().forEach(e -> {
            webIds.add(e.getId());
        });

        List<SimpleBlobBo> blobs = modelMapper.map(page.getRecords(), new TypeToken<List<SimpleBlobBo>>(){}.getType());

        return fillPageInformation(webIds, blobs, page, new HashMap<>());
    }

    @Override
    @CacheEvict(value = {
            "Code_Sharing_Community_BlobManage_getBlobs",
            "Code_Sharing_Community_WebInformationService_page"
        }, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteBlob(Integer id, CurrentUser currentUser) {
        WebInformation web = webInformationService.getById(id);

        Assert.nonNull(web, StatusEnum.NOT_FOUND);

        if(!web.getUid().equals(currentUser.getUid())) {
            Assert.isTrue(RoleEnum.SYSTEM.equals(currentUser.getRole()), StatusEnum.NO_PERMISSION);

            // 待写, 系统删除文章则对用户发送系统通知
        }

        return webInformationService.removeById(id);
    }

    @Cacheable(value = "Code_Sharing_Community_BlobManager_getCollection", key = "#ibo")
    @Override
    public PageTemplateBo<SimpleUserBlobBo> getCollection(UserBlobSearchIbo ibo) {
        Page<Collection> page = collectionService.page(modelMapper.map(ibo, CollectionSearchIbo.class));

        Assert.isFalse(page.getRecords().isEmpty(), StatusEnum.NOT_FOUND);

        List<Integer> webIds = new ArrayList<>();
        List<Integer> uids = new ArrayList<>();

        page.getRecords().forEach(e -> {
            webIds.add(e.getWid());
            uids.add(e.getBid());
        });

        List<SimpleBlobBo> webList = modelMapper.map(webInformationService.listByIds(webIds)
                , new TypeToken<List<SimpleBlobBo>>(){}.getType());
        Map<Integer, CurrentUser> users = userInformationService.listByUids(uids).stream()
                .collect(Collectors.toMap(CurrentUser :: getUid, CurrentUser :: context));

        return fillPageInformation(webIds, webList, page, users);
    }

    /**
     * 对分页信息进行填充
     * @param webIds 文章id
     * @param webList 文章集合
     * @param page 分页信息
     * @param users 博客的作者信息, 为empty 则代表不需要作者信息
     * @return
     *  pageTemplate
     */
    @SuppressWarnings("all")
    private <T extends SimpleUserBlobBo> PageTemplateBo<T> fillPageInformation(List<Integer> webIds
            , List<SimpleBlobBo> webList, Page<?> page, Map<Integer, CurrentUser> users) {
        List<T> list = new ArrayList<>();

        // 填充 Index Blob
        webList.forEach(blob -> {
            T t;

            if(!users.isEmpty()) {
                SimpleBlobIndexBo index = new SimpleBlobIndexBo();

                index.setUser(users.get(blob.getUid()));
                index.setBlob(blob);

                t = (T) index;
            } else {
                SimpleUserBlobBo index = SimpleUserBlobBo.builder()
                        .blob(blob)
                        .build();

                t = (T) index;
            }

            list.add(t);
        });

        fillBlobInformation(webIds, list);

        PageTemplateBo<T> lastPage = new PageTemplateBo<>();

        lastPage.init(page);
        lastPage.setRecords(list);

        return lastPage;
    }

}
