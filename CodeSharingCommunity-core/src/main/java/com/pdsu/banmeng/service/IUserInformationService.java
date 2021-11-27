package com.pdsu.banmeng.service;

import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.entity.UserInformation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.ApplyAccountIbo;
import com.pdsu.banmeng.ibo.UserSearchIbo;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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
    boolean applyAccount(ApplyAccountIbo applyAccountIbo, Function<UserInformation, Boolean> after);

    /**
     * 根据uid 获取用户
     * @param uids uid
     * @return
     * 一组用户
     */
    List<CurrentUser> listByUids(List<Integer> uids);

}
