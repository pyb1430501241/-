package com.pdsu.banmeng.service.impl;

import com.pdsu.banmeng.entity.UserInformation;
import com.pdsu.banmeng.mapper.UserInformationMapper;
import com.pdsu.banmeng.service.IUserInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
@Service
public class UserInformationServiceImpl extends ServiceImpl<UserInformationMapper, UserInformation> implements IUserInformationService {

}
