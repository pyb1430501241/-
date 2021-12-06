package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.bo.ImageBo;
import com.pdsu.banmeng.entity.Image;
import com.pdsu.banmeng.ibo.ImageSearchIbo;
import com.pdsu.banmeng.ibo.ImageUpdateIbo;
import com.pdsu.banmeng.mapper.ImageMapper;
import com.pdsu.banmeng.service.IImageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "CodeSharingCommunity_ImageService_getImage", key = "#image.uid")
    public ImageBo getImage(ImageSearchIbo image) {
        return modelMapper.map(getOne(new QueryWrapper<Image>()
                .setEntity(modelMapper.map(image, Image.class))), ImageBo.class);
    }

    @Override
    @Cacheable(value = "CodeSharingCommunity_ImageService_listImageByUids", key = "#uids")
    public List<ImageBo> listImageByUids(List<Integer> uids) {
        QueryWrapper<Image> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("uid", uids);

        return modelMapper.map(list(queryWrapper), new TypeToken<List<ImageBo>>(){}.getType());
    }

    @Override
    public Boolean update(ImageUpdateIbo ibo) {
        return update(Image.builder().imagePath(ibo.getImagePath()).build()
                , new QueryWrapper<Image>().setEntity(Image.builder().uid(ibo.getUid()).build()));
    }

}
