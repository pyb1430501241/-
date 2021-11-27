package com.pdsu.banmeng.service.impl;

import com.pdsu.banmeng.bo.TypeBo;
import com.pdsu.banmeng.entity.Type;
import com.pdsu.banmeng.mapper.TypeMapper;
import com.pdsu.banmeng.service.ITypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Cacheable(value = "Code_Sharing_Community_TypeService_lists")
    public List<TypeBo> lists() {
        return modelMapper.map(list(), new TypeToken<List<TypeBo>>(){}.getType());
    }

}
