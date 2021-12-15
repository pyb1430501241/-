package com.pdsu.banmeng.controller;

import com.pdsu.banmeng.bo.DownloadBo;
import com.pdsu.banmeng.business.Tourist;
import com.pdsu.banmeng.business.User;
import com.pdsu.banmeng.context.RequestContext;
import com.pdsu.banmeng.dto.SimpleResponse;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import com.pdsu.banmeng.ibo.FileInsertIbo;
import com.pdsu.banmeng.manager.IFileManager;
import com.pdsu.banmeng.service.IFileStorageService;
import com.pdsu.banmeng.vo.FileInsertVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-06 19:00
 */
@RestController
@RequestMapping("/file")
@Api(tags = "资源管理")
public class FileController {

    @Autowired
    private IFileStorageService fileStorageService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IFileManager fileManager;

    @PostMapping("/upload")
    @User
    @ApiOperation(value = "用户资源上传")
    public SimpleResponse<Integer> upload(FileInsertVo vo) {
        return new SimpleResponse<>(fileManager.insert(modelMapper.map(vo, FileInsertIbo.class)
                , RequestContext.currentUser()));
    }

    @GetMapping("/download/{id}")
    @User
    @ApiOperation(value = "资源下载")
    public ResponseEntity<Resource> download(@PathVariable Integer id) {
        DownloadBo resource = fileManager.download(id, RequestContext.currentUser());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "multipart/form-data")
                .header(HttpHeaders.CONTENT_ENCODING, "UTF-8")
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""
                        + new String(resource.getFileName().getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + "\"")
                .body(resource.getResource());
    }

}

