package com.pdsu.banmeng.service;

import com.pdsu.banmeng.bo.ReversalBo;
import com.pdsu.banmeng.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.CollectionRemoveIbo;
import com.pdsu.banmeng.ibo.CollectionSearchIbo;
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
public interface ICollectionService extends IService<Collection> {

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
    Integer count(CollectionSearchIbo ibo);

    /**
     *根据条件判断收藏信息是否存在
     * @param ibo
     * @return
     */
    Boolean isExist(CollectionSearchIbo ibo);

    /**
     *根据条件判断收藏信息是否存在
     * @param ibo
     * @return
     */
    ReversalBo isExist(CollectionSearchIbo ibo, Function<CollectionSearchIbo, ReversalBo> exist
            , Function<CollectionSearchIbo, ReversalBo> other);

    /**
     * 根据条件删除特定元素
     * @param iob
     * @return
     */
    Boolean remove(CollectionRemoveIbo iob);
}
