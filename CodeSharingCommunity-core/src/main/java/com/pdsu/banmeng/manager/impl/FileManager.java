package com.pdsu.banmeng.manager.impl;

import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.entity.WebFile;
import com.pdsu.banmeng.enums.FileTypeEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.ibo.FileInsertIbo;
import com.pdsu.banmeng.ibo.FileSearchIbo;
import com.pdsu.banmeng.manager.IFileManager;
import com.pdsu.banmeng.service.IFileDownloadService;
import com.pdsu.banmeng.service.IFileStorageService;
import com.pdsu.banmeng.service.IWebFileService;
import com.pdsu.banmeng.utils.Assert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-06 19:16
 */
@Service
public class FileManager implements IFileManager {

    @Autowired
    private IFileStorageService fileStorageService;

    @Autowired
    private IFileDownloadService fileDownloadService;

    @Autowired
    private IWebFileService webFileService;

    @Autowired
    private ModelMapper modelMapper;

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(5));

    @Override
    public Integer insert(FileInsertIbo ibo, CurrentUser currentUser) {
        Assert.isFalse(webFileService.isExist(FileSearchIbo.builder()
                .uid(currentUser.getUid()).title(ibo.getTitle()).build()), StatusEnum.FILE_ALREADY_EXIST);

        // 设置上传人
        ibo.setUid(currentUser.getUid());
        // 优先保存用户文件上传记录
        Integer id = webFileService.save(ibo);

        executor.execute(() -> {
            // 保存图片
            String filePath = fileStorageService.save(ibo.getFile(), FileTypeEnum.FILE);

            WebFile file = WebFile.builder().id(id).filePath(filePath).build();

            // 文件保存完毕后, 更新文件, 加入文件路径
            webFileService.updateById(file);
        });

        return id;
    }


}
