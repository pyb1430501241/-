package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.banmeng.entity.WebInformation;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import com.pdsu.banmeng.ibo.BlobInsertIbo;
import com.pdsu.banmeng.ibo.BlobSearchIbo;
import com.pdsu.banmeng.ibo.SimpleBlobInsertIbo;
import com.pdsu.banmeng.ibo.SimpleBlobUpdateIbo;
import com.pdsu.banmeng.mapper.WebInformationMapper;
import com.pdsu.banmeng.service.IWebInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
public class WebInformationServiceImpl extends ServiceImpl<WebInformationMapper, WebInformation> implements IWebInformationService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Integer insert(SimpleBlobInsertIbo ibo, Function<WebInformation, Integer> after) {
        WebInformation webInformation = modelMapper.map(ibo, WebInformation.class);

        webInformation.setWebData(ibo.getData().getBytes(StandardCharsets.UTF_8));

        boolean save = save(webInformation);

        if(save) {
            return after.apply(webInformation);
        }

        throw new BusinessException(StatusEnum.BLOB_ADD_ERROR);
    }

    @Override
    public Integer update(SimpleBlobUpdateIbo ibo, Function<WebInformation, Integer> after) {
        WebInformation webInformation = modelMapper.map(ibo, WebInformation.class);
        webInformation.setWebData(ibo.getData().getBytes(StandardCharsets.UTF_8));

        boolean update = updateById(webInformation);

        if(update) {
            return after.apply(webInformation);
        }

        throw new BusinessException(StatusEnum.BLOB_UPDATE_ERROR);
    }

    @Override
    @Cacheable(value = "Code_Sharing_Community_WebInformationService_page", key = "#searchIbo")
    public Page<WebInformation> page(BlobSearchIbo searchIbo) {
        return page(new Page<>(searchIbo.getP(), searchIbo.getSize())
                , new QueryWrapper<WebInformation>()
                        .setEntity(modelMapper.map(searchIbo, WebInformation.class))
                        .orderByDesc("update_time"));
    }

    @Override
    public boolean isExistById(Integer webId) {
        return count(new QueryWrapper<WebInformation>().setEntity(WebInformation.builder().id(webId).build())) != 0;
    }

    @Override
    @Cacheable(value = "Code_Sharing_Community_WebInformationService_count", key = "#blobSearchIbo")
    public Integer count(BlobSearchIbo blobSearchIbo) {
        return count(new QueryWrapper<WebInformation>().setEntity(modelMapper.map(blobSearchIbo, WebInformation.class)));
    }

}
