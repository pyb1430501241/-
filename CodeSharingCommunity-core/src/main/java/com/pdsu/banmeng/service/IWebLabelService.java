package com.pdsu.banmeng.service;

import com.pdsu.banmeng.bo.WebLabelBo;
import com.pdsu.banmeng.entity.WebLabel;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IWebLabelService extends IService<WebLabel> {

    /**
     * 获取所有的标签
     * @return
     */
    List<WebLabelBo> lists();

    /**
     * 获取所有的标签
     * @return
     */
    List<WebLabelBo> listsByIds(List<Integer> ids);

}
