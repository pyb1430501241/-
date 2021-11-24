package com.pdsu.banmeng.service;

import com.pdsu.banmeng.bo.ImageBo;
import com.pdsu.banmeng.entity.Image;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.ImageSearchIbo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface IImageService extends IService<Image> {

    /**
     * 根据条件获取用户头像
     * @param image
     * @return
     */
    ImageBo getImage(ImageSearchIbo image);

}
