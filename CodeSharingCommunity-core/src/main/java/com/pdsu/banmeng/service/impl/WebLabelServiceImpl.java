package com.pdsu.banmeng.service.impl;

import com.pdsu.banmeng.bo.WebLabelBo;
import com.pdsu.banmeng.entity.WebLabel;
import com.pdsu.banmeng.mapper.WebLabelMapper;
import com.pdsu.banmeng.service.IWebLabelService;
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
public class WebLabelServiceImpl extends ServiceImpl<WebLabelMapper, WebLabel> implements IWebLabelService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "Code_Sharing_Community_WebLabelService_lists")
    public List<WebLabelBo> lists() {
        return modelMapper.map(list(), new TypeToken<List<WebLabelBo>>(){}.getType());
    }

}
