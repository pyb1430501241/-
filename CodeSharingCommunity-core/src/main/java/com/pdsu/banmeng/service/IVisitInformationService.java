package com.pdsu.banmeng.service;

import com.pdsu.banmeng.entity.VisitInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.BlobSearchIbo;
import com.pdsu.banmeng.ibo.VisitSearchIbo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface IVisitInformationService extends IService<VisitInformation> {

    /**
     * 获取 webId 对应的文章的收藏数量
     * @param webIds webIds
     * @return
     * map
     */
    Map<Integer, Integer> countByWebIds(List<Integer> webIds);

    /**
     * 获取特定条件下的文章数量
     * @param blobSearchIbo
     * @return
     */
    Integer count(VisitSearchIbo blobSearchIbo);
}
