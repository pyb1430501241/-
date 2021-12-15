package com.pdsu.banmeng.controller;

import com.pdsu.banmeng.bo.*;
import com.pdsu.banmeng.business.Tourist;
import com.pdsu.banmeng.business.User;
import com.pdsu.banmeng.context.RequestContext;
import com.pdsu.banmeng.dto.SimpleResponse;
import com.pdsu.banmeng.enums.FileTypeEnum;
import com.pdsu.banmeng.ibo.*;
import com.pdsu.banmeng.manager.IBlobManager;
import com.pdsu.banmeng.service.IFileStorageService;
import com.pdsu.banmeng.service.ITypeService;
import com.pdsu.banmeng.service.IWebInformationService;
import com.pdsu.banmeng.service.IWebLabelService;
import com.pdsu.banmeng.vo.BlobIndexSearchVo;
import com.pdsu.banmeng.vo.BlobInsertVo;
import com.pdsu.banmeng.vo.ReversalStatusVo;
import com.pdsu.banmeng.vo.UserBlobSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-25 10:10
 */
@RestController
@RequestMapping("/blob")
@Validated
@Api(tags = "博客相关")
public class BlobController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IWebLabelService webLabelService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IBlobManager blobManager;

    @Autowired
    private IFileStorageService fileStorageService;

    @Autowired
    private IWebInformationService webInformationService;

    @GetMapping("/types")
    @ApiOperation(value = "获取所有文章标签")
    @User
    public SimpleResponse<List<TypeBo>> getType() {
        return new SimpleResponse<>(typeService.lists());
    }

    @GetMapping("/labels")
    @ApiOperation(value = "获取所有文章标签")
    @Tourist
    public SimpleResponse<List<WebLabelBo>> getLabel() {
        return new SimpleResponse<>(webLabelService.lists());
    }

    @PostMapping("/contribution")
    @ApiOperation(value = "发布文章")
    @User
    public SimpleResponse<Integer> contribution(@Valid @RequestBody BlobInsertVo vo) {
        return new SimpleResponse<>(blobManager.contribution(
                modelMapper.map(vo, BlobInsertIbo.class), RequestContext.currentUser()));
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改文章")
    @User
    public SimpleResponse<Integer> update(@Valid @RequestBody BlobInsertVo vo) {
        return new SimpleResponse<>(blobManager.update(
                modelMapper.map(vo, BlobUpdateIbo.class), RequestContext.currentUser()));
    }

    @PostMapping("/index")
    @ApiOperation(value = "博客首页")
    @Tourist
    public SimpleResponse<PageTemplateBo<SimpleBlobIndexBo>> index(@RequestBody BlobIndexSearchVo searchVo) {
        return new SimpleResponse<>(blobManager.getIndex(modelMapper.map(searchVo, BlobSearchIbo.class)));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "博客详情页")
    @Tourist
    public SimpleResponse<BlobInformationBo> blobInformation(@PathVariable Integer id) {
        return new SimpleResponse<>(blobManager.toBlob(id, RequestContext.currentUser()));
    }

    @PostMapping("/reversal")
    @ApiOperation(value = "反转用户对博客/作者的点赞、收藏、关注状态")
    @User
    public SimpleResponse<ReversalBo> reversalStatus(@RequestBody ReversalStatusVo vo) {
        return new SimpleResponse<>(blobManager.reversal(modelMapper.map(vo, ReversalStatusIbo.class), RequestContext.currentUser()));
    }

    @PostMapping("/blobs")
    @ApiOperation(value = "获取用户的博客")
    @Tourist
    public SimpleResponse<PageTemplateBo<SimpleUserBlobBo>> userBlobs(@RequestBody UserBlobSearchVo searchVo) {
        return new SimpleResponse<>(blobManager.getBlobs(modelMapper.map(searchVo, UserBlobSearchIbo.class)));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除博客")
    @User
    public SimpleResponse<Boolean> deleteBlob(Integer id) {
        return new SimpleResponse<>(blobManager.deleteBlob(id, RequestContext.currentUser()));
    }

    @PostMapping("/collections")
    @ApiOperation(value = "获取用户收藏")
    @Tourist
    public SimpleResponse<PageTemplateBo<SimpleUserBlobBo>> userCollectionBlobs(@RequestBody UserBlobSearchVo vo) {
        return new SimpleResponse<>(blobManager.getCollection(modelMapper.map(vo, UserBlobSearchIbo.class)));
    }

    @PostMapping("/upload")
    @ApiOperation(value = "创作博客时上传图片")
    @User
    public SimpleResponse<String> blobImage(MultipartFile file) {
        return new SimpleResponse<>(fileStorageService.save(file, FileTypeEnum.BLOB_IMAGE));
    }

}
