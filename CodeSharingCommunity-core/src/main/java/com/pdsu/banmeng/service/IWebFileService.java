package com.pdsu.banmeng.service;

import com.pdsu.banmeng.entity.WebFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.FileSearchIbo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface IWebFileService extends IService<WebFile> {

    /**
     * 获取特定条件下的文件数量
     * @param ibo
     * @return
     */
    Integer count(FileSearchIbo ibo);

}
