package com.pdsu.banmeng.controller;

import com.pdsu.banmeng.business.User;
import com.pdsu.banmeng.context.RequestContext;
import com.pdsu.banmeng.dto.SimpleResponse;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

