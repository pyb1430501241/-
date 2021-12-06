package com.pdsu.banmeng.service.impl;

import com.pdsu.banmeng.enums.FileTypeEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import com.pdsu.banmeng.service.IFileStorageService;
import com.pdsu.banmeng.utils.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-05 17:57
 */
public class FileStorageService implements IFileStorageService {

    @Override
    public void init() {
        try{
            for (FileTypeEnum value : FileTypeEnum.values()) {
                Files.createDirectories(value.getPath());
            }
        } catch (IOException e) {
            throw new BusinessException(StatusEnum.FILE_INIT_ERROR);
        }
    }

    @Override
    public String save(@NonNull MultipartFile multipartFile, FileTypeEnum typeEnum) {
        try {
            String newFileName = FileUtils.newFileName(multipartFile.getOriginalFilename());
            Path path = typeEnum.getPath().resolve(newFileName);

            Files.copy(multipartFile.getInputStream(), path);

            return newFileName;
        } catch (IOException e) {
            throw new BusinessException(StatusEnum.FILE_UPLOAD_ERROR);
        }
    }

    @Override
    public Resource load(String fileName, FileTypeEnum typeEnum) {
        try {
            Path resolve = typeEnum.getPath().resolve(fileName);
            Resource resource = new UrlResource(resolve.toUri());

            if(resource.exists() || resource.isReadable()) {
                return resource;
            }

            throw new BusinessException(StatusEnum.FILE_DOWNLOAD_ERROR);
        } catch (MalformedURLException e) {
            throw new BusinessException(StatusEnum.FILE_DOWNLOAD_ERROR);
        }
    }

    @Override
    public Stream<Path> load(FileTypeEnum typeEnum) {
        try {
            Path path = typeEnum.getPath();
            return Files.walk(path, 1)
                    .filter(t -> t.equals(path))
                    .map(path :: relativize);
        } catch (IOException e) {
            throw new BusinessException(StatusEnum.FILE_DOWNLOAD_ERROR);
        }
    }

    @Override
    public void clear() {

    }

}
