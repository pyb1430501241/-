package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.entity.WebFile;
import com.pdsu.banmeng.ibo.FileInsertIbo;
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

    @Override
    public Boolean isExist(FileSearchIbo ibo) {
        return count(ibo) != 0;
    }

    @Override
    public Integer save(FileInsertIbo ibo) {
        WebFile file = modelMapper.map(ibo, WebFile.class);

        save(file);

        return file.getId();
    }

    @Override
    public Boolean update(FileInsertIbo ibo) {
        return update(modelMapper.map(ibo, WebFile.class),
                new QueryWrapper<WebFile>().setEntity(WebFile
                        .builder()
                        .uid(ibo.getUid())
                        .title(ibo.getTitle())
                        .build()));
    }

}
