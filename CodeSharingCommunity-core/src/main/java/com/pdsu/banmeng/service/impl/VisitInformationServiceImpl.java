package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.entity.VisitInformation;
import com.pdsu.banmeng.ibo.VisitSearchIbo;
import com.pdsu.banmeng.mapper.VisitInformationMapper;
import com.pdsu.banmeng.service.IVisitInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Service
public class VisitInformationServiceImpl extends ServiceImpl<VisitInformationMapper, VisitInformation> implements IVisitInformationService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "Code_Sharing_community_VisitService_countByWebIds", key = "#webIds")
    public Map<Integer, Integer> countByWebIds(List<Integer> webIds) {
        Map<Integer, Integer> map = new HashMap<>();

        webIds.forEach(webId -> {
            map.put(webId, count(new QueryWrapper<VisitInformation>().setEntity(VisitInformation.builder().wid(webId).build())));
        });

        return map;
    }

    @Override
    @Cacheable(value = "Code_Sharing_community_VisitService_count", key = "#visitSearchIbo")
    public Integer count(VisitSearchIbo visitSearchIbo) {
        return count(new QueryWrapper<VisitInformation>().setEntity(modelMapper.map(visitSearchIbo, VisitInformation.class)));
    }
}
