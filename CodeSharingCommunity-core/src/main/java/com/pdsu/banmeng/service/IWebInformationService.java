package com.pdsu.banmeng.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.banmeng.bo.SimpleBlobBo;
import com.pdsu.banmeng.bo.SimpleBlobIndexBo;
import com.pdsu.banmeng.entity.WebInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.BlobInsertIbo;
import com.pdsu.banmeng.ibo.BlobSearchIbo;
import com.pdsu.banmeng.ibo.SimpleBlobInsertIbo;
import com.pdsu.banmeng.ibo.SimpleBlobUpdateIbo;

import java.util.List;
import java.util.function.Function;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface IWebInformationService extends IService<WebInformation> {

    /**
     * 插入一篇文章
     * @param ibo 文章信息
     * @param after 插入成功后的操作
     * @return
     * 文章id
     */
    Integer insert(SimpleBlobInsertIbo ibo, Function<WebInformation, Integer> after);

    /**
     * 更新一篇文章
     * @param ibo 文章信息
     * @param after 插入成功后的操作
     * @return
     * 文章id
     */
    Integer update(SimpleBlobUpdateIbo ibo, Function<WebInformation, Integer> after);

    /**
     * 根据条件获取分页的博客
     * @param searchIbo  条件
     * @return
     * 特定的博客寄
     */
    Page<WebInformation> page(BlobSearchIbo searchIbo);

    /**
     * 根据Uid 判断文章是否存在
     * @param webId 文章id
     * @return
     * 是否
     */
    boolean isExistById(Integer webId);

    /**
     * 获取特定条件下的文章数量
     * @param blobSearchIbo
     * @return
     */
    Integer count(BlobSearchIbo blobSearchIbo);

}
