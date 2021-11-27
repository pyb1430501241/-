package com.pdsu.banmeng.service;

import com.pdsu.banmeng.bo.TypeBo;
import com.pdsu.banmeng.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface ITypeService extends IService<Type> {

    /**
     * @return
     * 全部的type
     */
    List<TypeBo> lists();

}
