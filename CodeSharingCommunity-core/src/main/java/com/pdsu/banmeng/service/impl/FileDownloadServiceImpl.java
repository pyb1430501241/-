package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.entity.FileDownload;
import com.pdsu.banmeng.ibo.FileDownloadSearchIbo;
import com.pdsu.banmeng.mapper.FileDownloadMapper;
import com.pdsu.banmeng.service.IFileDownloadService;
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
public class FileDownloadServiceImpl extends ServiceImpl<FileDownloadMapper, FileDownload> implements IFileDownloadService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "Code_Sharing_Community_FileDownloadService_count", key = "#ibo")
    public Integer count(FileDownloadSearchIbo ibo) {
        return count(new QueryWrapper<FileDownload>().
                setEntity(modelMapper.map(ibo, FileDownload.class)));
    }

}
