package com.pdsu.banmeng.service;

import com.pdsu.banmeng.entity.WebFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.FileInsertIbo;
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

    /**
     * 文件是否存在
     * @param ibo 条件
     * @return
     * 是否
     */
    Boolean isExist(FileSearchIbo ibo);

    /**
     * 保存文件信息
     * @param ibo
     * @return
     */
    Integer save(FileInsertIbo ibo);

    /**
     * 更新文件
     * @param ibo 条件
     * @return
     */
    Boolean update(FileInsertIbo ibo);
}
