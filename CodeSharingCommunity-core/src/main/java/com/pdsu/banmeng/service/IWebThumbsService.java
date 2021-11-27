package com.pdsu.banmeng.service;

import com.pdsu.banmeng.bo.ReversalBo;
import com.pdsu.banmeng.entity.WebThumbs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.LikeSearchIbo;
import com.pdsu.banmeng.ibo.ThumbsRemoveIbo;
import com.pdsu.banmeng.ibo.ThumbsSearchIbo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface IWebThumbsService extends IService<WebThumbs> {

    /**
     * 获取 webId 对应的文章的收藏数量
     * @param webIds webIds
     * @return
     * map
     */
    Map<Integer, Integer> countByWebIds(List<Integer> webIds);


    /**
     * 获取特定条件的数据条目
     * @param ibo
     * @return
     */
    Integer count(ThumbsSearchIbo ibo);

    /**
     * 点赞是否存在
     * @param ibo
     * @return
     */
    Boolean isExist(ThumbsSearchIbo ibo);

    /**
     * 点赞是否存在
     * @param ibo 条件
     * @param exist 存在则执行
     * @param other 不存在则执行
     * @return
     * 操作是否成功
     */
    ReversalBo isExist(ThumbsSearchIbo ibo, Function<ThumbsSearchIbo, ReversalBo> exist
            , Function<ThumbsSearchIbo, ReversalBo> other);

    /**
     * 根据条件删除一条点赞记录
     * @param ibo
     * @return
     */
    Boolean remove(ThumbsRemoveIbo ibo);
}
