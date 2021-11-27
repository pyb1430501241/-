package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.bo.ReversalBo;
import com.pdsu.banmeng.entity.Collection;
import com.pdsu.banmeng.entity.Like;
import com.pdsu.banmeng.entity.WebThumbs;
import com.pdsu.banmeng.ibo.ThumbsRemoveIbo;
import com.pdsu.banmeng.ibo.ThumbsSearchIbo;
import com.pdsu.banmeng.mapper.WebThumbsMapper;
import com.pdsu.banmeng.service.IWebThumbsService;
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
public class WebThumbsServiceImpl extends ServiceImpl<WebThumbsMapper, WebThumbs> implements IWebThumbsService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "Code_Sharing_community_WebThumbsService_countByWebIds", key = "#webIds")
    public Map<Integer, Integer> countByWebIds(List<Integer> webIds) {
        Map<Integer, Integer> map = new HashMap<>();

        webIds.forEach(webId -> {
            map.put(webId, count(new QueryWrapper<WebThumbs>().setEntity(WebThumbs.builder().webId(webId).build())));
        });

        return map;
    }

    @Override
    @Cacheable(value = "Code_Sharing_Community_WebThumbsService_count", key = "#ibo")
    public Integer count(ThumbsSearchIbo ibo) {
        return count(new QueryWrapper<WebThumbs>().setEntity(modelMapper.map(ibo, WebThumbs.class)));
    }

    @Override
    public Boolean isExist(ThumbsSearchIbo ibo) {
        return count(ibo) != 0;
    }

    @Override
    public ReversalBo isExist(ThumbsSearchIbo ibo, Function<ThumbsSearchIbo, ReversalBo> exist
            , Function<ThumbsSearchIbo, ReversalBo> other) {
        if(isExist(ibo)) {
            return exist.apply(ibo);
        }

        return other.apply(ibo);
    }

    @Override
    public Boolean remove(ThumbsRemoveIbo ibo) {
        return remove(new QueryWrapper<WebThumbs>().setEntity(modelMapper.map(ibo, WebThumbs.class)));
    }
}
