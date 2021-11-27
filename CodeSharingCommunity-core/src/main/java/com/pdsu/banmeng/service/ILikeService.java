package com.pdsu.banmeng.service;

import com.pdsu.banmeng.bo.ReversalBo;
import com.pdsu.banmeng.entity.Like;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.CollectionSearchIbo;
import com.pdsu.banmeng.ibo.LikeRemoveIbo;
import com.pdsu.banmeng.ibo.LikeSearchIbo;

import java.util.function.Function;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface ILikeService extends IService<Like> {

    /**
     * 获取特定条件的数据条目
     * @param ibo
     * @return
     */
    Integer count(LikeSearchIbo ibo);

    /**
     * 根据条件获取关注是否存在
     * @param ibo
     * @return
     */
    Boolean isExist(LikeSearchIbo ibo);

    /**
     *根据条件判断关注信息是否存在
     * @param ibo 条件
     * @param exist 存在则执行
     * @param other 不存在则执行
     * @return
     */
    ReversalBo isExist(LikeSearchIbo ibo, Function<LikeSearchIbo, ReversalBo> exist
            , Function<LikeSearchIbo, ReversalBo> other);

    /**
     * 根据条件删除关注信息
     * @param ibo 条件
     * @return
     */
    Boolean remove(LikeRemoveIbo ibo);
}
