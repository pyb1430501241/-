package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.entity.WebFile;
import com.pdsu.banmeng.ibo.FileSearchIbo;
import com.pdsu.banmeng.mapper.WebFileMapper;
import com.pdsu.banmeng.service.IWebFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Service
public class WebFileServiceImpl extends ServiceImpl<WebFileMapper, WebFile> implements IWebFileService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "Code_Sharing_Community_WebFileService_count", key = "#ibo")
    public Integer count(FileSearchIbo ibo) {
        return count(new QueryWrapper<WebFile>().setEntity(modelMapper.map(ibo, WebFile.class)));
    }
}
