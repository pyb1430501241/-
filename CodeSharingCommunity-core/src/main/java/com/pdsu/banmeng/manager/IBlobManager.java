package com.pdsu.banmeng.manager;

import com.pdsu.banmeng.bo.*;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.ibo.BlobInsertIbo;
import com.pdsu.banmeng.ibo.BlobSearchIbo;
import com.pdsu.banmeng.ibo.ReversalStatusIbo;
import com.pdsu.banmeng.ibo.UserBlobSearchIbo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 11:34
 */
public interface IBlobManager {

    /**
     * 发布文章
     * @param ibo 信息
     * @return
     * 发布成功 返回 id,
     * 失败返回 -1
     *
     */
    Integer contribution(BlobInsertIbo ibo, CurrentUser currentUser);


    /**
     * 获取博客首页数据
     * @return
     */
    PageTemplateBo<SimpleBlobIndexBo> getIndex(BlobSearchIbo ibo);

    /**
     * 根据Id 获取一个博客的所有信息
     * @param webId
     * @return
     */
    BlobInformationBo toBlob(Integer webId, CurrentUser user);

    /**
     * 反转用户对点赞、关注和收藏的状态
     * @return
     */
    ReversalBo reversal(ReversalStatusIbo ibo, CurrentUser currentUser);

    /**
     * 获取对应用户博客
     * @return
     */
    PageTemplateBo<SimpleUserBlobBo> getBlobs(UserBlobSearchIbo ibo);

    /**
     * 删除博客
     * 如果当前用户为博客的主人, 则删除
     * 如果当前用户为System 则删除
     * @param id 博客id
     * @param currentUser 当前用户
     * @return
     * 是否成功
     */
    Boolean deleteBlob(Integer id, CurrentUser currentUser);

    /**
     * 获取用的收藏
     * @param ibo 条件
     * @return
     * 是否成功
     */
    PageTemplateBo<SimpleUserBlobBo> getCollection(UserBlobSearchIbo ibo);

}
