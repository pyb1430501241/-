package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.entity.AccountStatus;
import com.pdsu.banmeng.entity.UserInformation;
import com.pdsu.banmeng.enums.AccountStatusEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import com.pdsu.banmeng.function.BooleanConsumer;
import com.pdsu.banmeng.ibo.ApplyAccountIbo;
import com.pdsu.banmeng.ibo.UserSearchIbo;
import com.pdsu.banmeng.mapper.UserInformationMapper;
import com.pdsu.banmeng.service.IUserInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public boolean isExist(UserSearchIbo searchIbo) {
        return count(new QueryWrapper<UserInformation>()
                .setEntity(modelMapper.map(searchIbo, UserInformation.class))) != 0;
    }


    @Override
    public boolean applyAccount(ApplyAccountIbo applyAccountIbo, BooleanConsumer<UserInformation> after) {
        UserInformation user = modelMapper.map(applyAccountIbo, UserInformation.class);

        user.setAccountStatus(AccountStatusEnum.NORMAL);

        boolean b = save(user);

        if(b) {
            return after.accept(user);
        }

        throw new BusinessException(StatusEnum.USER_ADD_ERROR);
    }


}
