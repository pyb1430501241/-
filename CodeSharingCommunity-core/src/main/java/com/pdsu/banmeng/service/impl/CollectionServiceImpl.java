package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.banmeng.bo.ReversalBo;
import com.pdsu.banmeng.entity.Collection;
import com.pdsu.banmeng.ibo.CollectionRemoveIbo;
import com.pdsu.banmeng.ibo.CollectionSearchIbo;
import com.pdsu.banmeng.mapper.CollectionMapper;
import com.pdsu.banmeng.service.ICollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "Code_Sharing_community_CollectionService_countByWebIds", key = "#webIds")
    public Map<Integer, Integer> countByWebIds(List<Integer> webIds) {
        Map<Integer, Integer> map = new HashMap<>();

        webIds.forEach(webId -> {
           map.put(webId, count(new QueryWrapper<Collection>().setEntity(Collection.builder().wid(webId).build())));
        });
        
        return map;
    }

    @Override
    @Cacheable(value = "Code_Sharing_community_CollectionService_count", key = "#ibo")
    public Integer count(CollectionSearchIbo ibo) {
        return count(new QueryWrapper<Collection>().setEntity(modelMapper.map(ibo, Collection.class)));
    }

    @Override
    public Boolean isExist(CollectionSearchIbo ibo) {
        return count(ibo) != 0;
    }

    @Override
    public ReversalBo isExist(CollectionSearchIbo ibo, Function<CollectionSearchIbo, ReversalBo> exist
            , Function<CollectionSearchIbo, ReversalBo> other) {
        if(isExist(ibo)) {
            return exist.apply(ibo);
        }

        return other.apply(ibo);
    }

    @Override
    public Boolean remove(CollectionRemoveIbo ibo) {
        return remove(new QueryWrapper<Collection>().setEntity(modelMapper.map(ibo, Collection.class)));
    }


    @Override
    public Page<Collection> page(CollectionSearchIbo ibo) {
    return page(new Page<>(ibo.getP(), ibo.getSize()), new QueryWrapper<Collection>()
            .setEntity(modelMapper.map(ibo, Collection.class)).orderByDesc("create_time"));
    }

}
