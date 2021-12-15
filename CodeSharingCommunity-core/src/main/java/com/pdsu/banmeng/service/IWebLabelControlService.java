package com.pdsu.banmeng.service;

import com.pdsu.banmeng.bo.WebLabelBo;
import com.pdsu.banmeng.entity.WebLabelControl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.BlobSearchIbo;
import com.pdsu.banmeng.ibo.LabelDeleteIbo;
import com.pdsu.banmeng.ibo.LabelSearchIbo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface IWebLabelControlService extends IService<WebLabelControl> {

    /**
     * 根据标签id获取最新的十篇文章
     * @param ibo 条件
     * @return
     * return
     */
    List<Integer> getWebIdsById(BlobSearchIbo ibo);

    /**
     * 根据文章ID 获取其对应的LabelIds
     * @param ibo
     * @return
     */
    List<Integer> getLabelIds(LabelSearchIbo ibo);

    /**
     * 删除标签
     * @param ibo
     * @return
     */
    boolean remove(LabelDeleteIbo ibo);
}
