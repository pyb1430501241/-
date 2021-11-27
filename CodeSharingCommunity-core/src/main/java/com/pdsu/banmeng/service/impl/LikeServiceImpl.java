package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.bo.ReversalBo;
import com.pdsu.banmeng.entity.Like;
import com.pdsu.banmeng.ibo.LikeRemoveIbo;
import com.pdsu.banmeng.ibo.LikeSearchIbo;
import com.pdsu.banmeng.mapper.LikeMapper;
import com.pdsu.banmeng.service.ILikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, Like> implements ILikeService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "Code_Sharing_Community_LikeService_count", key = "#ibo")
    public Integer count(LikeSearchIbo ibo) {
        return count(new QueryWrapper<Like>().setEntity(modelMapper.map(ibo, Like.class)));
    }

    @Override
    public Boolean isExist(LikeSearchIbo ibo) {
        return count(ibo) != 0;
    }

    @Override
    public ReversalBo isExist(LikeSearchIbo ibo, Function<LikeSearchIbo, ReversalBo> exist, Function<LikeSearchIbo, ReversalBo> other) {
        if(isExist(ibo)) {
            return exist.apply(ibo);
        }

        return other.apply(ibo);
    }

    @Override
    public Boolean remove(LikeRemoveIbo ibo) {
        return remove(new QueryWrapper<Like>().setEntity(modelMapper.map(ibo, Like.class)));
    }

}
