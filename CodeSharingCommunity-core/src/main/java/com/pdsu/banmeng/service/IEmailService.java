package com.pdsu.banmeng.service;

import com.pdsu.banmeng.bo.EmailBo;
import com.pdsu.banmeng.entity.Email;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.banmeng.ibo.EmailSearchIbo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-11-20
 */
public interface IEmailService extends IService<Email> {

    /**
     * 查询特定条件的邮箱信息是否存在
     * @param searchIbo 条件
     * @return
     * 是否存在
     */
    boolean isExist(EmailSearchIbo searchIbo);

    /**
     * 获取特定条件的邮箱
     * @param emailSearchIbo 条件
     * @return
     * 邮箱信息
     */
    EmailBo getEmailByUid(EmailSearchIbo emailSearchIbo);

}
