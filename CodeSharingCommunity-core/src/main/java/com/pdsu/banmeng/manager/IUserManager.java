package com.pdsu.banmeng.manager;

import com.pdsu.banmeng.ibo.ApplyAccountIbo;

/**
 * @author 半梦
 * @email 1430501241@qq.com
 * @since 2021-11-24 20:11
 */
public interface IUserManager {

    /**
     * 添加用户
     * @param applyAccountIbo 用户信息
     * @return
     * 是否成功
     */
    boolean applyAccount(ApplyAccountIbo applyAccountIbo);

}
