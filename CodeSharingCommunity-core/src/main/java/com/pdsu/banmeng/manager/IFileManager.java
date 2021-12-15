package com.pdsu.banmeng.manager;

import com.pdsu.banmeng.bo.DownloadBo;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.ibo.FileInsertIbo;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-12-06 19:15
 */
public interface IFileManager {

    /**
     * 用户上传文件处理
     * @param ibo 数据
     * @return
     * 文件id
     */
    Integer insert(FileInsertIbo ibo, CurrentUser currentUser);

    DownloadBo download(Integer id, CurrentUser currentUser);

}
