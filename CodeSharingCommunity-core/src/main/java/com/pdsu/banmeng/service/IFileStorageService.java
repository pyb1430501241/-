package com.pdsu.banmeng.service;

import com.pdsu.banmeng.enums.FileTypeEnum;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-05 17:54
 */
public interface IFileStorageService {

    void init();

    /**
     * 保存文件
     * @param multipartFile 文件
     * @return 文件名
     */
    String save(@NonNull MultipartFile multipartFile, FileTypeEnum typeEnum);

    /**
     * 下载文件
     * @param fileName 文件名
     * @return
     */
    Resource load(String fileName, FileTypeEnum typeEnum);

    Stream<Path> load(FileTypeEnum typeEnum);

    void clear();

}
