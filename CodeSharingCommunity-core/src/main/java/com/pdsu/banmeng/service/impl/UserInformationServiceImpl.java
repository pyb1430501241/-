package com.pdsu.banmeng.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.entity.UserInformation;
import com.pdsu.banmeng.enums.AccountStatusEnum;
import com.pdsu.banmeng.enums.StatusEnum;
import com.pdsu.banmeng.exception.BusinessException;
import com.pdsu.banmeng.ibo.ApplyAccountIbo;
import com.pdsu.banmeng.ibo.UserSearchIbo;
import com.pdsu.banmeng.ibo.UserUpdateIbo;
import com.pdsu.banmeng.mapper.UserInformationMapper;
import com.pdsu.banmeng.service.IUserInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public CurrentUser getOne(UserSearchIbo searchIbo) {
        return modelMapper.map(getOne(new QueryWrapper<UserInformation>()
                .setEntity(modelMapper.map(searchIbo, UserInformation.class))), CurrentUser.class);
    }

    @Override
    public boolean applyAccount(ApplyAccountIbo applyAccountIbo, Function<UserInformation, Boolean> after) {
        UserInformation user = modelMapper.map(applyAccountIbo, UserInformation.class);

        user.setAccountStatus(AccountStatusEnum.NORMAL);

        boolean b = save(user);

        if(b) {
            return after.apply(user);
        }

        throw new BusinessException(StatusEnum.USER_ADD_ERROR);
    }

    @Override
    @Cacheable(value = "Code_Sharing_Community_UserInformation_listByUids", key = "#uids")
    public List<CurrentUser> listByUids(List<Integer> uids) {
        QueryWrapper<UserInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("uid", uids);

        return modelMapper.map(list(queryWrapper), new TypeToken<List<CurrentUser>>(){}.getType());
    }

    @Override
    public Boolean update(UserUpdateIbo ibo) {
        return update(modelMapper.map(ibo, UserInformation.class),
                new QueryWrapper<UserInformation>().setEntity(UserInformation.builder().uid(ibo.getUid()).build()));
    }

}
