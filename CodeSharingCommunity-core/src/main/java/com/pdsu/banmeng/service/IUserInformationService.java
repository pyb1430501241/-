package com.pdsu.banmeng.service;

import com.pdsu.banmeng.entity.UserInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.function.BooleanConsumer;
import com.pdsu.banmeng.ibo.ApplyAccountIbo;
import com.pdsu.banmeng.ibo.UserSearchIbo;

import java.util.function.Consumer;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface IUserInformationService extends IService<UserInformation> {

    /**
     * 用户是否存在
     * @param searchIbo 存在条件
     * @return
     * 是否
     */
    boolean isExist(UserSearchIbo searchIbo);

    /**
     * 添加用户信息
     * @param applyAccountIbo 用户信息
     * @param after 添加完成后执行
     * @return
     * 是否添加成功
     */
    boolean applyAccount(ApplyAccountIbo applyAccountIbo, BooleanConsumer<UserInformation> after);

}
