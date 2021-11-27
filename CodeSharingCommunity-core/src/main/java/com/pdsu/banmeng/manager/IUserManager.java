package com.pdsu.banmeng.manager;

import com.pdsu.banmeng.bo.AuthorBo;
import com.pdsu.banmeng.bo.FansInformationBo;
import com.pdsu.banmeng.bo.PageTemplateBo;
import com.pdsu.banmeng.context.CurrentUser;
import com.pdsu.banmeng.ibo.ApplyAccountIbo;
import com.pdsu.banmeng.ibo.FansSearchIbo;

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

    /**
     * 获取一个作者的信息
     * @param uid uid
     * @return
     * 作者信息
     */
    AuthorBo getAuthor(Integer uid, CurrentUser currentUser);

    /**
     * 获取特定条件的用户信息
     * @param ibo 条件
     * @return
     */
    PageTemplateBo<FansInformationBo> getFans(FansSearchIbo ibo);

}
